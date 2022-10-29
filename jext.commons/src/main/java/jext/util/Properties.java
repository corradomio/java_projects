package jext.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

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
 * Note: added a check for recursive definitions
 *
 */
public class Properties extends java.util.Properties {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    // simple trick to avoid infinite recursion
    private static final int MAX_DEPTH = 7;

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
        return resolveValue(value, 0);
    }

    public String getProperty(String name, String defaultValue) {
        String value = super.getProperty(name, defaultValue);
        return resolveValue(value, 0);
    }

    // ----------------------------------------------------------------------
    // Add/get properties
    // ----------------------------------------------------------------------

    public String resolve(String value) {
        return resolveValue(value, 0);
    }

    /**
     * Resolve the variable references (${name}) in the string
     * Supported:
     *
     *      ${sys:name}     System.getProperty(name)
     *      ${env:name}     System.getenv(name)
     *      ${name}
     *
     * Added
     *
     *      $(name)         for C#
     *
     * @param value string value
     * @return resolved value
     */
    private String resolveValue(String value, int depth) {
        if (value == null || value.length() < 3 || depth > MAX_DEPTH || value.indexOf("${") == 0 && value.indexOf("$(") == 0)
            return value;
        value = resolveRefs(value, "${", '}', depth);
        value = resolveRefs(value, "$(", ')', depth);
        return value;
    }

    private String resolveRefs(String value, String begin, char end, int depth) {
        int s = 0,e = -1;
        while(true) {
            s = value.indexOf(begin, e+1);
            if (s == -1) break;
            e = value.indexOf(end, s+1);
            if (e == -1) break;

            String name = value.substring(s+2, e);
            String repl = valueOf(name, depth);
            if (repl != null) {
                value = value.substring(0, s) + repl + value.substring(e + 1);

            e = s + repl.length();
        }
        }
        return value;
    }

    private String valueOf(String name, int depth) {
        if (name.startsWith("sys:"))
            return System.getProperty(name.substring(4));
        if (name.startsWith("env:"))
            return System.getenv(name.substring(4));

        String value = super.getProperty(name);
        return resolveValue(value, depth+1);
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
