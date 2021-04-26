package org.hls.check;

import jext.cache.CacheConfiguration;
import jext.configuration.Configuration;
import jext.configuration.HierarchicalConfiguration;
import jext.configuration.PriorityConfiguration;
import jext.logging.Logger;

import java.io.File;
import java.util.Properties;

public class App5 {

    public static void main(String[] args) {
        Logger.configure();

        PriorityConfiguration pconfig = PriorityConfiguration.getConfiguration();
        pconfig.setConfigurationFile(new File("config/splserver.xml"));

        CacheConfiguration cacheConfig = new CacheConfiguration();

        Configuration config = pconfig.configurationAt("cacheManager");
        for (Object lconfig : ((HierarchicalConfiguration)config).configurationsAt("cache")) {
            Configuration cconfig = (Configuration) lconfig;

            String cname = cconfig.getString("[@name]");

            String capacity = cconfig.getString("capacity[@value]", "");
            String expireAfterWrite = cconfig.getString("expireAfterWrite[@value]", "");
            String expireAfterAccess = cconfig.getString("expireAfterAccess[@value]", "");
            String softValues = cconfig.getString("softValues[@value]", "");
            String weakValues = cconfig.getString("weakValues[@value]", "");

            Properties cprops = new Properties();
            if (!capacity.isEmpty())
                cprops.put(jext.cache.CacheManager.CAPACITY, capacity);
            if (!expireAfterWrite.isEmpty())
                cprops.put(jext.cache.CacheManager.EXPIRE_AFTER_WRITE, expireAfterWrite);
            if (!expireAfterAccess.isEmpty())
                cprops.put(jext.cache.CacheManager.EXPIRE_AFTER_ACCESS, expireAfterAccess);
            if (!softValues.isEmpty())
                cprops.put(jext.cache.CacheManager.SOFT_VALUES, softValues);
            if (!weakValues.isEmpty())
                cprops.put(jext.cache.CacheManager.WEAK_VALUES, weakValues);

            cacheConfig.add(cname, cprops);
        }
    }
}
