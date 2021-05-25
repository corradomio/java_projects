package jext.util;

public class HashBidiMap<K, V> extends HashMap<K, V> implements BidiMap<K, V> {

    private Map<V, K> _invmap = new HashMap<V, K>();

    public HashBidiMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public HashBidiMap(int initialCapacity) {
        super(initialCapacity);
    }

    public HashBidiMap() {
        super();
    }


    public HashMap<K, V> putThis(K key, V value) {
        _invmap.put(value, key);
        super.put(key, value);
        return this;
    }

    public V put(K key, V value) {
        _invmap.put(value, key);
        return super.put(key, value);
    }

    public void putAll(java.util.Map<? extends K, ? extends V> m) {
        for (K key : m.keySet()) {
            V value = m.get(key);
            _invmap.put(value, key);
        }
        super.putAll(m);
    }

    public K getKey(V value) {
        return _invmap.get(value);
    }

}
