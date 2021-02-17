package jext.cache.caffeine;

import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.cache.ManagedCache;

import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class CaffeineCache<K, V> implements Cache<K, V>, ManagedCache<K, V> {

    private String name;
    private com.github.benmanes.caffeine.cache.Cache<K, V> innerCache;
    private Properties properties;
    private CacheManager manager;

    CaffeineCache(String name, com.github.benmanes.caffeine.cache.Cache<K, V> innerCache, Properties properties) {
        this.name = name;
        this.innerCache = innerCache;
        this.properties = properties;
    }

    @Override
    public String getId() {
        return Integer.toHexString(name.hashCode());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Properties getProperties() {
        return this.properties;
    }

    @Override
    public long size() {
        return innerCache.stats().loadCount();
    }

    @Override
    public boolean containsKey(K key) {
        return innerCache.getIfPresent(key) != null;
    }

    // @Override
    // public V getOrDefault(K key, V defaultValue) {
    //     V value = innerCache.getIfPresent(key);
    //     return value != null ? value : defaultValue;
    // }

    // @Override
    // public Optional<V> getIfPresent(K key) {
    //     V value = innerCache.getIfPresent(key);
    //     return Optional.ofNullable(value);
    // }

    @Override
    public V get(K key) {
        return innerCache.getIfPresent(key);
    }

    @Override
    public V getChecked(K key, Callable<V> callable) throws ExecutionException {
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
    public V get(K key, Callable<V> callable) {
        try {
            return getChecked(key, callable);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    // @Override
    // public V get(K key, Function<K, V> function) {
    //     try {
    //         return getChecked(key, () -> function.apply(key));
    //     } catch (ExecutionException e) {
    //         throw new RuntimeException(e);
    //     }
    // }

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

    @Override
    public Object getInnerCache() {
        return innerCache;
    }
}
