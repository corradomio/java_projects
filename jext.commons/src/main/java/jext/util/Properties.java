package jext.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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

    public String getProperty(String name) {
        String value = super.getProperty(name);
        return resolveValue(value);
    }

    public String getProperty(String name, String defaultValue) {
        String value = super.getProperty(name, defaultValue);
        return resolveValue(value);
    }

    /**
     * Resolve the variable references (${name}) in the string
     * Supported:
     *
     *      ${sys:name}     System.getProperty(name)
     *      ${env:name}     System.getenv(name)
     *      ${name}
     *
     * @param value string value
     * @return resolved value
     */
    private String resolveValue(String value) {
        if (value == null)
            return value;

        int s,e;
        while(value.contains("${")) {
            s = value.indexOf("${");
            e = value.indexOf('}');
            if (e == -1) break;

            String name = value.substring(s+2, e);
            String repl = valueOf(name);
            value = value.substring(0, s) + repl + value.substring(e+1);
        }
        return value;
    }

    private String valueOf(String key) {
        if (key.startsWith("sys:"))
            return System.getProperty(key.substring(4));
        if (key.startsWith("env:"))
            return System.getenv(key.substring(4));
        else
            return super.getProperty(key);
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

    public void store(File file) {
        try(OutputStream out = new FileOutputStream(file)) {
            super.store(out, "");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
