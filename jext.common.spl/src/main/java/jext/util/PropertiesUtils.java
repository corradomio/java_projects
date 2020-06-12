package jext.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

    public static final Properties NO_PROPERTIES = new Properties();
    public static Properties empty() { return NO_PROPERTIES; }

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
