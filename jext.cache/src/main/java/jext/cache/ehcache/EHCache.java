package jext.cache.ehcache;

import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.cache.ManagedCache;
import jext.cache.util.Unique;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class EHCache<K, V> implements Cache<K, V>, ManagedCache {

    private String name;
    private CacheManager manager;
    private EHCacheProvider provider;
    private org.ehcache.Cache<K, V> innerCache;
    private Unique<K> uniqueKeys = new Unique<>();

    EHCache(String name, org.ehcache.Cache<K, V> innerCache, EHCacheProvider provider) {
        this.name = name;
        this.innerCache = innerCache;
        this.provider = provider;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Optional<V> getIfPresent(K key) {
        V value = innerCache.get(key);
        return Optional.ofNullable(value);
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
        innerCache.remove(key);
    }

    @Override
    public void clear() {
        innerCache.clear();
    }

    @Override
    public void close() {
        provider.removeCache(this);
        manager.removeCache(this);
        innerCache.clear();
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
