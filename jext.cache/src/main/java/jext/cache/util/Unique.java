package jext.cache.util;

import java.util.HashMap;

public class Unique<K> extends HashMap<K, K> {
    public synchronized K getUnique(K key) {
        if (!containsKey(key))
            put(key, key);
        return get(key);
    }
}
