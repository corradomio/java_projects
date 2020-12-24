package jext.springframework.cache.guava;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class GuavaCache implements Cache {

    private String name;
    private com.google.common.cache.Cache cache;

    public GuavaCache(String name, com.google.common.cache.Cache cache) {
        this.name = name;
        this.cache = cache;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getNativeCache() {
        return cache;
    }

    @Override
    public ValueWrapper get(Object key) {
        Object value = this.cache.getIfPresent(key);
        return new SimpleValueWrapper(value);
    }

    @Override
    public <T> T get(Object key, Class<T> aClass) {
        Object value = this.cache.getIfPresent(key);
        return aClass.cast(value);
    }

    @Override
    public <T> T get(Object key, Callable<T> callable) {
        try {
            return (T) this.cache.get(key, callable);
        } catch (ExecutionException e) {
            return null;
        }
    }

    @Override
    public void put(Object key, Object value) {
        this.cache.put(key, value);
    }

    @Override
    public void evict(Object key) {
        this.cache.invalidate(key);
    }

    @Override
    public void clear() {

    }
}
