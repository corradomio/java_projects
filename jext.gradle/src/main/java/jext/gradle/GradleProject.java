package jext.gradle;

import jext.util.PropertiesUtils;
import jext.util.StringUtils;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public class GradleProject implements AutoCloseable {

    private File projectDir;
    private Properties properties = PropertiesUtils.empty();
    private GradleConnector connector;
    private GradleModule rootModule;

    public GradleProject(File projectDir) {
        this.projectDir = projectDir;
        this.rootModule = new GradleModule(this);
    }

    public GradleProject properties(Properties properties) {
        this.properties= properties;
        return this;
    }

    public GradleModule getRootModule() {
        return rootModule;
    }

    public void connect() throws URISyntaxException {
        connector = GradleConnector.newConnector().forProjectDirectory(projectDir);

        // useInstallation(File gradleHome);
        // useGradleVersion(String gradleVersion);
        // useDistribution(URI gradleDistribution);
        // useBuildDistribution();
        // useGradleUserHomeDir(File gradleUserHomeDir);

        if (properties.containsKey("gradle.version")) {
            String gradleVersion = PropertiesUtils.getString(properties, "gradle.version");
            connector.useGradleVersion(gradleVersion);
        }

        if (properties.containsKey("gradle.installation")) {
            File gradleHome = PropertiesUtils.getFile(properties, "gradle.installation");
            connector.useInstallation(gradleHome);
        }

        if (properties.containsKey("gradle.uri")) {
            URI gradleDistribution = PropertiesUtils.getURI(properties, "gradle.uri");
            connector.useDistribution(gradleDistribution);
        }

        if (properties.containsKey("gradle.homedir")) {
            File gradleUserHomeDir = PropertiesUtils.getFile(properties, "gradle.homedir");
            connector.useGradleUserHomeDir(gradleUserHomeDir);
        }
    }

    public void close() {  }

    public File getProjectDir() {
        return projectDir;
    }

    ProjectConnection getConnection() {
        return connector.connect();
    }
}
