package jext.gradle;

import jext.gradle.util.UncloseableProjectConnection;
import jext.logging.Logger;
import jext.util.PropertiesUtils;
import jext.util.StringUtils;
import org.gradle.tooling.BuildAction;
import org.gradle.tooling.BuildActionExecuter;
import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnectionException;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ModelBuilder;
import org.gradle.tooling.ProjectConnection;
import org.gradle.tooling.ResultHandler;
import org.gradle.tooling.TestLauncher;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Queue;

public class GradleProject implements AutoCloseable {

    private static Logger logger = Logger.getLogger(GradleProject.class);

    public static final String GRADLE_VERSION = "gradle.version";
    public static final String GRADLE_INSTALLATION = "gradle.installation";
    public static final String GRADLE_URI = "gradle.uri";
    public static final String GRADLE_HOMEDIR = "gradle.homedir";

    private final File projectDir;
    private Properties properties = PropertiesUtils.empty();
    private GradleConnector connector;
    private final GradleModule rootModule;
    private List<GradleModule> modules;

    // private ProjectConnection connection;
    // private ProjectConnection uncloseable;

    public GradleProject(File projectDir) {
        this.projectDir = projectDir;
        this.rootModule = new GradleModule(this);
    }

    public GradleProject properties(Properties properties) {
        this.properties= properties;
        return this;
    }

    public void analyzeStructure() {
        try {
            connect();
            rootModule.analyzeStructure();
        }
        catch (Throwable t) {
            logger.error(t, t);
        }
        finally {
            close();
        }
    }

    public GradleModule getRootModule() {
        return rootModule;
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
            String mname = module.getName();
            if (mname.equals(name) || mname.endsWith(name))
                return module;
        }
        return null;
    }

    public void connect() throws URISyntaxException {
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

        // connection = connector.connect();
        // uncloseable = new UncloseableProjectConnection(connection);
    }

    public void close() {
        // if (connection != null)
        //     connection.close();
        // connection = null;
    }

    public File getProjectDir() {
        return projectDir;
    }

    ProjectConnection getConnection() {
        return connector.connect();
        // return uncloseable;
    }
}
