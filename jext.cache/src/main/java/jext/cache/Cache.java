package jext.cache;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public interface Cache<K, V> extends AutoCloseable {

    /** name of the cache */
    String getName();

    /** The value in the cache, or null */
    V get(K key);

    /**
     * If the cache doesn't contain the key, the value is computed
     * calling the callable
     * @param key key
     * @param callable used to create the value. It CAN BE NOT null
     * @return the value
     * @throws ExecutionException
     */
    V get(K key, Callable<V> callable) throws ExecutionException;

    /** Insert in the cache */
    void put(K key, V value);

    /** Remove fom the cache */
    void remove(K key);

    /** Clear the cache */
    void clear();

    /** Destroy the cache */
    void close();
}
