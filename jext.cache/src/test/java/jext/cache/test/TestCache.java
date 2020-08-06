package jext.cache.test;

import jext.cache.CacheManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCache {

    @Test
    void createCacheTest() {
        Assertions.assertNotNull(CacheManager.getCache("test", Object.class, Object.class));
    }
}
