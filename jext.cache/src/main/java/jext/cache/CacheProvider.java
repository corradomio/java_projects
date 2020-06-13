package jext.cache;

import java.util.Properties;

public interface CacheProvider {

    /**
     * Create a cache with th specified name and properties
     * Note: the list of properties depends on the cache provider
     *
     * @param name
     * @param properties
     * @param <K>
     * @param <V>
     * @return
     */
    <K, V> Cache<K, V> createCache(String name, Properties properties);
}
