package jext.util;

import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;

public class MapUtils {

    // public static <K,V> HashMap<K,V> map() {
    //     return new HashMap<K,V>();
    // }

    public static <K,V> Map<K,V> asMap(K key, V value, Object... args) {
        Map<K,V> map = new HashMap<>();
        return fill(map, key, value, args);
    }

    public static <K,V> Map<K,V> asTreeMap(K v, V value, Object... args) {
        Map<K,V> map = new TreeMap<>();
        return fill(map, v, value, args);
    }

    private static <K, V> Map<K, V> fill(Map<K, V> map, K key, V val, Object[] args) {
        int at = 0;
        map.put(key, val);
        while (at < args.length-1) {
            key = (K)args[at++];
            val = (V)args[at++];

            map.put(key, val);
        }
        return map;
    }

    // public static <K, V> Set<K> toKeys(Set<V> set, BidiMap<K, V> map) {
    //     Set<K> keys = new HashSet<>();
    //     for (V value : set)
    //         keys.add(map.getKey(value));
    //     return keys;
    // }

}
