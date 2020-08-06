package jext.cache;

import jext.cache.guava.GuavaCacheProvider;
import jext.cache.util.ManagedCache;
import jext.logging.Logger;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class CacheManager {

    // ----------------------------------------------------------------------
    // Static methods
    // ----------------------------------------------------------------------
    // Properties:
    //
    //      jext.cache.cacheProvider={providerClass}
    //      jext.cache.{qualifiedName}.capacity=<capacity>
    //      jext.cache.{qualifiedName}.expireAfterWrite=<timeout>
    //      jext.cache.{qualifiedName}.expireAfterAccess=<timeout>
    //      jext.cache.{qualifiedName}.weakValues=<boolean>
    //
    // XML:
    //
    //      <configuration>
    //          <provider name="{providerClass}"/>
    //          <cache name="{qualifiedName}">
    //              <property name="..." value="..."/>
    //              ...
    //          </cache>
    //          <cache name="...">
    //              ...
    //          </cache>
    //      </configuration>
    //
    //  property names: 'capacity', 'expireAfterWrite', 'expireAfterAccess', 'weakValues'
    //

    private static CacheManager instance = new CacheManager();

    public static void configure() {
        instance.defaultConfiguration();
    }

    public static void configure(File configurationFile) {
        instance.configureUsing(configurationFile);
    }

    public static void configure(Properties properties) throws Exception {
        instance.configureUsing(properties);
    }

    public static <K, V> Cache<K, V> getCache(String name, Class<K> kclass, Class<V> vclass) {
        return instance.retrieveCache(name, kclass, vclass);
    }

    public static void shutdown() {
        instance.clear();
    }

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private static final String DEFAULT_CACHE_PROVIDER = "jext.cache.guava.GuavaCacheProvider";
    private static final String CACHE_PROVIDER = "jext.cache.cacheProvider";
    private static final String CACHE_PREFIX = "jext.cache.";

    private static class CacheConfig {
        String name;
        Properties properties;

        CacheConfig() {
            name = "";
            properties = new Properties();
            properties.setProperty("capacity", "128");
        }

        CacheConfig(String name) {
            this.name = name;
            this.properties = new Properties();
        }

        CacheConfig(String name, Properties properties) {
            this.name = name;
            this.properties = properties;
        }
    }

    private static Logger logger = Logger.getLogger(CacheManager.class);
    private final List<CacheConfig> configurations = new ArrayList<>();
    private final Map<String, Cache<?,?>> caches = new HashMap<>();
    private CacheProvider cacheProvider;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private CacheManager() {

    }

    // ----------------------------------------------------------------------
    // Private operations
    // ----------------------------------------------------------------------

    private void defaultConfiguration() {
        File configurations = new File("cache4j.xml");
        if (configurations.exists()) {
            configureUsing(configurations);
        }
    }

    private void configureUsing(File configurationsFile) {
        try {
            if (configurationsFile.getName().endsWith(".xml")) {
                Element configuration = XPathUtils.parse(configurationsFile).getDocumentElement();
                configureUsing(configuration);
            }
            if (configurationsFile.getName().endsWith(".properties")) {
                Properties properties = new Properties();
                try (InputStream stream = new FileInputStream(configurationsFile)) {
                    properties.load(stream);
                }
                configureUsing(properties);
            }
            throw new UnsupportedOperationException("Invalid file format " + configurationsFile.toString());
        }
        catch (Throwable t) {
            logger.error(t, t);
            cacheProvider = new GuavaCacheProvider();
        }
    }

    private void configureUsing(Element configuration) throws Exception {
        String name = "";
        Properties properties;
        for(Element cache : XPathUtils.selectNodes(configuration, "cache")) {
            try {
                name = XPathUtils.getValue(cache, "@name");
                properties = XPathUtils.getProperties(cache);

                CacheConfig cconfig = new CacheConfig(name, properties);
                configurations.add(cconfig);
            }
            catch (Exception e) {
                logger.errorf("Unable to configure '%s': %s", name, e);
            }
        }

        String providerClass = XPathUtils.getValue(configuration, "provider/@value", DEFAULT_CACHE_PROVIDER);
        cacheProvider = (CacheProvider) Class.forName(providerClass).getConstructor().newInstance();
    }

    private void configureUsing(Properties properties) throws Exception {
        for(String key : properties.stringPropertyNames()) {
            if (key.equals(CACHE_PROVIDER) || !key.startsWith(CACHE_PREFIX))
                continue;

            String name = nameOf(key);
            String cckey = keyOf(key);

            CacheConfig cconfig = createCacheConfig(name);
            cconfig.properties.put(cckey, properties.getProperty(key));
        }

        String providerClass = properties.getProperty("jext.cache.cacheProvider", DEFAULT_CACHE_PROVIDER);
        cacheProvider = (CacheProvider) Class.forName(providerClass).getConstructor().newInstance();
    }

    private CacheConfig createCacheConfig(String name) {
        for (CacheConfig cconfig : configurations)
            if (cconfig.name.equals(name))
                return cconfig;
        CacheConfig cconfig = new CacheConfig(name);
        configurations.add(cconfig);
        return cconfig;
    }

    private static String nameOf(String key) {
        int pos;
        key = key.substring(CACHE_PREFIX.length());
        pos = key.lastIndexOf('.');
        if (pos == -1)
            return "";
        else
            return key.substring(0, pos);
    }

    private static String keyOf(String key) {
        int pos = key.lastIndexOf('.');
        return key.substring(pos + 1);
    }

    private void clear() {
        List<Cache<?, ?>> caches = new ArrayList<>(this.caches.values());
        for (Cache<?, ?> cache : caches)
            cache.close();
        configurations.clear();
        cacheProvider = null;
    }

    // ----------------------------------------------------------------------
    // Private operations
    // ----------------------------------------------------------------------

    private <K,V> Cache<K, V> retrieveCache(String name, Class<K> kclass, Class<V> vclass){
        if (cacheProvider == null)
            throw new CacheException("CacheManager not configured");

        synchronized (caches) {

            if (!caches.containsKey(name)) {
                CacheConfig cconfig = getCacheConfig(name);

                Cache<K, V> cache = cacheProvider.createCache(name, kclass, vclass, cconfig.properties);
                ((ManagedCache)cache).setManager(this);

                caches.put(name, cache);
            }
            return (Cache<K, V>) caches.get(name);
        }
    }

    public <K,V> void removeCache(Cache<K, V> cache) {
        synchronized (caches) {
            caches.remove(cache.getName());
        }
    }

    private CacheConfig getCacheConfig(String name) {
        CacheConfig cconfig = getDefaultConfig();

        for (CacheConfig cc : configurations) {
            // found a cache with the exact name
            if (name.equals(cc.name))
                return cc;

            // select the MOST SPECIFIC configuration
            String prefix = cc.name + ".";
            if (name.startsWith(prefix) && cc.name.length() > cconfig.name.length())
                cconfig = cc;
        }
        return cconfig;
    }

    private CacheConfig getDefaultConfig() {
        for (CacheConfig cc : configurations)
            if (cc.name.isEmpty())
                return cc;
        return new CacheConfig();
    }

    // ----------------------------------------------------------------------
    // Done
    // ----------------------------------------------------------------------

}
