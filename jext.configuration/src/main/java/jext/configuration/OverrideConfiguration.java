package jext.configuration;

import jext.logging.Logger;
import jext.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class OverrideConfiguration implements HierarchicalConfiguration {

    private static final Logger logger = Logger.getLogger(OverrideConfiguration.class);

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    /** Main configuration file */
    private File homeFolder;
    private File configurationFile;
    private File overrideFile;

    /** Configurations */
    private XMLConfiguration configuration;
    private XMLConfiguration overrideConfig;
    private List<XMLConfiguration> configurations;
    private List<XMLConfiguration> reversedConfig;

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
        return configuration.isChanged() || overrideConfig.isChanged();
    }

    @Override
    public void load() {
        if (configuration.isChanged()) {
            configuration.load();
        }
        if (overrideConfig.isChanged()) {
            overrideConfig.load();
        }
    }

    @Override
    public void save() {
        if (configuration.isChanged())
            configuration.save();
        if (overrideConfig.isChanged())
            overrideConfig.save();
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    // DEBUG
    public void setConfigurationFile(File configurationFile) {
        File homeFolder = configurationFile.getAbsoluteFile().getParentFile().getParentFile();
        setConfigurationFile(homeFolder, configurationFile);
    }

    public void setConfigurationFile(File homeFolder, File configurationFile) {
        this.homeFolder = homeFolder;
        
        this.configurationFile = configurationFile;
        this.configuration = new XMLConfiguration(this.configurationFile);
        this.configuration.setParent(this);
        this.configuration.load();

        this.overrideFile = getOverrideFile();
        this.overrideConfig = new XMLConfiguration(this.overrideFile);
        this.configuration.setParent(this);
        this.overrideConfig.load();

        this.configurations = new ArrayList<XMLConfiguration>(){{
            add(configuration);
            add(overrideConfig);
        }};
        this.reversedConfig = new ArrayList<XMLConfiguration>(){{
            add(overrideConfig);
            add(configuration);
        }};
    }

    public void deleteOverrideConfiguration() {
        writeEmptyConfiguration(overrideFile);
    }

    // ----------------------------------------------------------------------
    // Read Properties
    // ----------------------------------------------------------------------

    public File getHomeFolder() {
        return this.homeFolder;
    }

    /** Main configuration file */
    public File getConfigurationFile() {
        return this.configurationFile;
    }

    /** 'namedqueries.xml' configuration file */
    public File getNamedQueriesConfigurationFile() {
        File configurationDirectory = configurationFile.getParentFile();
        File namedQueriesConfigurationFile = new File(configurationDirectory, "namedqueries.xml");
        if (namedQueriesConfigurationFile.exists())
            return namedQueriesConfigurationFile;
        else
            return configurationFile;
    }

    public File getConfigurationDirectory() {
        return this.configurationFile.getParentFile();
    }

    /**
     * Retrieve a sub configuration
     */
    @Override
    public HierarchicalConfiguration configurationAt(String key) {
        return new InnerConfiguration(key, this);
    }

    @Override
    public List<Configuration> configurationsAt(String key) {
        List<Configuration> configList = new ArrayList<>();
        for(HierarchicalConfiguration config : reversedConfig)
            configList.addAll(config.configurationsAt(key));
        return configList;
    }

    // ----------------------------------------------------------------------
    // Read Properties
    // ----------------------------------------------------------------------

    /**
     * The default configuration is the configuration defined in the first
     * file
     */
    public Configuration getDefaultConfiguration() {
        return configuration;
    }

    public Configuration getOverrideConfiguration() {
        return overrideConfig;
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

    @Override
    public List<String> getList(String key) {
        List<String> values = new ArrayList<>();
        for(Configuration config : reversedConfig)
            if (config.containsKey(key))
                values.addAll(config.getList(key));
        return values;
    }

    @Override
    public Properties getProperties(String key) {
        Properties properties = new Properties();
        // keep this order to ensure that the properties
        // defined in the "override" file will override
        // the properties defined in the "default" configuration file"
        for (Configuration config : configurations)
            if (config.containsKey(key))
                properties.putAll(config.getProperties(key));
        return properties;
    }

    // ----------------------------------------------------------------------
    // Write Properties
    // ----------------------------------------------------------------------

    @Override
    public Object getProperty(String key) {
        for(Configuration config : reversedConfig)
            if (config.containsKey(key))
                return config.getProperty(key);
        return null;
    }

    @Override
    public void setProperty(String key, Object value) {
            getOverrideConfiguration().setProperty(key, value);
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    public File getOverrideFile() {
        File overrideFile;
        String path = configuration.getString("override[@path]", "config-override.xml");
        path = path.replace('\\', '/');
        if (path.startsWith("/") || path.indexOf(":/") == 1)
            overrideFile = new File(path);
        else
            overrideFile = new File(homeFolder, path);
        if (!overrideFile.exists())
            writeEmptyConfiguration(overrideFile);
        return overrideFile;
    }

    private void writeEmptyConfiguration(File overrideFile) {
        FileUtils.mkdirs(overrideFile.getAbsoluteFile().getParentFile());
        FileUtils.asFile(overrideFile,
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<configuration/>"
        );
    }

    // private File getOverrideFile(File configurationFile) {
    //     try {
    //         Element root = XPathUtils.parse(configurationFile).getDocumentElement();
    //         Properties properties = XPathUtils.getProperties(root);
    //         String path = XPathUtils.getValue(root, "override[@path]", "config-override.xml", properties);
    //         if (FileUtils.isAbsolute(path))
    //             return new File(path);
    //         else
    //             return new File(configurationFile.getParentFile(), path);
    //     } catch (Exception e) { }
    //
    //     return new File(configurationFile.getParentFile(), "config-override.xml");
    // }
}
