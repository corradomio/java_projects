package jext.springframework.cache.guava;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheManagerUtils;
import org.springframework.cache.support.AbstractCacheManager;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Collections;

public class GuavaCacheManager extends AbstractCacheManager {

    @Override
    protected Collection<? extends Cache> loadCaches() {
        return Collections.emptyList();
    }

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
    }

    @Override
    @Nullable
    public Cache getCache(String name) {
        jext.cache.ManagedCache cache = (jext.cache.ManagedCache) jext.cache.CacheManager.getCache(name, Object.class, Object.class);
        return new GuavaCache(name, (com.google.common.cache.Cache)cache.getInnerCache());
    }
}
