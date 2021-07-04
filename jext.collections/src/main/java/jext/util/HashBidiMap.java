package jext.util;

public class HashBidiMap<K, V> extends HashMap<K, V> implements BidiMap<K, V> {

    private Map<V, K> valueToKey = new HashMap<V, K>();

    public HashBidiMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public HashBidiMap(int initialCapacity) {
        super(initialCapacity);
    }

    public HashBidiMap() {
        super();
    }

    @Override
    public HashMap<K, V> putThis(K key, V value) {
        valueToKey.put(value, key);
        super.put(key, value);
        return this;
    }

    @Override
    public V put(K key, V value) {
        valueToKey.put(value, key);
        return super.put(key, value);
    }

    @Override
    public void putAll(java.util.Map<? extends K, ? extends V> m) {
        for (K key : m.keySet()) {
            V value = m.get(key);
            valueToKey.put(value, key);
        }
        super.putAll(m);
    }

    @Override
    public K getKey(V value) {
        return valueToKey.get(value);
    }

    @Override
    public boolean contains(V value) {
        return valueToKey.containsKey(value);
    }

    @Override
    public boolean containsValue(Object value) {
        return contains((V) value);
    }

}
