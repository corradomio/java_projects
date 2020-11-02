package jext.cache.access;

import jext.cache.Cache;
import jext.cache.util.ManagedCache;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class AccessCache<K, V> implements Cache<K, V> {

    private long lastAccess;
    private Cache<K, V> innerCache;

    public AccessCache(Cache<K, V> innerCache) {
        this.innerCache = innerCache;
    }

    @Override
    public String getName() {
        return innerCache.getName();
    }

    @Override
    public Optional<V> getIfPresent(K key) {
        lastAccess = System.currentTimeMillis();
        return innerCache.getIfPresent(key);
    }

    @Override
    public V getChecked(K key, Callable<V> callable) throws ExecutionException {
        lastAccess = System.currentTimeMillis();
        return innerCache.getChecked(key, callable);
    }

    @Override
    public V get(K key, Callable<V> callable) {
        lastAccess = System.currentTimeMillis();
        return innerCache.get(key, callable);
    }

    @Override
    public V get(K key, Function<K, V> function) {
        lastAccess = System.currentTimeMillis();
        return innerCache.get(key, function);
    }

    @Override
    public void put(K key, V value) {
        lastAccess = System.currentTimeMillis();
        innerCache.put(key, value);
    }

    @Override
    public void remove(K key) {
        lastAccess = System.currentTimeMillis();
        innerCache.remove(key);
    }

    @Override
    public void clear() {
        lastAccess = System.currentTimeMillis();
        innerCache.clear();
    }

    @Override
    public void close() {
        innerCache.close();
    }
}
