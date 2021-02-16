package jext.sourcecode.project.gradle;

import jext.sourcecode.project.ProjectType;
import jext.sourcecode.project.util.BaseProject;
import jext.sourcecode.project.Module;
import jext.io.file.FilePatterns;
import jext.util.PropertiesUtils;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Queue;
import java.util.Set;

public class GradleProject extends BaseProject {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    public static final ProjectType TYPE = ProjectType.GRADLE;
    private static final String MODULE_FILE = "build.gradle";
    private static final String MODULE_FILE_KTS = "build.gradle.kts";

    public static final String GRADLE_VERSION = "gradle.version";
    public static final String GRADLE_INSTALLATION = "gradle.installation";
    public static final String GRADLE_URI = "gradle.uri";
    public static final String GRADLE_HOMEDIR = "gradle.homedir";
    public static final String GRADLE_BUILD = "gradle.build";
    public static final String GRADLE_CONFIGURATION = "gradle.configuration";

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

        connect();
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public List<Module> getModules() {
        if (modules != null)
            return modules;

        modules = new ArrayList<>();

        findModulesByConfig();
        findModulesByGradle();
        findModulesByJavaSourceRoots();
        addRootModule();

        return modules;
    }

    private void findModulesByConfig() {
        logger.debug("findModulesByConfig");

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
            modules.add(module);
        });
    }

    public void findModulesByGradle() {
        logger.debug("findModulesByGradle");

        Set<GradleModule> gmodules = new HashSet<>();
        Queue<GradleModule> toVisit = new LinkedList<>();
        toVisit.add(rootModule);

        while(!toVisit.isEmpty()) {
            GradleModule gmodule = toVisit.remove();
            gmodules.add(gmodule);

            toVisit.addAll(gmodule.getModules());
        }

        for (GradleModule gmodule : gmodules)
            addGradleModule(gmodule);
    }

    void addGradleModule(GradleModule gmodule) {
        for (Module module : modules)
            if (module.getName().equals(gmodule.getName()))
                return;

        modules.add(gmodule);
    }

    // ----------------------------------------------------------------------
    // Tooling api
    // ----------------------------------------------------------------------

    ProjectConnection getConnection() {
        return connector.connect();
    }

    private void connect() {
        connector = GradleConnector.newConnector().forProjectDirectory(projectHome);

        // useInstallation(File gradleHome);
        // useGradleVersion(String gradleVersion);
        // useDistribution(URI gradleDistribution);
        // useBuildDistribution();
        // useGradleUserHomeDir(File gradleUserHomeDir);

        if (properties.containsKey(GRADLE_VERSION)) {
            String gradleVersion = PropertiesUtils.getString(properties, GRADLE_VERSION);
            connector.useGradleVersion(gradleVersion);
        }

        if (properties.containsKey(GRADLE_INSTALLATION)) {
            File gradleHome = PropertiesUtils.getFile(properties, GRADLE_INSTALLATION);
            connector.useInstallation(gradleHome);
        }

        if (properties.containsKey(GRADLE_URI)) {
            URI gradleDistribution = PropertiesUtils.getURI(properties, GRADLE_URI);
            connector.useDistribution(gradleDistribution);
        }

        if (properties.containsKey(GRADLE_HOMEDIR)) {
            File gradleUserHomeDir = PropertiesUtils.getFile(properties, GRADLE_HOMEDIR);
            connector.useGradleUserHomeDir(gradleUserHomeDir);
        }

        if (properties.containsKey(GRADLE_BUILD)) {
            boolean gradleBluild = PropertiesUtils.getBoolean(properties, GRADLE_BUILD, false);
            if (gradleBluild)
                connector.useBuildDistribution();
        }

    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    protected Module newModule(File moduleHome) {
        return new GradleModule(moduleHome, this);
    }

}