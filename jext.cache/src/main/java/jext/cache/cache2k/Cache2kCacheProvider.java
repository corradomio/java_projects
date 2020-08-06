package jext.cache.cache2k;

import jext.cache.Cache;
import jext.cache.CacheProvider;
import jext.util.PropertiesUtils;
import jext.time.TimeUtils;
import org.cache2k.Cache2kBuilder;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Cache2kCacheProvider implements CacheProvider {

    @Override
    public <K, V> Cache<K, V> createCache(String name, Properties properties) {
        Cache2kBuilder<K, V> builder = Cache2kBuilder.forUnknownTypes();

        if (properties.containsKey(CAPACITY)) {
            long capacity = PropertiesUtils.getInt(properties, CAPACITY, 128);
            builder.entryCapacity(capacity);
        }
        if (properties.containsKey(EXPIRE_AFTER_WRITE)) {
            long duration = TimeUtils.toMillis(PropertiesUtils.getString(properties, EXPIRE_AFTER_WRITE));
            builder.expireAfterWrite(duration, TimeUnit.MILLISECONDS);
        }

        org.cache2k.Cache<K, V> innerCache = builder.build();

        return new Cache2kCache<>(name, innerCache);
    }
}
