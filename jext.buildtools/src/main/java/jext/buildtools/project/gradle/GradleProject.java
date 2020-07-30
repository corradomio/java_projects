package jext.buildtools.project.gradle;

import jext.buildtools.Module;
import jext.buildtools.util.BaseProject;
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

public class GradleProject extends BaseProject {

    public static final String TYPE = "gradle";

    public static boolean isProject(File projectDir) {
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

    private GradleConnector connector;
    private final GradleModule rootModule;

    public GradleProject(File projectDir, Properties properties) {
        super(projectDir, properties);
        this.rootModule = new GradleModule(this);
        connect();
    }

    @Override
    public List<Module> getModules() {
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

    @Override
    public Module findModule(String name) {
        for (Module module : getModules()) {
            if (module.getId().equals(name)
                    || module.getName().getFullname().equals(name)
                    || module.getName().getName().equals(name)
                    || module.getDirectory().getAbsolutePath().equals(name))
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
