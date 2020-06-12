package jext.util;

public class MapUtils {

    public static <K,V> HashMap<K,V> map() {
        return new HashMap<K,V>();
    }

    public static <K,V> HashMap<K,V> map(K k, V v, Object... a) {
        HashMap<K,V> map = new HashMap<>();
        map.put_(k, v);
        int at = 0;
        while (at < a.length-1) {
            K key = (K)a[at++];
            V val = (V)a[at++];

            map.put(key, val);
        }
        return map;
    }

    public static <K, V> Set<K> toKeys(Set<V> set, BidiMap<K, V> map) {
        Set<K> keys = new HashSet<>();
        for (V value : set)
            keys.add(map.getKey(value));
        return keys;
    }

}
