package jext.util;

import java.util.Set;

public interface Multimap<K, V> {

    void put(K key, V value);

    Set<V> get(K key);

    Set<V> getOrDefault(K key, Set<V> defval);

    Set<K> keySet();

    int size();

    void clear();
}
