package jext.util;

public interface BidiMap<K, V> extends Map<K, V> {

    K getKey(V v);
}
