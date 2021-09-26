package jext.util;

import java.util.Set;

public class HashMultimap<K, V> implements Multimap<K, V> {

    private Map<K, Set<V>> map = new HashMap<>();

    @Override
    public void put(K key, V value) {
        if (!map.containsKey(key))
            map.put(key, new HashSet<>());
        map.get(key).add(value);
    }

    @Override
    public void put(K key) {
        if (!map.containsKey(key))
            map.put(key, new HashSet<>());
    }

    @Override
    public Set<V> get(K key) {
        return map.get(key);
    }

    @Override
    public Set<V> getOrDefault(K key, Set<V> defval) {
        return map.getOrDefault(key, defval);
    }

    @Override
    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void clear() {
        map.clear();
    }
}
