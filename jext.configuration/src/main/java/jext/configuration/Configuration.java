package jext.configuration;

import java.io.File;
import java.util.List;
import java.util.Properties;

/*
    Supported syntax:

        node1.node2                 retrieve the value of the node
        node[@attribute]            retrieve the value of 'attribute'
        node[@attribute='value']    select the node with attribute having the specified value

 */

public interface Configuration {

    /** Home folder used to resolve relative paths */
    File getHomeFolder();
    File getFile();

    /** The configuration is changed IF the configuration file is changed */
    boolean isChanged();
    /** Load the configuration from the configuration file */
    void load();
    /** Save the configuration into the configuration file */
    void save();

    /** Check if the configuration contains the key */
    boolean containsKey(String key);

    /** Getters with default value */
    default boolean getBoolean(String key) { return getBoolean(key, false); }
    default int  getInt(String key) { return getInt(key, 0); }
    default long getLong(String key) { return getLong(key, 0); }
    default String getString(String key) { return getString(key, null); }

    boolean getBoolean(String key, boolean defaultValue);
    int     getInt(String key, int defaultValue);
    long    getLong(String key, long defaultValue);
    String  getString(String key, String defaultValue);

    /**
     * Note:
     *  for OverrideConfiguration, the behavior is to collect the values from ALL configurations
     *  in the order: default configuration + override configuration
     */
    List<String> getList(String key);

    /**
     * Read the content of:
     *
     *      ...
     *      [key]
     *          [property name="name" value="short value"]...long value...[/property]
     *          ...
     *      [key/]
     *
     * Note:
     *  for OverrideConfiguration, the behavior is to merge the properties from ALL configurations
     *  in the order: default configuration + override configuration
     */
    Properties getProperties(String key);

    Object getProperty(String key);
    void setProperty(String key, Object value);

}
