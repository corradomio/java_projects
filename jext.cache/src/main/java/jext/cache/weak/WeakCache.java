package jext.cache.weak;

import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.cache.ManagedCache;

import java.util.Optional;
import java.util.WeakHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class WeakCache<K, V> implements Cache<K, V>, ManagedCache {

    private final String name;
    private CacheManager manager;
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
    public Optional<V> getIfPresent(K key) {
        synchronized (innerCache) {
            V value = innerCache.getOrDefault(key, null);
            return Optional.ofNullable(value);
        }
    }

    @Override
    public V getChecked(K key, Callable<V> callable) throws ExecutionException {
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
