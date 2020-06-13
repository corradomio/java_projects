package jext.cache.weak;

import jext.cache.Cache;
import jext.cache.CacheProvider;

import java.util.Properties;

public class WeakCacheProvider implements CacheProvider {
    @Override
    public <K, V> Cache<K, V> createCache(String name, Properties properties) {
        return new WeakCache(name);
    }
}
