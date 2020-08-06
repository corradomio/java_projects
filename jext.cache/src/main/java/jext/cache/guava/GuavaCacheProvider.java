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
    public <K, V> Cache<K, V> createCache(String name, Class<K> kclass, Class<V> vclass, Properties properties) {
        CacheBuilder<K, V> builder = (CacheBuilder<K, V>)CacheBuilder.newBuilder();

        if(properties.containsKey(CAPACITY)) {
            long capacity = PropertiesUtils.getInt(properties, CAPACITY, 128);
            builder.maximumSize(capacity);
        }
        if (properties.containsKey(EXPIRE_AFTER_ACCESS)) {
            long duration = TimeUtils.toMillis(PropertiesUtils.getString(properties, EXPIRE_AFTER_ACCESS));
            builder.expireAfterAccess(duration, TimeUnit.MILLISECONDS);
        }
        if (properties.containsKey(EXPIRE_AFTER_WRITE)) {
            long duration = TimeUtils.toMillis(PropertiesUtils.getString(properties, EXPIRE_AFTER_WRITE));
            builder.expireAfterWrite(duration, TimeUnit.MILLISECONDS);
        }
        if (properties.containsKey(WEAK_VALUES)) {
            boolean weakValues = Boolean.parseBoolean(properties.getProperty(WEAK_VALUES));
            if (weakValues)
                builder.weakValues();
        }

        // concurrencyLevel(int)
        // weakValues()
        // softValues()
        // refreshAfterWrite(timeout)

        com.google.common.cache.Cache<K, V> innerCache = builder.build();

        return new GuavaCache<>(name, innerCache);
    }
}
