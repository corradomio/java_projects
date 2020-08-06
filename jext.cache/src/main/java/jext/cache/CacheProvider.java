package jext.cache;

import java.util.Properties;

public interface CacheProvider {

    String CAPACITY = "capacity";
    String EXPIRE_AFTER_ACCESS = "expireAfterAccess";
    String EXPIRE_AFTER_WRITE = "expireAfterWrite";

    /**
     * Create a cache with th specified name and properties
     * Note: the list of properties depends on the cache provider
     *
     * @param name cache name
     * @param properties cache configuration properties
     * @param <K> key type
     * @param <V> value type
     * @return a new cache object
     */
    <K, V> Cache<K, V> createCache(String name, Properties properties);
}
