package jext.configuration;

import jext.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurationUtils {

    public static final String HOME_PATH = "homePath";

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
}
