package jext.cache;

import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.function.Function;

public interface Cache<K, V> extends AutoCloseable {

    String getId();
    String getName();

    Properties getProperties();

    boolean containsKey(K key);

    /**
     * If the cache doesn't contain the key, the value is computed
     * calling the callable
     * @param key key
     * @param callable used to create the value. The value MUST BE NOT null
     * @return the value
     */
    V get(K key, Callable<V> callable)    /*throws RuntimeException*/;
    V get(K key, Function<K, V> function) /*throws RuntimeException*/;

    /** the value in cache or the defaultValue */
    V getOrDefault(K key, V defaultValue);

    // V getChecked(K key, Callable<V> callable) throws ExecutionException;
    // Optional<V> getIfPresent(K key);

    /** Insert into the cache */
    void put(K key, V value);

    /** Remove from the cache */
    void remove(K key);

    /** Clear the cache */
    void clear();

    /** Destroy the cache */
    void close();
}
