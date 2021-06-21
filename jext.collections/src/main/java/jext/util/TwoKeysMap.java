package jext.util;

public interface TwoKeysMap<K1, K2, V> extends Map<K1, Map<K2, V>> {

    void set(K1 key1);

    void put(K1 key1, K2 key2, V value);
    V get(K1 key, K2 key2);
}
