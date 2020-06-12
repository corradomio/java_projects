package jext.util;

public interface Map<K, V> extends java.util.Map<K, V> {

    Map<K, V> put_(K key, V value);

    Map<K, V> putAll_(java.util.Map<? extends K, ? extends V> m);
}
