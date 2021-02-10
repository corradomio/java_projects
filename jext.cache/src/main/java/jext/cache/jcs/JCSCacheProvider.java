package jext.cache.jcs;

import jext.cache.Cache;
import jext.cache.CacheProvider;
import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.access.CacheAccess;

import java.util.Properties;

public class JCSCacheProvider implements CacheProvider {

    public static final String DEFAULT = "default";
    public static final String UNNAMED = "";

    @Override
    public <K, V> Cache<K, V> createCache(String name, Class<K> kclass, Class<V> vclass, Properties properties) {
        JCS.setConfigProperties(properties);

        CacheAccess<K, V> cacheAccess = getInstance(name);
        if (cacheAccess == null)
            cacheAccess = getInstance(DEFAULT);
        if (cacheAccess == null)
            cacheAccess = getInstance(UNNAMED);

        return new JCSCache<>(name, cacheAccess, properties);
    }

    private static <K, V> CacheAccess<K, V> getInstance(String name) {
        try {
            return JCS.getInstance(name);
        }
        catch(Throwable t) {
            return null;
        }
    }
}
