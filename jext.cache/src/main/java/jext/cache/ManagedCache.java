package jext.cache.util;

import jext.cache.CacheManager;

/**
 * Used internally
 */
public interface ManagedCache<K, V> {

    void setManager(CacheManager manager);
}
