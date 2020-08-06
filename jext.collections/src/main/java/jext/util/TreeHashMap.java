package jext.util;

import java.util.AbstractMap;
import java.util.Set;

public class TreeHashMap<K extends Splittable, V> extends AbstractMap<K, V> {

    private static class Node<K, V> /*implements java.util.Map.Entry<K, V>*/ {
        // Node<K, V> parent;
        // K key;
        V value;
        Map<String, Node<K, V>> children = new HashMap<>();

        Node(Node<K,V> parent) {
            // this.parent = parent;
        }

        // @Override
        // public K getKey() {
        //     return key;
        // }
        //
        // @Override
        // public V getValue() {
        //     return value;
        // }
        //
        // @Override
        // public V setValue(V value) {
        //     V oldv = this.value;
        //     this.value = value;
        //     return oldv;
        // }

        Node<K, V> getNode(String name) {
            if (!children.containsKey(name))
                children.put(name, new Node<>(this));
            return children.get(name);
        }

        int count() {
            int count = 0;
            for(Node<K, V> child : children.values())
                count += child.count();
            return count + (value != null ? 1 : 0);
        }

        // public boolean equals(Object o) {
        //     if (!(o instanceof Entry))
        //         return false;
        //     Entry<?,?> e = (Entry<?,?>)o;
        //     return Objects.equals(key,e.getKey()) && Objects.equals(value,e.getValue());
        // }

        // public int hashCode() {
        //     int keyHash = (key==null ? 0 : key.hashCode());
        //     int valueHash = (value==null ? 0 : value.hashCode());
        //     return keyHash ^ valueHash;
        // }

        // public String toString() {
        //     return key + "=" + value;
        // }
    }

    private Node<K, V> root = new Node<>(null);


    public TreeHashMap() {

    }

    @Override
    public int size() {
        return root.count();
    }

    @Override
    public boolean isEmpty() {
        return root.children.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return getNode((Splittable) key).value != null;
    }

    @Override
    public V get(Object key) {
        Node<K, V> node = getNode((Splittable) key);
        return node.value;
    }

    @Override
    public V put(K key, V value) {
        Node<K, V> node = getNode((K)key);
        V oldv = node.value;
        node.value = value;
        return oldv;
    }

    @Override
    public V remove(Object key) {
        Node<K, V> node = getNode((Splittable) key);
        V oldv = node.value;
        node.value = null;
        return oldv;
    }

    @Override
    public void clear() {
        root = new Node<>(null);
    }

    protected Node<K, V> getNode(Splittable key) {
        String[] parts = key.split();
        Node<K, V> current = root;
        for(String part : parts)
            current = current.getNode(part);
        return current;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

}
