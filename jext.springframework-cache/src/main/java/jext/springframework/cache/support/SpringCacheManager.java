package jext.springframework.cache.support;

import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.cache.support.SimpleCacheManager;


public class SpringCacheManager {

    SimpleCacheManager scm;
    EhCacheCacheManager ehcm;
    CaffeineCacheManager ccm;
    // GuavaCacheManager gcm; deprecated
    JCacheCacheManager jccm;
}
