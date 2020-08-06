package jext.cache.util;

import java.util.HashMap;

public class Unique<K> extends HashMap<K, K> {
    public synchronized K getUnique(K key) {
        if (!containsKey(key))
            super.put(key, key);
        return super.get(key);
    }

    public synchronized void removeUnique(K key) {
        super.remove(key);
    }

}
