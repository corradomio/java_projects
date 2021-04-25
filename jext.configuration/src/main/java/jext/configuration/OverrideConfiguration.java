package jext.configuration;

import com.sun.istack.internal.Nullable;
import jext.util.FileUtils;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

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
    // IO Operations
    // ----------------------------------------------------------------------

    @Override
    public boolean isChanged() {
        for(Configuration configuration : configurations)
            if (configuration.isChanged())
                return true;
        return false;
    }

    @Override
    public void load() throws IOException {
        for(Configuration configuration : configurations)
            if (configuration.isChanged())
                configuration.load();
    }

    @Override
    public void save() throws IOException {
        for(Configuration configuration : configurations)
            configuration.save();
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public void setConfigurationFile(File configurationFile) {
        addConfigurationFile(configurationFile);
        // retrieve the override file
        File overrideFile = getOverrideFile(configurationFile);
        addConfigurationFile(overrideFile);
    }

    public void addConfigurationFile(File configurationFile) {
        if (configurationFile == null) return;
        configurations.add(new XMLConfiguration(configurationFile));
        reversedConfig = new ArrayList<>(configurations);
        Collections.reverse(reversedConfig);
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

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    @Nullable
    private File getOverrideFile(File configurationFile) {
        try {
            Element root = XPathUtils.parse(configurationFile).getDocumentElement();
            Properties properties = XPathUtils.getProperties(root);
            String path = XPathUtils.getValue(root, "override[@path]", "config-override.xml", properties);
            if (FileUtils.isAbsolute(path))
                return new File(path);
            else
                return new File(configurationFile.getParentFile(), path);
        } catch (Exception e) { }
        return null;
    }
}
