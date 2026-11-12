package jext.util;

public class UniqueKeyMap<K,V> extends HashMap<K,V> {

    public UniqueKeyMap() {
    }

    @Override
    public V put(K key, V value) {
        if (containsKey(key))
            throw new IllegalStateException("Duplicate key found");
        return super.put(key, value);
    }
}
