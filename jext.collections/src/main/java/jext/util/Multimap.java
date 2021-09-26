package jext.util;

import java.util.Set;

public interface Multimap<K, V> {

    void put(K key, V value);
    void put(K key);

    Set<V> get(K key);

    Set<V> getOrDefault(K key, Set<V> defval);

    Set<K> keySet();

    boolean containsKey(K key);

    int size();

    void clear();
}
