package jext.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class PropertiesUtils {

    public static final Properties NO_PROPERTIES = new Properties();
    public static Properties empty() { return NO_PROPERTIES; }

    // ----------------------------------------------------------------------
    // Load properties
    // ----------------------------------------------------------------------

    public static Properties load(String path) throws IOException {
        return load(new File(path));
    }

    public static Properties load(File propertiesFile) throws IOException {
        try(InputStream iStream = new FileInputStream(propertiesFile)) {
            Properties properties = new Properties();
            properties.load(iStream);
            return properties;
        }
    }

    // ----------------------------------------------------------------------
    // Filter properties
    // ----------------------------------------------------------------------

    /**
     * Select the properties with the specified prefix and return a new
     * Properties object with prefix stripped
     *
     * @param allprops properties
     * @param prefix prefix to select
     * @return
     */
    public static Properties filterProperties(Properties allprops, String prefix) {
        if(!prefix.endsWith("."))
            prefix = prefix + ".";

        Properties props = new Properties();
        for(String name : allprops.stringPropertyNames())
            if (name.startsWith(prefix))
                props.put(name.substring(prefix.length()), allprops.getProperty(name));
        return props;
    }

    // ----------------------------------------------------------------------
    // Get typed values
    // ----------------------------------------------------------------------
    private static Set<String> FALSE_VALUES = new HashSet<String>(){{
        add("0");
        add("false");
        add("False");
        add("FALSE");
        add("f");
        add("F");
        add("#f");
        add("#F");
        add("off");
        add("closed");
    }};
    private static Set<String> TRUE_VALUES = new HashSet<String>(){{
        add("1");
        add("true");
        add("True");
        add("TRUE");
        add("t");
        add("T");
        add("#t");
        add("#T");
        add("on");
        add("opened");
    }};

    public static String getString(Properties properties, String name) {
        return properties.getProperty(name);
    }

    public static int getInt(Properties properties, String name, int defaultValue) {
        String value = properties.getProperty(name, String.valueOf(defaultValue));
        return Integer.parseInt(value);
    }

    public static long getLong(Properties properties, String name, long defaultValue) {
        String value = properties.getProperty(name, String.valueOf(defaultValue));
        return Long.parseLong(value);
    }

    public static boolean getBoolean(Properties properties, String name, boolean defaultValue) {
        String value = properties.getProperty(name, String.valueOf(defaultValue));
        if (FALSE_VALUES.contains(value))
            return false;
        if (TRUE_VALUES.contains(value))
            return true;
        else
            return Boolean.parseBoolean(value);
    }

    public static File getFile(Properties properties, String name) {
        String value = properties.getProperty(name);
        return new File(value);
    }

    public static URI getURI(Properties properties, String name) throws URISyntaxException {
        String value = properties.getProperty(name);
        return new URI(value);
    }

    // ----------------------------------------------------------------------
    // resolve values
    // ----------------------------------------------------------------------

    public static Properties resolveProperties(Properties properties) {
        for(String key : properties.stringPropertyNames())
            properties.put(key, resolveValue(properties.getProperty(key), properties));
        return properties;
    }

    public static Properties resolveProperties(Properties properties, Properties replacements) {
        for(String key : properties.stringPropertyNames())
            properties.put(key, resolveValue(properties.getProperty(key), replacements));
        return properties;
    }

    public static String resolveValue(String value, Properties replacements) {
        if (value == null)
            return value;

        int s,e;
        while((s = value.indexOf("${")) != -1) {
            e = value.indexOf("}",s+1);
            if (e == -1) break;

            String name = value.substring(s+2,e);
            String repl = replacements.getProperty(name, "@{" + name + "}");
            value = value.substring(0, s) + repl + value.substring(e+1);
        }
        return value.replace("@{", "${");
    }

}
