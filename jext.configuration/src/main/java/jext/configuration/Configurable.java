package jext.configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This class permit to collect multiple configuration files.
 * Some files can be not existent.
 * When a
 */
public class ConfigurationsWithDefaults {

    private static ConfigurationsWithDefaults instance;

    public synchronized static ConfigurationsWithDefaults getConfigurations() {
        if (instance != null)
            return instance;

        instance = new ConfigurationsWithDefaults();
        instance.configure();

        return instance;
    }

    private List<File> configurationFiles = new ArrayList<>();

    /**
     * Register a configuration file.
     *
     * @param configurationFile
     */
    public void addConfigurationFile(File configurationFile) {
        configurationFiles.add(configurationFile);
    }

    public void configure() {

    }
}
