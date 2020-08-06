package jext.cache.ehcache;

import jext.cache.Cache;
import jext.cache.CacheProvider;
import jext.time.TimeUtils;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.time.Duration;
import java.util.Properties;

public class EHCacheProvider implements CacheProvider {

    private CacheManager cacheManager;

    public EHCacheProvider() {
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        cacheManager.init();
    }

    @Override
    public <K, V> Cache<K, V> createCache(String name, Properties properties) {
        int capacity = Integer.parseInt(properties.getProperty(CAPACITY, "1024"));

        // CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        // cacheManager.init();

        CacheConfigurationBuilder<K, V> cacheConfigurationBuilder = (CacheConfigurationBuilder<K, V>) CacheConfigurationBuilder
                .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(capacity));

        if (properties.containsKey(EXPIRE_AFTER_WRITE)) {
            long millis = TimeUtils.toMillis(properties.getProperty(EXPIRE_AFTER_WRITE));
            cacheConfigurationBuilder.withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMillis(millis)));
        }

        if (properties.containsKey(EXPIRE_AFTER_ACCESS)) {
            long millis = TimeUtils.toMillis(properties.getProperty(EXPIRE_AFTER_ACCESS));
            cacheConfigurationBuilder.withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofMillis(millis)));
        }

        CacheConfiguration<K, V> cacheConfiguration = cacheConfigurationBuilder.build();

        org.ehcache.Cache<K, V> innerCache = cacheManager.createCache(name, cacheConfiguration);
        return new EHCache<>(name, innerCache, this);
    }

    <K, V> void removeCache(EHCache<K, V> cache) {
        cacheManager.removeCache(cache.getName());
    }
}
