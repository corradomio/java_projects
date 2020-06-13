package jext.cache.guava;

import jext.cache.Cache;
import jext.cache.CacheConfigurator;
import jext.cache.util.ConfiguredCache;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class GuavaCache<K, V> implements Cache<K, V>, ConfiguredCache {

    private final String name;
    private CacheConfigurator configurator;
    private final com.google.common.cache.Cache<K,V> innerCache;

    GuavaCache(String name, com.google.common.cache.Cache<K, V> innerCache) {
        this.name = name;
        this.innerCache = innerCache;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public V get(K key) {
        return (V) innerCache.getIfPresent(key);
    }

    @Override
    public V get(K key, Callable<V> callable) throws ExecutionException {
        return (V) innerCache.get(key, callable);
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
    }

    @Override
    public void close() {
        configurator.detach(this);
        innerCache.cleanUp();
    }

    @Override
    public void setConfigurator(CacheConfigurator configurator) {
        this.configurator = configurator;
    }
}
