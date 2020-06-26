package jext.cache.weak;

import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.cache.util.ConfiguredCache;

import java.util.WeakHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class WeakCache<K, V> implements Cache<K, V>, ConfiguredCache {

    private final String name;
    private CacheManager configurator;
    private WeakHashMap<K, V> innerCache;

    WeakCache(String name, WeakHashMap<K, V> innerCache) {
        this.name = name;
        this.innerCache = innerCache;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public V get(K key) {
        synchronized (innerCache) {
            return innerCache.getOrDefault(key, null);
        }
    }

    @Override
    public V get(K key, Callable<V> callable) throws ExecutionException {
        synchronized (innerCache) {
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
            return innerCache.get(key);
        }
    }

    @Override
    public void put(K key, V value) {
        synchronized (innerCache) {
            innerCache.put(key, value);
        }
    }

    @Override
    public void remove(K key) {
        synchronized (innerCache) {
            innerCache.remove(key);
        }
    }

    @Override
    public void clear() {
        synchronized (innerCache) {
            innerCache.clear();
        }
    }

    @Override
    public void close() {
        clear();
        this.configurator.detach(this);
    }

    @Override
    public void setConfigurator(CacheManager configurator) {
        this.configurator = configurator;
    }
}
