package jext.util;

public interface Map<K, V> extends java.util.Map<K, V> {

    Map<K, V> putThis(K key, V value);

    Map<K, V> putAllThis(java.util.Map<? extends K, ? extends V> m);
}
