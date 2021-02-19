package jext.util;

public class HashMap<K, V> extends java.util.HashMap<K, V> implements Map<K, V> {

    public HashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public HashMap(int initialCapacity) {
        super(initialCapacity);
    }

    public HashMap() {
        super();
    }

    public HashMap(Map<? extends K, ? extends V> m) {
        super(m);
    }

    public HashMap<K, V> add(K key, V value) {
        super.put(key, value);
        return this;
    }

    public HashMap<K, V> addAll(java.util.Map<? extends K, ? extends V> m) {
        super.putAll(m);
        return this;
    }

}
