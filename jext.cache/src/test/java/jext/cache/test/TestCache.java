package jext.cache.test;

import jext.cache.Cache;
import jext.cache.CacheManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

public class TestCache {

    @BeforeAll
    static void setUp() {
        CacheManager.configure();
    }

    @Test
    void createCacheTest() {
        Assertions.assertNotNull(CacheManager.getCache("test", Object.class, Object.class));
        Assertions.assertNotNull(CacheManager.getCache("test.first", Object.class, Object.class));
    }

    @Test
    void testCacheAccess() throws ExecutionException {
        Cache<Integer, Integer> cache = CacheManager.getCache("ii", Integer.class, Integer.class);

        Assertions.assertNull(cache.getOrDefault(1, null));
        Assertions.assertNotNull(cache.get(2, () -> 22));

        cache.put(3, 33);
        Assertions.assertNotNull(cache.getOrDefault(3, null));
        Assertions.assertEquals(33, cache.getOrDefault(3, null));
    }
}
