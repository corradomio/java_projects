package jext.cache.weak;

import jext.cache.Cache;
import jext.cache.CacheProvider;

import java.util.Properties;
import java.util.WeakHashMap;

public class WeakCacheProvider implements CacheProvider {

    @Override
    public <K, V> Cache<K, V> createCache(String name, Class<K> kclass, Class<V> vclass, Properties properties) {
        WeakHashMap<K, V> innerCache = new WeakHashMap<>();
        return new WeakCache<>(name, innerCache, properties);
    }
}
