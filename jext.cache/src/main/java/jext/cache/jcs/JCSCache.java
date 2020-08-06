package jext.cache.jcs;

import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.cache.util.ManagedCache;
import jext.cache.util.Unique;
import org.apache.commons.jcs.access.CacheAccess;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class JCSCache<K, V> implements Cache<K, V>, ManagedCache {

    private String name;
    private CacheManager manager;
    private CacheAccess<K, V> innerCache;
    private Unique<K> uniqueKeys = new Unique<>();

    JCSCache(String name, CacheAccess<K, V> innerCache) {
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
        K unique = uniqueKeys.get(key);
        synchronized (unique) {
            V value = innerCache.get(unique);
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
        uniqueKeys.removeUnique(key);
    }

    @Override
    public void clear() {
        uniqueKeys.clear();
        innerCache.clear();
    }

    @Override
    public void close() {
        manager.removeCache(this);
        innerCache.clear();
    }

    @Override
    public void setManager(CacheManager manager) {
        this.manager = manager;
    }
}
