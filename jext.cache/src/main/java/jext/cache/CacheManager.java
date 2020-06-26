package jext.cache;

import jext.cache.guava.GuavaCacheProvider;
import jext.cache.util.ConfiguredCache;
import jext.logging.Logger;
import jext.util.StringUtils;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class CacheConfiguration {

    private static CacheConfiguration instance = new CacheConfiguration();

    public static void configure() {
        instance.defaultConfiguration();
    }

    public static void configure(File configurationFile) {
        instance.configureUsing(configurationFile);
    }

    public static <K, V> Cache<K, V> getCache(String name, Class<K> kclass, Class<V> vclass) {
        return instance.retrieveCache(name);
    }

    static class CacheConfig {
        String name;
        Properties properties;

        CacheConfig() {
            name = "";
            properties = new Properties();
            properties.setProperty("capacity", "128");
        }

        CacheConfig(String name, Properties properties) {
            this.name = name;
            this.properties = properties;
        }
    }

    private static Logger logger = Logger.getLogger(CacheConfiguration.class);
    private final CacheConfig defaultConfig = new CacheConfig();
    private final List<CacheConfig> configurations = new ArrayList<>();
    private final Map<String, Cache> caches = new HashMap<>();
    private CacheProvider cacheProvider;

    public CacheConfiguration() {

    }

    private void defaultConfiguration() {
        File configurations = new File("cache4j.xml");
        if (configurations.exists()) {
            try {
                configureUsing(configurations);
            } catch (Exception e) {
                logger.error(e, e);
            }
        }
    }

    private void configureUsing(File configurationsFile) {

        // default cache provider
        try {
            Element configuration = XPathUtils.parse(configurationsFile).getDocumentElement();
            String providerClass = XPathUtils.getValue(configuration, "provider/@value", "jext.cache.guava.GuavaCacheProvider");
            cacheProvider = (CacheProvider) Class.forName(providerClass).getConstructor().newInstance();

            String name = StringUtils.empty();
            Properties properties;
            for(Element cache : XPathUtils.selectNodes(configuration, "cache")) {
                try {
                    name = XPathUtils.getValue(cache, "@name");
                    properties = XPathUtils.getProperties(cache);

                    CacheConfig cconfig = new CacheConfig(name, properties);
                    configurations.add(cconfig);
                }
                catch (Exception e) {
                    logger.errorf("Unable to configure %s: %s", name, e);
                }
            }
        }
        catch (Throwable t) {
            logger.error(t, t);
            cacheProvider = new GuavaCacheProvider();
        }
    }

    private CacheConfig getCacheConfig(String name) {
        CacheConfig cconfig = defaultConfig;

        for (CacheConfig cc : configurations) {
            // found a chache with the exact name
            if (name.equals(cc.name))
                return cc;

            // select the MOST SPECIFIC configuration
            String prefix = cc.name + ".";
            if (name.startsWith(prefix) && cc.name.length() > cconfig.name.length())
                cconfig = cc;
        }
        return cconfig;
    }

    private <K,V> Cache<K, V> retrieveCache(String name){
        synchronized (caches) {
            if (name == null)
                name = StringUtils.empty();

            if (!caches.containsKey(name)) {
                CacheConfig cconfig = getCacheConfig(name);

                Cache<K, V> cache = cacheProvider.createCache(name, cconfig.properties);
                ((ConfiguredCache)cache).setConfigurator(this);

                caches.put(name, cache);
            }
            return caches.get(name);
        }
    }

    public <K,V> void detach(Cache<K, V> cache) {
        synchronized (caches) {
            caches.remove(cache.getName());
        }
    }
}
