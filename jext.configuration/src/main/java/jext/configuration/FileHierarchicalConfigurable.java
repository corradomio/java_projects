package jext.configuration;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class FileHierarchicalConfigurable extends HierarchicalConfigurable {
    private FileBasedConfigurationBuilder<XMLConfiguration> builder;

    public FileHierarchicalConfigurable(
        PriorityConfigurable parent, FileBasedConfigurationBuilder<XMLConfiguration> builder) {
        super(parent);
        try {
            this.builder = builder;
            this.config = builder.getConfiguration();
        } catch (ConfigurationException e) { }
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public void save() {
        try {
            builder.save();
        } catch (ConfigurationException e) { }
    }

}
