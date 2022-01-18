package jext.util;

import jext.logging.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesUtils {

    public static final Properties NO_PROPERTIES = new Properties(){
        @Override
        public synchronized Object setProperty(String key, String value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public synchronized Object put(Object key, Object value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public synchronized void putAll(Map<?, ?> t) {
            throw new UnsupportedOperationException();
        }
    };
    public static Properties empty() { return NO_PROPERTIES; }

    // ----------------------------------------------------------------------
    // Load properties
    // ----------------------------------------------------------------------

    public static Properties save(File propertiesFile, Properties properties) {
        try (OutputStream stream = new FileOutputStream(propertiesFile)) {
            properties.store(stream, "");
        } catch (IOException e) {
            Logger.getLogger(PropertiesUtils.class).error(e, e);
        }
        return properties;
    }

    public static Properties load(String path) {
        return load(new File(path));
    }

    public static Properties load(File propertiesFile) {
        Properties properties = new Properties();
        try(InputStream iStream = new FileInputStream(propertiesFile)) {
            properties.load(iStream);
            return properties;
        }
        catch (IOException e) {
            Logger.getLogger(PropertiesUtils.class).error(e, e);
            return properties;
        }
    }

    public static Properties load(File propertiesFile, String prefix) {
        Properties props = load(propertiesFile);
        return filterProperties(props, prefix);
    }

    public static Properties filterProperties(Properties props, String prefix) {
        Properties filtered = new Properties();
        if (prefix == null)
            prefix = "";
        if (prefix.length() > 0 && !prefix.endsWith("."))
            prefix = prefix + ".";

        for (String name : props.stringPropertyNames())
            if (name.startsWith(prefix)) {
                String value = getString(props, name);
                value = resolveValue(value, props);

                String fname = name.substring(prefix.length());
                filtered.setProperty(fname, value);
            }
        return filtered;
    }

    // ----------------------------------------------------------------------
    // Filter properties
    // ----------------------------------------------------------------------

    public static List<String> getValues(Properties properties, String name) {
        return getValues(properties, name, ",", null);
    }

    public static List<String> getValues(Properties properties, String prefix, String sep, String defval) {
        List<String> pvalues = new ArrayList<>();
        for(String name : properties.stringPropertyNames()){
            if (name.startsWith(prefix)) {
                String value = properties.getProperty(name);
                String[] values = value.split(sep);
                pvalues.addAll(Arrays.asList(values));
            }
        }
        if (pvalues.isEmpty() && defval != null) {
            String[] values = defval.split(sep);
            pvalues.addAll(Arrays.asList(values));
        }

        return pvalues;
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
        return resolveValue(properties.getProperty(name), properties);
    }

    public static int getInt(Properties properties, String name, int defaultValue) {
        String value = resolveValue(properties.getProperty(name, String.valueOf(defaultValue)), properties);
        return Integer.parseInt(value);
    }

    public static long getLong(Properties properties, String name, long defaultValue) {
        String value = resolveValue(properties.getProperty(name, String.valueOf(defaultValue)), properties);
        return Long.parseLong(value);
    }

    public static boolean getBoolean(Properties properties, String name, boolean defaultValue) {
        String value = resolveValue(properties.getProperty(name, String.valueOf(defaultValue)), properties);
        if (FALSE_VALUES.contains(value))
            return false;
        if (TRUE_VALUES.contains(value))
            return true;
        else
            return Boolean.parseBoolean(value);
    }

    public static File getFile(Properties properties, String name) {
        String value = resolveValue(properties.getProperty(name), properties);
        return new File(value);
    }

    public static URI getURI(Properties properties, String name) {
        String value = resolveValue(properties.getProperty(name), properties);
        try {
            return new URI(value);
        } catch (URISyntaxException e) {
            return null;
        }
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

    public static List<String>[] splitNamesAndValues(Properties properties) {
        List<String> names = new ArrayList<>();
        List<String> values = new ArrayList<>();
        for(String name : properties.stringPropertyNames()) {
            String value = properties.getProperty(name);
            names.add(name);
            values.add(value);
        }
        List<String>[] result = new List[]{ names, values};
        return result;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public static Properties properties(String ... args) {
        Properties properties = new Properties();
        for(int i=0; i<args.length-1; i+=2)
            properties.put(args[i+0], args[i+1]);
        return properties;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
