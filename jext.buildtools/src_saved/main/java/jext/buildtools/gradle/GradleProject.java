package jext.buildtools.gradle;

import jext.buildtools.Name;
import jext.buildtools.ProjectAnalyzer;
import jext.logging.Logger;
import jext.util.PropertiesUtils;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Queue;

public class GradleProject implements ProjectAnalyzer {

    public static boolean isValid(File projectDir) {
        File buildGradle = new File(projectDir, "build.gradle");
        File buildGradleKts = new File(projectDir, "build.gradle.kts");

        return buildGradle.exists() || buildGradleKts.exists();
    }

    private static Logger logger = Logger.getLogger(GradleProject.class);

    public static final String GRADLE_VERSION = "gradle.version";
    public static final String GRADLE_INSTALLATION = "gradle.installation";
    public static final String GRADLE_URI = "gradle.uri";
    public static final String GRADLE_HOMEDIR = "gradle.homedir";
    public static final String GRADLE_BUILD = "gradle.build";

    private final File projectDir;
    private Properties properties = PropertiesUtils.empty();
    private GradleConnector connector;
    private final GradleModule rootModule;
    private List<GradleModule> modules;

    public GradleProject(File projectDir) {
        this.projectDir = projectDir;
        this.rootModule = new GradleModule(this);
        connect();
    }

    public GradleProject properties(Properties properties) {
        this.properties= properties;
        return this;
    }

    public String getName() {
        return projectDir.getName();
    }

    public GradleProject initialize() {
        getModules();
        return this;
    }

    public List<GradleModule> getModules() {
        if (modules != null)
            return modules;

        modules = new ArrayList<>();
        Queue<GradleModule> toVisit = new LinkedList<>();
        toVisit.add(rootModule);

        while(!toVisit.isEmpty()) {
            GradleModule module = toVisit.remove();
            modules.add(module);

            toVisit.addAll(module.getModules());
        }

        return modules;
    }

    public GradleModule getModule(String name) {
        for (GradleModule module : getModules()) {
            Name mname = module.getName();
            if (mname.toString().equals(name) || name.length() > 0 && mname.toString().endsWith(name))
                return module;
        }
        return null;
    }

    // private void analyzeStructure() {
    //     try {
    //         connect();
    //         getModules().forEach(GradleModule::analyzeStructure);
    //     }
    //     catch (Throwable t) {
    //         logger.error(t, t);
    //     }
    //     finally {
    //         close();
    //     }
    // }

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

        else if (properties.containsKey(GRADLE_INSTALLATION)) {
            File gradleHome = PropertiesUtils.getFile(properties, GRADLE_INSTALLATION);
            connector.useInstallation(gradleHome);
        }

        // else if (properties.containsKey(GRADLE_URI)) {
        //     URI gradleDistribution = PropertiesUtils.getURI(properties, GRADLE_URI);
        //     connector.useDistribution(gradleDistribution);
        // }

        else if (properties.containsKey(GRADLE_HOMEDIR)) {
            File gradleUserHomeDir = PropertiesUtils.getFile(properties, GRADLE_HOMEDIR);
            connector.useGradleUserHomeDir(gradleUserHomeDir);
        }

        else if (properties.containsKey(GRADLE_BUILD)) {
            boolean gradleBluild = PropertiesUtils.getBoolean(properties, GRADLE_BUILD, false);
            if (gradleBluild)
                connector.useBuildDistribution();
        }

        else if (hasGradleWrapper()) {

        }

        // connection = connector.connect();
        // uncloseable = new UncloseableProjectConnection(connection);
    }

    public File getProjectDir() {
        return projectDir;
    }

    ProjectConnection getConnection() {
        return connector.connect();
        // return uncloseable;
    }

    private boolean hasGradleWrapper() {
        return new File(projectDir, "gradle/wrapper").exists();
    }

    // public void dump() {
    //
    //     getModules().forEach(module -> {
    //         System.out.printf("Module %s (%s)\n", module.getName(), module.isValid());
    //         System.out.println("... dmodules");
    //         module.getModuleDependencies()
    //                 .forEach(dep -> {
    //                     System.out.printf("... ... %s\n", dep);
    //                 });
    //         System.out.println("... dependencies");
    //         module.getDependencies()
    //                 .forEach(dep -> {
    //                     System.out.printf("... ... %s\n", dep);
    //                 });
    //     });
    //     System.out.println("Done");
    // }
}
