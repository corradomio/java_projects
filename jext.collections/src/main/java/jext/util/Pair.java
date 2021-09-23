package jext.util;

public class Pair<K, V> implements Map.Entry<K, V>, Comparable<Pair<K, V>> {

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
        Pair<K, V> that = (Pair<K, V>) obj;
        //return this.getClass().isInstance(obj) && key.equals(((Pair)obj).key);
        return this.key.equals(that.key) && this.value.equals(that.value);
    }

    @Override
    public int compareTo(Pair<K, V> that) {
        int cmp = ((Comparable<K>)this.key).compareTo(that.key);
        if (cmp != 0) return cmp;
        return ((Comparable<V>)this.value).compareTo(that.value);
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]", key.toString(), value.toString());
    }

}
