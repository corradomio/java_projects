package jext.cache;

/**
 * Used internally
 */
public interface ManagedCache<K, V> {

    void setManager(CacheManager manager);

    Object getInnerCache();
}
