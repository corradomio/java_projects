package jext.util;

import jext.logging.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class PropertiesUtils {

    public static final Properties NO_PROPERTIES = new Properties();
    public static Properties empty() { return NO_PROPERTIES; }

    // ----------------------------------------------------------------------
    // Load properties
    // ----------------------------------------------------------------------

    public static Properties load(String path) {
        return load(new File(path));
    }

    public static Properties load(File propertiesFile) {
        try(InputStream iStream = new FileInputStream(propertiesFile)) {
            Properties properties = new Properties();
            properties.load(iStream);
            return properties;
        }
        catch (IOException e) {
            Logger.getLogger(PropertiesUtils.class).error(e, e);
            return new Properties();
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
    
    public static long getTimeoutMillis(Properties props, String name, long defvalue) {
        String value = props.getProperty(name, null);
        if (value == null)
            return defvalue;
        
        int end = value.length()-1;
        double seconds = 0.;
        // 10s
        if (value.endsWith("s"))
            seconds = Double.parseDouble(value.substring(0, end));
        // 10m
        else if (value.endsWith("m"))
            seconds = Double.parseDouble(value.substring(0, end))*60;
        // 10h
        else if (value.endsWith("h"))
            seconds = Double.parseDouble(value.substring(0, end))*60*60;
        // 10d
        else if (value.endsWith("d"))
            seconds = Double.parseDouble(value.substring(0, end))*24*60*60;
        // dd:hh:mm:ss
        else if (value.indexOf(':') != -1) {
            String[] parts = value.split(":");
            switch (parts.length) {
                case 2:
                    seconds = Double.parseDouble(parts[0])*60 
                            + Double.parseDouble(parts[1]);
                    break;
                case 3:
                    seconds = Double.parseDouble(parts[0])*60*60
                            + Double.parseDouble(parts[1])*60
                            + Double.parseDouble(parts[2]);
                    break;
                case 4:
                    seconds = Double.parseDouble(parts[0])*24*60*60
                            + Double.parseDouble(parts[1])*60*60
                            + Double.parseDouble(parts[2])*60
                            + Double.parseDouble(parts[3]);
                    break;
                default:
                    seconds = Double.parseDouble(parts[0]);
                    break;
            }
        }
        else {
            seconds = Double.parseDouble(value)/1000.;
        }
        
        return (long)(seconds*1000);
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
