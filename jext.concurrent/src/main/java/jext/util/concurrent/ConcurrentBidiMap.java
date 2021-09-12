package jext.util.concurrent;

import jext.util.BidiMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentBidiMap<K, V> extends ConcurrentHashMap<K, V> implements BidiMap<K, V> {

    private Map<V, K> valueToKey = new ConcurrentHashMap<>();

    public ConcurrentBidiMap() {
        super();
    }

    @Override
    public K getKey(V v) {
        return valueToKey.get(v);
    }

    // @Override
    // public boolean contains(Object v) {
    //     return valueToKey.containsKey((V) v);
    // }

    @Override
    public boolean containsValue(Object value) {
        return valueToKey.containsKey(value);
    }

    @Override
    public V put(K key, V value) {
        V ret = super.put(key, value);
        valueToKey.put(value, key);
        return ret;
    }

    @Override
    public void putAll(java.util.Map<? extends K, ? extends V> m) {
        m.forEach(this::put);
    }

    @Override
    public V remove(Object key) {
        V value = super.remove(key);
        valueToKey.remove(value);
        return value;
    }

    @Override
    public void clear() {
        super.clear();
        valueToKey.clear();
    }
}
