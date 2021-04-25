package jext.configuration;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OverrideConfiguration implements Configuration {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    /** Configurations */
    private List<XMLConfiguration> configurations = new ArrayList<>();

    /** Reverse configuration order */
    private List<XMLConfiguration> reversedConfig = new ArrayList<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public OverrideConfiguration() {

    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public void addConfigurationFile(File configurationFile) {
        configurations.add(new XMLConfiguration(configurationFile));
        reversedConfig = new ArrayList<>(configurations);
        Collections.reverse(reversedConfig);
    }

    public void save() throws IOException, TransformerException {
        if (configurations.size() > 1)
            getOverrideConfiguration().save();
    }

    // ----------------------------------------------------------------------
    // Read Properties
    // ----------------------------------------------------------------------

    /**
     * The default configuration is the configuration defined in the first
     * file
     */
    public Configuration getDefaultConfiguration() {
        return configurations.get(0);
    }

    public Configuration getOverrideConfiguration() {
        return reversedConfig.get(0);
    }

    /**
     * Retrieve a sub configuration
     */
    @Override
    public Configuration configurationAt(String key) {
        return new InnerConfiguration(key, this);
    }

    @Override
    public boolean containsKey(String key) {
        for(Configuration config : reversedConfig)
            if (config.containsKey(key))
                return true;
        return false;
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        for(Configuration config : reversedConfig)
            if (config.containsKey(key))
                return config.getBoolean(key);
        return defaultValue;
    }

    @Override
    public int getInt(String key, int defaultValue) {
        for(Configuration config : reversedConfig)
            if (config.containsKey(key))
                return config.getInt(key);
        return defaultValue;
    }

    @Override
    public long getLong(String key, long defaultValue) {
        for(Configuration config : reversedConfig)
            if (config.containsKey(key))
                return config.getLong(key);
        return defaultValue;
    }

    @Override
    public String getString(String key, String defaultValue) {
        for(Configuration config : reversedConfig)
            if (config.containsKey(key))
                return config.getString(key);
        return defaultValue;
    }

    // ----------------------------------------------------------------------
    // Write Properties
    // ----------------------------------------------------------------------

    @Override
    public void setProperty(String key, Object value) {
        getOverrideConfiguration().setProperty(key, value);
    }

}
