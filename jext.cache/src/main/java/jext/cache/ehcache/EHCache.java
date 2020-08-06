package jext.cache.ehcache;

import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.cache.util.ManagedCache;
import jext.cache.util.Unique;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class EHCache<K, V> implements Cache<K, V>, ManagedCache {

    private String name;
    private CacheManager manager;
    private EHCacheProvider provider;
    private org.ehcache.Cache<K, V> innerCache;
    private Unique<K> uniqueKeys = new Unique<>();

    EHCache(String name, org.ehcache.Cache<K, V> innerCache, EHCacheProvider provider) {
        this.name = name;
        this.innerCache = innerCache;
        this.provider = provider;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public V get(K key) {
        return innerCache.get(key);
    }

    @Override
    public V get(K key, Callable<V> callable) throws ExecutionException {
        V value = innerCache.get(key);
        if (value != null)
            return value;

        K unique = uniqueKeys.getUnique(key);
        synchronized (unique) {
            value = innerCache.get(unique);
            if (value == null)
                try {
                    value = callable.call();
                    if (value == null)
                        throw new NullPointerException();
                    innerCache.put(unique, value);
                } catch (Exception e) {
                    throw new ExecutionException(e);
                }
            return value;
        }
    }

    @Override
    public void put(K key, V value) {
        innerCache.put(key, value);
    }

    @Override
    public void remove(K key) {
        innerCache.remove(key);
    }

    @Override
    public void clear() {
        innerCache.clear();
    }

    @Override
    public void close() {
        provider.removeCache(this);
        manager.detach(this);
        innerCache.clear();
    }

    @Override
    public void setManager(CacheManager manager) {
        this.manager = manager;
    }
}
