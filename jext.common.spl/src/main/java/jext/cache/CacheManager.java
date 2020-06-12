package jext.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.HashMap;
import java.util.Map;

public class CacheManager {

    private static CacheConfiguration config = new CacheConfiguration();

    private static Map<String, Cache> caches = new HashMap<>();

    public static void configure(CacheConfiguration config) {
        CacheManager.config = config;
    }

    public static synchronized  <K, V> Cache<K, V> getCache(String name) {
        if (caches.containsKey(name))
            return caches.get(name);

        CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder().softValues();
        Cache<K, V> cache = cacheBuilder.build();
        caches.put(name, cache);
        return cache;
    }
}
