package jext.cache;

import java.util.Properties;

public interface CacheProvider {

    /** Maximum capacity (number of entries) */
    String CAPACITY = "capacity";

    /** Expiry policy based on the first 'put': after the specified timeout */
    String EXPIRE_AFTER_WRITE = "expireAfterWrite";

    /** Expiry policy based on the last 'put'/'get': after the specified timeout */
    String EXPIRE_AFTER_ACCESS = "expireAfterAccess";

    /** If a value is wrapped by a WeakReference (boolean) */
    String WEAK_VALUES = "weakValues";

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
