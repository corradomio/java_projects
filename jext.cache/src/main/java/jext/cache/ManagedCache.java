package jext.cache;

import jext.cache.CacheManager;

/**
 * Used internally
 */
public interface ManagedCache<K, V> {

    void setManager(CacheManager manager);

    Object getInnerCache();
}
