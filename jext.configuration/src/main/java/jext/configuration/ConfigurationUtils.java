package jext.configuration;

import jext.util.FileUtils;
import jext.util.StringUtils;
import jext.util.TimeUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurationUtils {

    public static final String HOME_PATH = "@homePath";

    public static File getHomeFolder(Configuration config) {
        return new File(config.getString(HOME_PATH));
    }

    /**
     * Read a list of keys having the form
     *
     *      [parent]
     *          [key url="..."/]
     *          ...
     *      [/parent]
     */
    public static List<String> loadUrls(Configuration config, String entry) {
        String ekey = "[@url]";
        String prefix = StringUtils.prefixOf(entry, ".");
        String dkey = StringUtils.lastOf(entry, ".");

        Configuration subConfig = ((HierarchicalConfiguration)config).configurationAt(prefix);
        List values = subConfig.getList(dkey + ekey);

        return (List<String>)values;
    }

    /**
     * Read a list of keys having the form
     *
     *      [parent]
     *          [key name='...']
     *              ... value ...
     *          [/key]
     *          ...
     *      [/parent]
     */
    public static Map<String,String> loadDictionary(Configuration config, String entry) {
        String ekey = "[@name]";
        String evalue = "";
        String prefix = StringUtils.prefixOf(entry, ".");
        String dkey = StringUtils.lastOf(entry, ".");

        Configuration subConfig = ((HierarchicalConfiguration)config).configurationAt(prefix);
        List<?> pnames = subConfig.getList(dkey + ekey);
        List<?> values = subConfig.getList(dkey + evalue);

        Map<String,String> props = new HashMap<>();
        for (int i=0; i<pnames.size(); ++i) {
            String pname = pnames.get(i).toString();
            String value = values.get(i).toString();

            props.put(pname, value);
        }

        return props;
    }

    public static File getFile(Configuration config, String key) {
        String path = config.getString(key);
        if (path == null)
            return null;
        if (FileUtils.isAbsolute(path))
            return new File(path);
        else
            return new File(getHomeFolder(config), path);
    }

    public static File getFile(Configuration config, String key, String defaultPath) {
        String path = config.getString(key, defaultPath);
        if (FileUtils.isAbsolute(path))
            return new File(path);
        else
            return new File(getHomeFolder(config), path);
    }

    public static long/*milliseconds*/ getTimeout(Configuration config, String key, long defaultValue) {
        return getTimeout(config, key, String.valueOf(defaultValue));
    }
    public static long/*milliseconds*/ getTimeout(Configuration config, String key, String defaultValue) {
        String interval = config.getString(key, String.valueOf(defaultValue));
        return TimeUtils.parse(interval);
    }
}
