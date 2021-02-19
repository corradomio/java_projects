package jext.util;

public interface Map<K, V> extends java.util.Map<K, V> {

    Map<K, V> add(K key, V value);

    Map<K, V> addAll(java.util.Map<? extends K, ? extends V> m);
}
