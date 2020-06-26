package jext.cache.jcs;

import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.cache.util.ConfiguredCache;
import jext.cache.util.Unique;
import org.apache.commons.jcs.access.CacheAccess;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class JCSCache<K, V> implements Cache<K, V>, ConfiguredCache {

    private String name;
    private CacheManager configurator;
    private CacheAccess<K, V> cacheAccess;
    private Unique<K> uniqueKeys = new Unique<>();

    JCSCache(String name, CacheAccess<K, V> cacheAccess) {
        this.name = name;
        this.cacheAccess = cacheAccess;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public V get(K key) {
        return cacheAccess.get(key);
    }

    @Override
    public V get(K key, Callable<V> callable) throws ExecutionException {
        K unique = uniqueKeys.get(key);
        synchronized (unique) {
            V value = cacheAccess.get(key);
            if (value == null)
            try {
                value = callable.call();
                if (value == null)
                    throw new NullPointerException();
                cacheAccess.put(key, value);
            } catch (Exception e) {
                throw new ExecutionException(e);
            }
            return value;
        }
    }

    @Override
    public void put(K key, V value) {
        cacheAccess.put(key, value);
    }

    @Override
    public void remove(K key) {
        cacheAccess.remove(key);
    }

    @Override
    public void clear() {
        cacheAccess.clear();
    }

    @Override
    public void close() {

    }

    @Override
    public void setConfigurator(CacheManager configurator) {
        this.configurator = configurator;
    }
}
