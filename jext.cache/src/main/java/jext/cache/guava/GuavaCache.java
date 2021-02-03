package jext.cache.guava;

import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.cache.ManagedCache;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class GuavaCache<K, V> implements Cache<K, V>, ManagedCache<K, V> {

    private final String name;
    private CacheManager manager;
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
    public boolean containsKey(K key) {
        return innerCache.getIfPresent(key) != null;
    }

    @Override
    public V getOrDefault(K key, V defaultValue) {
        V value = innerCache.getIfPresent(key);
        return value != null ? value : defaultValue;
    }

    // @Override
    // public Optional<V> getIfPresent(K key) {
    //     V value = (V) innerCache.getIfPresent(key);
    //     return Optional.ofNullable(value);
    // }

    // @Override
    public V getChecked(K key, Callable<V> callable) throws ExecutionException {
        return (V) innerCache.get(key, callable);
    }

    @Override
    public V get(K key, Callable<V> callable) {
        try {
            return (V) innerCache.get(key, callable);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public V get(K key, Function<K, V> function) {
        try {
            return getChecked(key, () -> function.apply(key));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
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
    }

    @Override
    public void close() {
        manager.removeCache(this);
        innerCache.cleanUp();
    }

    @Override
    public void setManager(CacheManager manager) {
        this.manager = manager;
    }

    @Override
    public Object getInnerCache() {
        return innerCache;
    }
}
