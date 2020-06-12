package jext.util;

import java.util.Set;

public class ConcurrentHashMultimap<K, V> extends HashMultimap<K, V> {
    @Override
    public synchronized void put(K key, V value) {
        super.put(key, value);
    }

    @Override
    public synchronized Set<V> get(K key) {
        return super.get(key);
    }

    @Override
    public synchronized Set<V> getOrDefault(K key, Set<V> defval) {
        return super.getOrDefault(key, defval);
    }


    @Override
    public synchronized Set<K> keySet() {
        return new HashSet<>(super.keySet());
    }

}
