package jext.cache.imcache;

import com.cetsoft.imcache.cache.builder.HeapCacheBuilder;
import jext.cache.Cache;
import jext.cache.CacheProvider;
import com.cetsoft.imcache.cache.builder.CacheBuilder;
import jext.time.TimeUtils;

import java.util.Properties;

public class IMCacheProvider implements CacheProvider {

    @Override
    public <K, V> Cache<K, V> createCache(String name, Class<K> kclass, Class<V> vclass, Properties properties) {
        HeapCacheBuilder cacheBulder = CacheBuilder.heapCache();

        if (properties.containsKey(CAPACITY)) {
            cacheBulder.capacity(Integer.parseInt(properties.getProperty(CAPACITY)));
        }
        if (properties.containsKey(EXPIRE_AFTER_WRITE)) {
            cacheBulder.expiry(TimeUtils.toMillis(properties.getProperty(EXPIRE_AFTER_WRITE)));
        }

        com.cetsoft.imcache.cache.Cache<K, V> innerCache = cacheBulder.build(name);
        return new IMCache<>(name, innerCache);
    }
}
