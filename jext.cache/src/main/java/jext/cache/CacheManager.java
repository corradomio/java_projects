package jext.cache;

import jext.cache.guava.GuavaCacheProvider;
import jext.logging.Logger;
import jext.util.StringUtils;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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

    private static final CacheManager instance = new CacheManager();

    /**
     * Configure the cache manager.
     * The configuratio tries:
     *
     *      1) read the value 'canche.config' from the system properties,
     *         ad check if the file exists, otherwise it tries for the files
     *      2) 'cache4j.xml'
     *      3) 'cache4j.properties'
     *      4) 'config/cache4j.xml'
     *      5) 'config/cache4j.properties'
     *      6) uses a the default configuration:
     *          cache with 1024 entries without expiring
     */
    public static void configure() {
        instance.defaultConfiguration();
    }

    /** Configure the cache manager using a '.xml' or a '.properties' file */
    public static void configure(File configurationFile) {
        instance.configureUsing(configurationFile);
    }

    /** Configure the cache manager using the list of properties */
    public static void configure(Properties properties) throws Exception {
        instance.configureUsing(properties);
    }

    /**
     * Retrieve the cacche with the speicified name.
     * If the cache doesn't exists, it is created based on the configuration and
     * the cache name.
     *
     * The cache name must be a qualified name as a Java namespace
     */
    public static <K, V> Cache<K, V> getCache(String name, Class<K> kclass, Class<V> vclass) {
        return instance.retrieveCache(name, kclass, vclass);
    }
    public static <K, V> Cache<K, V> getCache(String name) {
        return (Cache<K, V>) getCache(name, Object.class, Object.class);
    }

    /**
     * Destroy all caches and shutdown the cache manager
     */
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
        String[] split;
        Properties properties;

        CacheConfig() {
            this.name = "";
            this.split = new String[0];
            this.properties = new Properties();
            this.properties.setProperty("capacity", "128");
        }

        CacheConfig(String name) {
            this.name = name;
            this.split = name.split("\\.");
            this.properties = new Properties();
        }

        CacheConfig(String name, Properties properties) {
            this.name = name;
            this.split = name.split("\\.");
            this.properties = properties;
        }
    }

    private static Logger logger = Logger.getLogger(CacheManager.class);
    private final List<CacheConfig> configurations = new ArrayList<>();
    private final Map<String, Cache<?,?>> byName = new HashMap<>();
    private final Map<String, Cache<?,?>> byId = new HashMap<>();
    private CacheProvider cacheProvider;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected CacheManager() {

    }

    // ----------------------------------------------------------------------
    // Private operations
    // ----------------------------------------------------------------------

    private void defaultConfiguration() {
        String filename = System.getProperties().getProperty("cache.config", "cache4j.xml");
        File configurations = new File(filename);
        if (configurations.exists()) {
            configureUsing(configurations);
            return;
        }
        configurations = new File("cache4j.properties");
        if (configurations.exists()) {
            configureUsing(configurations);
            return;
        }
        configurations = new File("config/cache4j.xml");
        if (configurations.exists()) {
            configureUsing(configurations);
            return;
        }
        configurations = new File("config/cache4j.properties");
        if (configurations.exists()) {
            configureUsing(configurations);
            return;
        }

        throw new CacheException("Unable to configure CacheManager: no 'cache4j.xml' found");
    }

    private void configureUsing(File configurationsFile) {
        logger.info(String.format("Configure using %s", configurationsFile.getAbsolutePath()));
        try {
            if (configurationsFile.getName().endsWith(".xml")) {
                Element configuration = XPathUtils.parse(configurationsFile).getDocumentElement();
                configureUsing(configuration);
            }
            else if (configurationsFile.getName().endsWith(".properties")) {
                Properties properties = new Properties();
                try (InputStream stream = new FileInputStream(configurationsFile)) {
                    properties.load(stream);
                }
                configureUsing(properties);
            }
            else
                throw new UnsupportedOperationException("Invalid file format " + configurationsFile.toString());
        }
        catch (Throwable t) {
            logger.error(t, t);
            cacheProvider = new GuavaCacheProvider();
        }
    }

    private void configureUsing(Element configuration) throws Exception {
        if (cacheProvider != null) {
            logger.warn("Already configured");
            return;
        }

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
        if (cacheProvider != null) {
            logger.warn("Already configured");
            return;
        }

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
        List<Cache<?, ?>> caches = new ArrayList<>(this.byName.values());
        for (Cache<?, ?> cache : caches)
            cache.close();
        configurations.clear();
        byName.clear();
        byId.clear();
    }

    // ----------------------------------------------------------------------
    // Private operations
    // ----------------------------------------------------------------------

    private <K,V> Cache<K, V> retrieveCache(String name, Class<K> kclass, Class<V> vclass){
        if (cacheProvider == null)
            throw new CacheException("CacheManager not configured");

        synchronized (byName) {
            if (byName.containsKey(name))
                return (Cache<K, V>) byName.get(name);
            if (byId.containsKey(name))
                return (Cache<K, V>) byName.get(name);

            CacheConfig cconfig = getCacheConfig(name);

            Cache<K, V> cache = cacheProvider.createCache(name, kclass, vclass, cconfig.properties);
            ((ManagedCache)cache).setManager(this);

            byName.put(cache.getName(), cache);
            byId.put(cache.getId(), cache);

            return (Cache<K, V>) byName.get(name);
        }
    }

    public <K,V> void removeCache(Cache<K, V> cache) {
        synchronized (byName) {
            byName.remove(cache.getName());
            byId.remove(cache.getId());
        }
    }

    private CacheConfig getCacheConfig(String name) {
        CacheConfig selectedConfig = getDefaultConfig();
        String[] selectedSplit = selectedConfig.split;
        String[] cacheSplit = name.split("\\.");

        for (CacheConfig cc : configurations) {
            // found a cache with the exact name
            // if (name.equals(cc.name))
            //     return cc;

            // select the MOST SPECIFIC configuration
            String[] ccSplit = cc.split;
            // String prefix = cc.name + ".";
            if (hasPrefix(ccSplit, cacheSplit) && ccSplit.length > selectedSplit.length) {
                selectedConfig = cc;
                selectedSplit = ccSplit;
            }
        }
        return selectedConfig;
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

    // ----------------------------------------------------------------------
    // Done
    // ----------------------------------------------------------------------

}
