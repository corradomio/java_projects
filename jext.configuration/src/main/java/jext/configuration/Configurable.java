package jext.configuration;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This class permit to collect multiple configuration files.
 * Some files can be not existent.
 * When a
 */
public class Configurable extends PriorityConfigurable {

    private static Configurable instance;

    public synchronized static Configurable getConfigurable() {
        if (instance != null)
            return instance;

        instance = new Configurable();
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
        Configurations configs = new Configurations();
        for (File configurationFile : configurationFiles) {
            FileBasedConfigurationBuilder<XMLConfiguration> builder = configs.xmlBuilder(configurationFile);
            this.addConfiguration(new FileHierarchicalConfigurable(this, builder));
        }
    }

    public void save() {
        this.peek().save();
    }

}
