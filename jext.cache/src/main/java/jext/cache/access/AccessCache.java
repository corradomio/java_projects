package jext.cache.access;

import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.cache.ManagedCache;

import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class AccessCache<K, V> implements Cache<K, V>, ManagedCache<K, V> {

    private long lastAccess;
    private Cache<K, V> innerCache;

    public AccessCache(Cache<K, V> innerCache) {
        this.innerCache = innerCache;
    }

    @Override
    public String getId() {
        return this.innerCache.getId();
    }

    @Override
    public String getName() {
        return this.innerCache.getName();
    }

    @Override
    public Properties getProperties() {
        return this.innerCache.getProperties();
    }

    @Override
    public long size() {
        return innerCache.size();
    }

    // @Override
    // public Optional<V> getIfPresent(K key) {
    //     lastAccess = System.currentTimeMillis();
    //     return innerCache.getIfPresent(key);
    // }

    // @Override
    // public V getOrDefault(K key, V defaultValue) {
    //     lastAccess = System.currentTimeMillis();
    //     return innerCache.getOrDefault(key, defaultValue);
    // }

    @Override
    public boolean containsKey(K key) {
        lastAccess = System.currentTimeMillis();
        return innerCache.containsKey(key);
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

    // @Override
    // public V get(K key, Function<K, V> function) {
    //     lastAccess = System.currentTimeMillis();
    //     return innerCache.get(key, function);
    // }

    @Override
    public V get(K key) {
        return innerCache.get(key);
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

    @Override
    public void setManager(CacheManager manager) {
        ((ManagedCache)innerCache).setManager(manager);
    }

    @Override
    public Object getInnerCache() {
        return ((ManagedCache)innerCache).getInnerCache();
    }
}
