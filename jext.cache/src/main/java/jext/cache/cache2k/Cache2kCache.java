package jext.cache.cache2k;

import jext.cache.Cache;
import jext.cache.CacheConfigurator;
import jext.cache.internal.ConfiguredCache;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

public class Cache2kCache<K, V> implements Cache<K, V>, ConfiguredCache {

    private String name;
    private CacheConfigurator configurator;
    private org.cache2k.Cache<K, V> innerCache;
    private Map<K, K> uniqueKeys = new ConcurrentHashMap<>();

    Cache2kCache(String name, org.cache2k.Cache<K, V> innerCache) {
        this.name = name;
        this.innerCache = innerCache;
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
        key = uniqueKeys.getOrDefault(key, key);
        synchronized (key) {
            if (!innerCache.containsKey(key)) {
                try {
                    V value = callable.call();
                    if (value == null)
                        throw new NullPointerException();
                    innerCache.put(key, value);
                } catch (Exception e) {
                    throw new ExecutionException(e);
                }
            }
        }
        return innerCache.get(key);
    }

    @Override
    public void put(K key, V value) {
        innerCache.put(key, value);
    }

    @Override
    public void remove(K key) {
        innerCache.remove(key);
        uniqueKeys.remove(key);
    }

    @Override
    public void clear() {
        innerCache.removeAll();
        uniqueKeys.clear();
    }

    @Override
    public void close() {
        configurator.detach(this);
        innerCache.close();
    }

    @Override
    public void setConfigurator(CacheConfigurator configurator) {
        this.configurator = configurator;
    }
}
