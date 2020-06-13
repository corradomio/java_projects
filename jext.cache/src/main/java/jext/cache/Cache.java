package jext.cache;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public interface Cache<K, V> extends AutoCloseable {

    String getName();

    V get(K key);

    V get(K key, Callable<V> callable) throws ExecutionException;

    void put(K key, V value);

    void evict(K key);

    void evictAll();
}
