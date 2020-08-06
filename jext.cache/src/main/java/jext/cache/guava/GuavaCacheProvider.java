package jext.cache.guava;

import com.google.common.cache.CacheBuilder;
import jext.cache.Cache;
import jext.cache.CacheProvider;
import jext.util.PropertiesUtils;
import jext.time.TimeUtils;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class GuavaCacheProvider implements CacheProvider {

    @Override
    public <K, V> Cache<K, V> createCache(String name, Properties properties) {
        CacheBuilder<?,?> builder = CacheBuilder.newBuilder();

        if(properties.contains(CAPACITY)) {
            long capacity = PropertiesUtils.getInt(properties, CAPACITY, 128);
            builder.maximumSize(capacity);
        }
        if (properties.contains(EXPIRE_AFTER_ACCESS)) {
            long duration = TimeUtils.toMillis(PropertiesUtils.getString(properties, EXPIRE_AFTER_ACCESS));
            builder.expireAfterAccess(duration, TimeUnit.MILLISECONDS);
        }
        if (properties.contains(EXPIRE_AFTER_WRITE)) {
            long duration = TimeUtils.toMillis(PropertiesUtils.getString(properties, EXPIRE_AFTER_WRITE));
            builder.expireAfterWrite(duration, TimeUnit.MILLISECONDS);
        }

        com.google.common.cache.Cache<?,?> innerCache = builder.build();

        return new GuavaCache(name, innerCache);
    }
}
