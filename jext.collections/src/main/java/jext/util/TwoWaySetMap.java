package jext.util;

public class TwoWaySetMap<K, V> {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private Map<K, Set<V>> keyValueMap = new HashMap<>();
    private Map<V, Set<K>> valueKeyMap = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public TwoWaySetMap() { }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public void put(K key, V value) { put_(key, value); }

    public TwoWaySetMap put_(K key, V value) {
        if (!keyValueMap.containsKey(key))
            keyValueMap.put(key, new HashSet<>());
        keyValueMap.get(key).add(value);

        if (!valueKeyMap.containsKey(value))
            valueKeyMap.put(value, new HashSet<>());
        valueKeyMap.get(value).add(key);

        return this;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public Set<K> keySet() { return new HashSet<>(keyValueMap.keySet()); }

    public Set<V> valueSet() { return new HashSet<>(valueKeyMap.keySet()); }

    public Set<V> getValues(K key) { return keyValueMap.get(key); }

    public Set<K> getKeys(V value) { return valueKeyMap.get(value); }

}
