package org.hls.check.check_neo4j_2;

import jext.configuration.OverrideConfiguration;
import jext.util.FileUtils;
import jext.util.PropertiesUtils;

import java.io.File;
import java.util.Properties;

public class AppConfiguration {

    private static final String SPRING_APP_ROOT = "spring.app.root";

    private static File appRoot;
    private static File configurationDirectory;
    private static OverrideConfiguration configuration;
    private static Properties applicationProperties;
    private static File applicationPropertiesFile;
    private static File loggingConfigurationFile;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------


    public static void configure(String appRoot) {
        AppConfiguration.appRoot = new File(appRoot);
        findConfigurationDirectory();
        initConfiguration();
        initApplicationProperties();
        initLogging();
    }

    private static void initConfiguration() {
        File configDir = configurationDirectory;
        File configurationFile = new File(configDir, "splserver.xml");
        if (!configurationFile.exists())
            throw new IllegalStateException(String.format("Configuration file 'splserver.xml' not found in '%s'",
                FileUtils.getAbsolutePath(configDir))
            );

        configuration = new OverrideConfiguration();
        configuration.setConfigurationFile(appRoot, configurationFile);
    }

    private static void initApplicationProperties() {
        File configDir = configurationDirectory;
        applicationPropertiesFile = new File(configDir, "application.properties");
        if (!applicationPropertiesFile.exists())
            throw new IllegalStateException(String.format("Configuration file 'application.properties' not found in '%s'",
                FileUtils.getAbsolutePath(configDir))
            );

        applicationProperties = PropertiesUtils.load(applicationPropertiesFile);

        System.setProperty(SPRING_APP_ROOT, FileUtils.getAbsolutePath(appRoot));
        applicationProperties.setProperty(SPRING_APP_ROOT, System.getProperty(SPRING_APP_ROOT));
    }

    public static void initLogging() {
        // Note: the logging system is ALREADY initialized&configured by Spring
        // Here we retrieve the location of the configuration file
        String path = PropertiesUtils.getString(applicationProperties, "logging.config");
        if (path == null)
            path = String.format("%s/log4j2.xml", configurationDirectory.getAbsolutePath());
        loggingConfigurationFile = new File(path);
    }

    private static void findConfigurationDirectory() {
        configurationDirectory = new File(appRoot, "WEB-INF");
        if (!configurationDirectory.exists())
            configurationDirectory = new File(appRoot, "config");
        if (!configurationDirectory.exists())
            configurationDirectory = appRoot;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public static OverrideConfiguration getConfiguration() {
        if (configuration == null)
            throw new IllegalStateException("SPL configuration not initialized");
        return configuration;
    }

    public static Properties getApplicationProperties() {
        if (applicationProperties == null)
            throw new IllegalStateException("SPL configuration not initialized");

        return applicationProperties;
    }

    public static File getConfigurationDirectory() {
        return configurationDirectory;
    }

    public static File getApplicationPropertiesFile() {
        return applicationPropertiesFile;
    }

    public static File getLoggingConfigurationFile() {
        return loggingConfigurationFile;
    }

}
