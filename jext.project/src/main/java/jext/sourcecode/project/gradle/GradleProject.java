package jext.sourcecode.project.gradle;

import jext.io.file.FilePatterns;
import jext.maven.MavenCoords;
import jext.maven.MavenDownloader;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Modules;
import jext.sourcecode.project.gradle.collectors.AllDepsCollector;
import jext.sourcecode.project.gradle.collectors.ErrorsCollector;
import jext.sourcecode.project.gradle.collectors.LoggerCollector;
import jext.sourcecode.project.util.LibrarySet;
import jext.sourcecode.project.maven.MavenLibrary;
import jext.sourcecode.project.util.BaseProject;
import jext.sourcecode.project.util.ModulesImpl;
import jext.util.FileUtils;
import jext.util.PropertiesUtils;
import org.gradle.tooling.BuildAction;
import org.gradle.tooling.BuildActionExecuter;
import org.gradle.tooling.BuildException;
import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnectionException;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ModelBuilder;
import org.gradle.tooling.ProjectConnection;
import org.gradle.tooling.ResultHandler;
import org.gradle.tooling.TestLauncher;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class GradleProject extends BaseProject {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    public static final String TYPE = "gradle";
    private static final String MODULE_FILE = "build.gradle";
    private static final String MODULE_FILE_KTS = "build.gradle.kts";

    public static final String BUILD_GRADLE = "build.gradle";

    public static final String GRADLE_VERSION = "gradle.version";
    public static final String GRADLE_INSTALLATION = "gradle.installation";
    public static final String GRADLE_URI = "gradle.uri";
    public static final String GRADLE_HOMEDIR = "gradle.home";
    public static final String GRADLE_BUILD = "gradle.build";
    public static final String GRADLE_CONFIGURATION = "gradle.configuration";

    private static final String GRADLE_HOME = "GRADLE_HOME";
    private static final String NO_GRADLE_HOME = "NO_GRADLE_HOME";

    static String[] VALID_CONFIGURATIONS = {
        "default",
        "implementation",
        "compile",
        "test"
    };

    // ----------------------------------------------------------------------
    // Static methods
    // ----------------------------------------------------------------------

    public static boolean isProject(File projectHome, Properties props) {
        File buildGradle = new File(projectHome, MODULE_FILE);
        File buildGradleKts = new File(projectHome, MODULE_FILE_KTS);

        return buildGradle.exists() || buildGradleKts.exists();
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private final GradleModule rootModule;
    private GradleConnector connector;
    protected FilePatterns excludes;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public GradleProject(String projectName, File projectHome, Properties properties) {
        super(projectName, projectHome, properties, TYPE);
        if (!properties.containsKey(PROJECT_MODULE))
            this.properties.setProperty(PROJECT_MODULE, MODULE_FILE);

        this.rootModule = new GradleModule(this);

        List<String> excludes = PropertiesUtils.getValues(this.getProperties(), MODULE_EXCLUDE);
        this.excludes = new FilePatterns().addAll(excludes);

        // FIRST update 'build.gradle' THEN create the GradleToolingAPI connector!
        // updateBuildGradle();
        connect();
        // retrieveAllDependencies();
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public Modules getModules() {
        if (modules != null)
            return modules;

        modules = new ModulesImpl();

        addModule(rootModule);
        findModulesByConfig();
        findModulesByGradle();
        findModulesByJavaSourceRoots();
        //addRootModule();

        return modules;
    }

    void addModule(Module module) {
        if (modules.getModule(module.getPath()) != null)
            return;

        modules.add(module);
    }

    private void findModulesByConfig() {
        List<File> moduleHomes = new ArrayList<>();
        try {
            Files.walkFileTree(projectHome.toPath(), new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    File moduleHome = dir.toFile();

                    if (excludes.accept(projectHome, moduleHome))
                        return FileVisitResult.SKIP_SUBTREE;

                    File buildGradle = new File(moduleHome, MODULE_FILE);
                    File buildGradleKts = new File(moduleHome, MODULE_FILE_KTS);
                    File buildGradleDir = new File(moduleHome, moduleHome.getName() + ".gradle");
                    if (buildGradle.exists() || buildGradleKts.exists() || buildGradleDir.exists())
                        moduleHomes.add(moduleHome);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
        catch (IOException e) { }

        moduleHomes.forEach(moduleHome -> {
            Module module = newModule(moduleHome);
            module.getProperties().setProperty(MODULE_DEFINITION, MODULE_DEFINITION_BY_CONFIGURATION);
            addModule(module);
        });
    }

    // private void addGradleModule(String moduleName) {
    //     if (getModule(moduleName) != null)
    //         return;
    //
    //     addModule(newModule(new File(projectHome, moduleName)));
    // }

    public void findModulesByGradle() {
        if (isDependenciesResolved())
            addResolvedGradleModules();
        else
            findModulesCallingGradle();
    }

    private void findModulesCallingGradle() {

        try {
            openConnection();

            rootModule.getModules().forEach(this::addModule);
        }
        finally {
            closeConnection();
        }

        // try {
        //     openConnection();
        //
        //     Set<GradleModule> visited = new HashSet<>();
        //     Queue<GradleModule> toVisit = new LinkedList<>();
        //     toVisit.add(rootModule);
        //
        //     while (!toVisit.isEmpty() && !isAborted()) {
        //         GradleModule gmodule = toVisit.remove();
        //         visited.add(gmodule);
        //
        //         toVisit.addAll(gmodule.getModules());
        //     }
        //
        //     for (GradleModule gmodule : visited) {
        //         gmodule.getDeclaredLibraries();
        //         gmodule.getMavenRepositories();
        //         addModule(gmodule);
        //     }
        // }
        // finally {
        //     closeConnection();
        // }
    }

    // ----------------------------------------------------------------------
    // updateBuildGradle
    // ----------------------------------------------------------------------
    // this method append the content of the resource 'jext.sourcecode.project.gradle.splalldeps.gradle
    // at the end of <projectHome>/build.gradle

    private static final String HEADER = "// SPLAllDependencies::BEGIN";
    private static final String FOOTER = "// SPLAllDependencies::END";
    private AllDepsCollector depsCollector;

    private void updateBuildGradle() {
        File buildGradle = new File(projectHome, BUILD_GRADLE);
        if (!buildGradle.exists()) {
            logger.errorf("Gradle project %s WITHOUT 'build.gradle'", getName().getFullName());
            return;
        }

        String script = FileUtils.toString(this.getClass().getResourceAsStream(
            "/jext/sourcecode/project/gradle/splalldeps.gradle"));

        // read the file content
        String content = FileUtils.toString(buildGradle);
        int pos = content.indexOf(HEADER);
        int end = content.indexOf(FOOTER);

        // check the 'content' contains HEADER
        if (pos == -1) {
            // add the script
            content += "\n\n" + script;
        }
        else if (end == -1) {
            content = content.substring(0, pos) + script;
        }
        else {
            content = content.substring(0, pos) + script + content.substring(end + FOOTER.length());

        }
        FileUtils.asFile(buildGradle, content);
    }

    private void retrieveAllDependencies() {
        ErrorsCollector err = new ErrorsCollector(logger);
        AllDepsCollector collector = new AllDepsCollector();
        LoggerCollector logcoll = new LoggerCollector(logger, collector);
        try {
            openConnection();
            connection.newBuild().forTasks("splAllDeps")
                .withArguments("--continue")
                // .setStandardOutput(collector)            // this
                .setStandardOutput(logcoll)                 // OR this
                .setStandardError(err)
                .run();

            this.depsCollector = collector;
        }
        catch (BuildException e) {
            String message = e.getCause().getMessage();
            if (!message.contains("not found in root project"))
                logger.error(e);
        }
        finally {
            logcoll.close();
            collector.close();
            err.close();
            closeConnection();
        }

    }

    boolean isDependenciesResolved() {
        return depsCollector != null;
    }

    Set<Library> getMavenLibraries(GradleModule module) {
        String moduleName = module.getName().getFullName();
        if (!depsCollector.isGradleProject(moduleName))
            return Collections.emptySet();

        MavenDownloader md = getLibraryDownloader();

        LibrarySet libraries = new LibrarySet();
        for (String configurationName : VALID_CONFIGURATIONS) {

            Set<String> moduleLibraries = depsCollector.getMavenLibraries(moduleName, configurationName);

            moduleLibraries
                .stream()
                .map(MavenCoords::of)
                .map(coords -> new MavenLibrary(coords, md, this))
                .forEach(libraries::add);
        }

        return libraries;
    }

    Set<String> getMavenRepositories(GradleModule module) {
        return depsCollector.getMavenRepositories();
    }

    private void addResolvedGradleModules() {
        depsCollector.getGradleProjectNames().forEach(gradleProjectName -> {
            Module module = newModule(new File(projectHome, gradleProjectName));
            module.getProperties().setProperty(MODULE_DEFINITION, MODULE_DEFINITION_BY_CONFIGURATION);
            addModule(module);
        });
    }

    // ----------------------------------------------------------------------
    // Tooling api
    // ----------------------------------------------------------------------

    private static class NoCloseableProjectConnection implements ProjectConnection {

        private ProjectConnection pc;

        NoCloseableProjectConnection(ProjectConnection pc) {
            this.pc = pc;
        }

        @Override
        public <T> T getModel(Class<T> aClass) throws GradleConnectionException, IllegalStateException {
            return pc.getModel(aClass);
        }

        @Override
        public <T> void getModel(Class<T> aClass, ResultHandler<? super T> resultHandler) throws IllegalStateException {
            getModel(aClass, resultHandler);
        }

        @Override
        public BuildLauncher newBuild() {
            return pc.newBuild();
        }

        @Override
        public TestLauncher newTestLauncher() {
            return pc.newTestLauncher();
        }

        @Override
        public <T> ModelBuilder<T> model(Class<T> aClass) {
            return pc.model(aClass);
        }

        @Override
        public <T> BuildActionExecuter<T> action(BuildAction<T> buildAction) {
            return pc.action(buildAction);
        }

        @Override
        public BuildActionExecuter.Builder action() {
            return pc.action();
        }

        @Override
        public void notifyDaemonsAboutChangedPaths(List<Path> list) {
            pc.notifyDaemonsAboutChangedPaths(list);
        }

        @Override
        public void close() {

        }
    }

    private ProjectConnection connection;

    ProjectConnection getConnection() {
        if (connection != null)
            return new NoCloseableProjectConnection(connection);

        connection = connector.connect();
        return new NoCloseableProjectConnection(connection);
    }

    private void openConnection() {
        connection = connector.connect();
    }

    private void closeConnection() {
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }

    private void connect() {
        connector = GradleConnector.newConnector().forProjectDirectory(projectHome);

        // useInstallation(File gradleHome);
        // useGradleVersion(String gradleVersion);
        // useDistribution(URI gradleDistribution);
        // useBuildDistribution();
        // useGradleUserHomeDir(File gradleUserHomeDir);

        // check if "gradlew.*" is present in 'projectHome'
        File[] gradlews = projectHome.listFiles(file -> file.getName().startsWith("gradlew"));
        if (gradlews != null && gradlews.length > 0) {

        }
        else if (System.getenv().containsKey(GRADLE_HOME)) {
            File gradleHome = new File(System.getenv(GRADLE_HOME));

            if (gradleHome.exists()) {
                logger.infof("GRADLE_HOME defined: using local installation at %s", FileUtils.getAbsolutePath(gradleHome));
                connector.useInstallation(gradleHome);
            }
            else
                logger.warnf("GRADLE_HOME=%s: home directory not existent", FileUtils.getAbsolutePath(gradleHome));
        }

        if (properties.containsKey(GRADLE_VERSION)) {
            String gradleVersion = PropertiesUtils.getString(properties, GRADLE_VERSION);

            logger.infof("%s defined: using Gradle v%s", GRADLE_VERSION, gradleVersion);

            connector.useGradleVersion(gradleVersion);
        }
        if (properties.containsKey(GRADLE_INSTALLATION)) {
            File gradleHome = PropertiesUtils.getFile(properties, GRADLE_INSTALLATION);

            connector.useInstallation(gradleHome);
            if (gradleHome.exists()) {
                logger.infof("%s defined: using local installation at %s", GRADLE_INSTALLATION, FileUtils.getAbsolutePath(gradleHome));

                connector.useInstallation(gradleHome);
            }
            else
                logger.warnf("%s=%s: installation not existent", GRADLE_INSTALLATION, FileUtils.getAbsolutePath(gradleHome));
        }

        if (properties.containsKey(GRADLE_URI)) {
            URI gradleDistribution = PropertiesUtils.getURI(properties, GRADLE_URI);

            logger.infof("%s defined: using %s", GRADLE_URI, gradleDistribution);

            connector.useDistribution(gradleDistribution);
        }

        if (properties.containsKey(GRADLE_HOMEDIR)) {
            File gradleHome = PropertiesUtils.getFile(properties, GRADLE_HOMEDIR);
            if (gradleHome.exists()) {
                logger.infof("%s defined: using local installation at %s", GRADLE_INSTALLATION, FileUtils.getAbsolutePath(gradleHome));

                connector.useGradleUserHomeDir(gradleHome);
            }
            else
                logger.warnf("%s=%s: home directory not existent", GRADLE_HOMEDIR, FileUtils.getAbsolutePath(gradleHome));
        }

        if (properties.containsKey(GRADLE_BUILD)) {
            boolean gradleBluild = PropertiesUtils.getBoolean(properties, GRADLE_BUILD, false);

            logger.infof("%s=%s", GRADLE_BUILD, gradleBluild);

            if (gradleBluild)
                connector.useBuildDistribution();
        }

    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    protected Module newModule(String gradleProjectName) {
        if (gradleProjectName.startsWith(":"))
            gradleProjectName = gradleProjectName.substring(1);
        gradleProjectName = gradleProjectName.replace(":", "/");
        if (gradleProjectName.isEmpty())
            return rootModule;

        Module module = newModule(new File(projectHome, gradleProjectName));
        module.getProperties().setProperty(MODULE_DEFINITION, MODULE_DEFINITION_BY_CONFIGURATION);
        return module;
    }

    @Override
    protected Module newModule(File moduleHome) {
        Module module = new GradleModule(moduleHome, this);
        module.getProperties().setProperty(MODULE_DEFINITION, MODULE_DEFINITION_BY_HEURISTIC);
        return module;
    }

}