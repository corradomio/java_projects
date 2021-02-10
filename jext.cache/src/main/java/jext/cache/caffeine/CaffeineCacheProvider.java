package jext.cache.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import jext.cache.Cache;
import jext.cache.CacheProvider;
import jext.time.TimeUtils;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class CaffeineCacheProvider implements CacheProvider {

    @Override
    public <K, V> Cache<K, V> createCache(String name, Class<K> kclass, Class<V> vclass, Properties properties) {

        Caffeine<Object, Object> cacheBuilder = Caffeine.newBuilder();

        if (properties.containsKey(CAPACITY)) {
            cacheBuilder.maximumSize(Long.parseLong(properties.getProperty(CAPACITY)));
        }
        if (properties.containsKey(EXPIRE_AFTER_WRITE)) {
            long millis = TimeUtils.toMillis(properties.getProperty(EXPIRE_AFTER_WRITE));
            cacheBuilder.expireAfterWrite(millis, TimeUnit.MILLISECONDS);
        }
        if (properties.containsKey(EXPIRE_AFTER_ACCESS)) {
            long millis = TimeUtils.toMillis(properties.getProperty(EXPIRE_AFTER_ACCESS));
            cacheBuilder.expireAfterAccess(millis, TimeUnit.MILLISECONDS);
        }
        if (properties.containsKey(WEAK_VALUES)) {
            boolean weakValues = Boolean.parseBoolean(properties.getProperty(WEAK_VALUES));
            if (weakValues)
                cacheBuilder.weakValues();
        }

        com.github.benmanes.caffeine.cache.Cache<K, V> innerCache = cacheBuilder.build();

        return new CaffeineCache<K, V>(name, innerCache, properties);
    }
}
