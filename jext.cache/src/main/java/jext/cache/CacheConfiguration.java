package jext.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CacheConfiguration {

    private static class CacheConfig {

        String name;
        String[] split;
        Properties properties;

        CacheConfig() {
            this.name = "";
            this.split = new String[0];
            this.properties = new Properties();
            this.properties.setProperty("capacity", "128");
        }

        CacheConfig(String name, Properties properties) {
            this();
            if (!name.isEmpty()) {
                this.name = name;
                this.split = name.split("\\.");
            }
            this.properties = properties;
        }

    }

    private final List<CacheConfig> configurations = new ArrayList<>();

    public CacheConfiguration() {
        // default configuration
    }

    public void add(String name, Properties properties) {
        this.configurations.add(new CacheConfig(name, properties));
    }

    public Properties get(String name) {
        CacheConfig selectedConfig = getDefaultConfig();
        String[] selectedSplit = selectedConfig.split;
        String[] cacheSplit = name.split("\\.");

        for (CacheConfig cc : configurations) {

            // select the MOST SPECIFIC configuration
            String[] ccSplit = cc.split;
            // String prefix = cc.name + ".";
            if (hasPrefix(ccSplit, cacheSplit) && ccSplit.length > selectedSplit.length) {
                selectedConfig = cc;
                selectedSplit = ccSplit;
            }
        }
        return selectedConfig.properties;
    }

    private static boolean hasPrefix(String[] ccSplit, String[] cacheSplit) {
        if(ccSplit.length > cacheSplit.length)
            return false;
        for (int i=0; i<ccSplit.length; ++i)
            if (ccSplit[i].equals("*"))
                continue;
            else if (!ccSplit[i].equals(cacheSplit[i]))
                return false;
        return true;
    }

    private CacheConfig getDefaultConfig() {
        for (CacheConfig cc : configurations)
            if (cc.split.length == 0)
                return cc;
        return new CacheConfig();
    }
}
