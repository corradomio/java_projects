package jext.util.concurrent;

import jext.util.BidiMap;

import java.util.Collection;
import java.util.Set;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentBidiMap<K, V> extends ConcurrentHashMap<K, V> implements BidiMap<K, V> {

    private Map<V, K> map = new ConcurrentHashMap<>();

    @Override
    public K getKey(V v) {
        return map.get(v);
    }

    @Override
    public V put(K key, V value) {
        V ret = super.put(key, value);
        map.put(value, key);
        return ret;
    }

    @Override
    public V remove(Object key) {
        V value = super.remove(key);
        map.remove(value);
        return value;
    }

    @Override
    public void putAll(java.util.Map<? extends K, ? extends V> m) {
        m.forEach(this::put);
    }

    @Override
    public void clear() {
        super.clear();
        map.clear();
    }

}
