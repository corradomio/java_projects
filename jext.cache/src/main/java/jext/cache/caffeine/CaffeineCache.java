package jext.cache.caffeine;

import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.cache.util.ManagedCache;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class CaffeineCache<K, V> implements Cache<K, V>, ManagedCache {

    private String name;
    private com.github.benmanes.caffeine.cache.Cache<K, V> innerCache;
    private CacheManager manager;

    CaffeineCache(String name, com.github.benmanes.caffeine.cache.Cache<K, V> innerCache) {
        this.name = name;
        this.innerCache = innerCache;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public V get(K key) {
        return innerCache.getIfPresent(key);
    }

    @Override
    public V get(K key, Callable<V> callable) throws ExecutionException {
        try {
            return innerCache.get(key, k -> {
                try {
                    V value = callable.call();
                    if (value == null)
                        throw new NullPointerException();
                    return value;
                } catch (Exception e) {
                    throw new CaffeineError(e);
                }
            });
        }
        catch (CaffeineError e) {
            throw new ExecutionException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void put(K key, V value) {
        innerCache.put(key, value);
    }

    @Override
    public void remove(K key) {
        innerCache.invalidate(key);
    }

    @Override
    public void clear() {
        innerCache.invalidateAll();
        innerCache.cleanUp();
    }

    @Override
    public void close() {
        manager.removeCache(this);
        innerCache.invalidateAll();
        innerCache.cleanUp();
    }

    @Override
    public void setManager(CacheManager manager) {
        this.manager = manager;
    }
}
