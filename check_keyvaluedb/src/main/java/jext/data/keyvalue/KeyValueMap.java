package jext.data.keyvalue;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.Function;

public interface KeyValueMap<K,V> {

    boolean containsKey(K key);
    void put(K key, V value);

    /** The value in the cache, or null */
    Optional<V> getIfPresent(K key);

    V get(K key);

    V getOrDefault(K key, V defaultValue);

    V get(K key, Callable<V> callable);
    V get(K key, Function<K, V> function);
}
