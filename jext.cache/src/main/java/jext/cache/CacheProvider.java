package jext.cache;

import java.util.Properties;

public interface CacheProvider {

    <K, V> Cache<K, V> createCache(String name, Properties properties);
}
