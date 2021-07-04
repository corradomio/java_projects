package jext.util;

import java.util.Map;

public interface BidiMap<K, V> extends Map<K, V> {

    K getKey(V v);

    boolean contains(V value);
}
