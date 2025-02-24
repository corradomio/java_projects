package jext.util.concurrent;

/*
    Map<Name, BidiMap<Name, String>>
 */

import jext.util.BidiMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentTwoKeysBidiMap<K1, K2, V> extends ConcurrentHashMap<K1, BidiMap<K2, V>> implements Map<K1, BidiMap<K2, V>> {

    public void set(K1 key1) {
        super.put(key1, new ConcurrentBidiMap<>());
    }

    public void put(K1 key1, K2 key2, V value) {
        BidiMap<K2, V> map = super.get(key1);
        map.put(key2, value);
    }

    public V get(K1 key1, K2 key2) {
        BidiMap<K2, V> map = super.get(key1);
        return map.get(key2);
    }

}
