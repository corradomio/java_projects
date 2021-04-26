package jext.cache.cache2k;

import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.cache.ManagedCache;
import jext.cache.util.Unique;

import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class Cache2kCache<K, V> implements Cache<K, V>, ManagedCache<K, V> {

    private long lastAccess;
    private final String name;
    private CacheManager manager;
    private final org.cache2k.Cache<K, V> innerCache;
    private final Unique<K> uniqueKeys = new Unique<>();
    private final Properties properties;

    Cache2kCache(String name, org.cache2k.Cache<K, V> innerCache, Properties properties) {
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
        return -1;
    }

    @Override
    public boolean containsKey(K key) {
        return innerCache.containsKey(key);
    }

    // @Override
    // public V getOrDefault(K key, V defaultValue) {
    //     V value = innerCache.get(key);
    //     return value != null ? value : defaultValue;
    // }

    // @Override
    // public Optional<V> getIfPresent(K key) {
    //     V value = innerCache.get(key);
    //     return Optional.ofNullable(value);
    // }

    @Override
    public V get(K key) {
        return innerCache.get(key);
    }

    @Override
    public V getChecked(K key, Callable<V> callable) throws ExecutionException {
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
        innerCache.remove(key);
        uniqueKeys.removeUnique(key);
    }

    @Override
    public void clear() {
        innerCache.removeAll();
        uniqueKeys.clear();
    }

    @Override
    public void close() {
        manager.removeCache(this);
        innerCache.close();
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
