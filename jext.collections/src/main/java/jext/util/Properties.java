package jext.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * This extends 'java.util.Properties' to put_ some useful features:
 *
 *      put_|addProperty  (name,value)
 *          return the Properties object to write:
 *
 *              new Properties().put_().put_() ...
 *
 *      getProperty
 *          the value of a property can contains reference to other properties
 *          written as ${name}. The references will be resolved
 *
 *      getProperties(prefix)
 *          it is possible to select a isSubset of properties with the specified
 *          prefix
 *
 *
 *
 */
public class Properties extends java.util.Properties {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    private static final Properties NO_PROPERTIES = new Properties();
    public static final String NO_VALUE = "";

    // ----------------------------------------------------------------------
    // Static methods
    // ----------------------------------------------------------------------

    public static Properties empty() { return NO_PROPERTIES; }

    // ----------------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------------

    public Properties() {
        super();
    }

    public Properties(java.util.Properties defaults) {
        super(defaults);
    }

    // ----------------------------------------------------------------------
    // Add/get properties
    // ----------------------------------------------------------------------

    public Properties put_(String key, String value) {
        super.setProperty(key, value);
        return this;
    }

    public Properties putAll_(Map<?,?> map) {
        if (map != null)
            super.putAll(map);
        return this;
    }

    public Properties put_(String prefix, String name, String value) {
        if (prefix != null && prefix.length() > 0)
            name = String.format("%s.%s", prefix, name);
        return put_(name,  value);
    }

    public Properties setProperty_(String key, String value) {
        super.setProperty(key, value);
        return this;
    }

    @Override
    public String getProperty(String name) {
        String value = super.getProperty(name);
        return resolveValue(value);
    }

    @Override
    public String getProperty(String name, String defaultValue) {
        String value = super.getProperty(name, defaultValue);
        return resolveValue(value);
    }

    // public java.util.Properties getProperties(String prefix) {
    //     return PropertiesUtils.filterProperties(this, prefix);
    // }

    /**
     * Resolve the variable references (${name}) in the string
     * @param value string value
     * @return resolved value
     */
    private String resolveValue(String value) {
        if (value == null)
            return value;

        int s,e;
        while((s = value.indexOf("${")) != -1) {
            e = value.indexOf("}",s+1);
            if (e == -1) break;

            String name = value.substring(s+2,e);
            String repl = super.getProperty(name, NO_VALUE);
            value = value.substring(0, s) + repl + value.substring(e+1);
        }
        return value;
    }

    /**
     * Load the file content
     */
    public static Properties load(String path) {
        return load(new File(path));
    }

    public static Properties load(File file) {
        try(InputStream in = new FileInputStream(file)) {
            Properties props = new Properties();
            props.load(in);
            return props;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void store(File file, Properties props) {
        try(OutputStream out = new FileOutputStream(file)) {
            props.store(out, "");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
