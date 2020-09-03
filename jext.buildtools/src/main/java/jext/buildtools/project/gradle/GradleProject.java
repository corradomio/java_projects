package jext.buildtools.project.gradle;

import jext.buildtools.Module;
import jext.buildtools.Named;
import jext.buildtools.Project;
import jext.buildtools.project.BaseProject;
import jext.io.file.FilePatterns;
import jext.util.FileUtils;
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
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class GradleProject extends BaseProject {

    // ----------------------------------------------------------------------
    // Static operations
    // ----------------------------------------------------------------------

    public static final String TYPE = "gradle";
    private static final String MODULE_FILE = "build.gradle";
    private static final String MODULE_FILE_KTS = "build.gradle.kts";

    public static boolean isProject(File projectDir) {
        File buildGradle = new File(projectDir, MODULE_FILE);
        File buildGradleKts = new File(projectDir, MODULE_FILE_KTS);

        return buildGradle.exists() || buildGradleKts.exists();
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    public static final String GRADLE_VERSION = "gradle.version";
    public static final String GRADLE_INSTALLATION = "gradle.installation";
    public static final String GRADLE_URI = "gradle.uri";
    public static final String GRADLE_HOMEDIR = "gradle.homedir";
    public static final String GRADLE_BUILD = "gradle.build";

    private GradleConnector connector;
    private final GradleModule rootModule;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public GradleProject(File projectDir, Properties properties) {
        super(projectDir, properties, TYPE);
        if (!properties.containsKey(PROJECT_MODULE))
            this.properties.setProperty(PROJECT_MODULE, MODULE_FILE);
        this.rootModule = new GradleModule(this);

        List<String> excludes = PropertiesUtils.getValues(this.getProperties(), MODULE_EXCLUDE);
        this.excludes = new FilePatterns().addAll(excludes);

        connect();
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public List<Module> getModules() {
        if (modules != null)
            return modules;

        findModulesByConfig();
        findModulesByGradle();

        modules.sort(Comparator.comparing(Named::getName));
        return modules;
    }

    private void findModulesByConfig() {
        List<File> moduleDirs = new ArrayList<>();
        try {
            Files.walkFileTree(projectDir.toPath(), new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    File moduleDir = dir.toFile();

                    if (excludes.accept(moduleDir.getName()) || excludes.accept(FileUtils.getAbsolutePath(moduleDir)))
                        return FileVisitResult.SKIP_SUBTREE;

                    File buildGradle = new File(moduleDir, MODULE_FILE);
                    File buildGradleKts = new File(moduleDir, MODULE_FILE_KTS);
                    File buildGradleDir = new File(moduleDir, moduleDir.getName() + ".gradle");
                    if (buildGradle.exists() || buildGradleKts.exists() || buildGradleDir.exists())
                        moduleDirs.add(moduleDir);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
        catch (IOException e) { }

        modules = moduleDirs.stream().map(this::newModule)
                .collect(Collectors.toList());
    }

    public void findModulesByGradle() {
        Set<GradleModule> gmodules = new HashSet<>();
        Queue<GradleModule> toVisit = new LinkedList<>();
        toVisit.add(rootModule);

        while(!toVisit.isEmpty()) {
            GradleModule gmodule = toVisit.remove();
            if (gmodules.contains(gmodule))
                continue;

            gmodules.add(gmodule);

            toVisit.addAll(gmodule.getModules());
        }

        for (GradleModule gmodule : gmodules)
            addGradleModule(gmodule);
    }

    @Override
    public Module findModule(String name) {
        for (Module module : getModules()) {
            if (module.getId().equals(name)
                    || module.getName().getFullName().equals(name)
                    || module.getName().getName().equals(name)
                    || module.getDirectory().getAbsolutePath().equals(name))
                return module;
        }
        return null;
    }

    @Override
    protected Module newModule(File moduleDir) {
        return new GradleModule(moduleDir, this);
    }

    // ----------------------------------------------------------------------
    // Connector
    // ----------------------------------------------------------------------

    private void connect() {
        connector = GradleConnector.newConnector().forProjectDirectory(projectDir);

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

    ProjectConnection getConnection() {
        return connector.connect();
    }

    void addGradleModule(GradleModule gmodule) {
        for (Module module : modules)
            if (module.getName().equals(gmodule.getName()))
                return;

        modules.add(gmodule);
    }

}
