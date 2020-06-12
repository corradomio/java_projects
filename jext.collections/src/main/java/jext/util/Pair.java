package jext.util;

public class Pair<K, V> implements Map.Entry<K, V> {

    private K key;
    private V value;

    public Pair(K k, V v) {
        key = k;
        value = v;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V nvalue) {
        V ovalue = value;
        value = nvalue;
        return ovalue;
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.getClass().isInstance(obj) && key.equals(((Pair)obj).key);
    }

    @Override
    public String toString() {
        return String.format("%s: %s", key.toString(), value.toString());
    }
}
