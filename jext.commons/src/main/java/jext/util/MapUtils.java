package jext.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapUtils {

    public static <K, V> Map<K, V> asMap(Object ... args) {
        return fillMap(new HashMap<>(), args);
    }

    public static <K, V> Map<K, V> asTreeMap(Object ... args) {
        return fillMap(new TreeMap<>(), args);
    }

    // public static <K, V> Map<K, V> asLinkedMap(Object ... args) {
    //     return fillMap(new LinkedHashMap<>(), args);
    // }

    private static <K, V> Map<K, V> fillMap(Map<K, V> map, Object[] args) {
        if (args == null || args.length == 0)
            return Collections.emptyMap();

        int n = args.length - (args.length%2);
        for (int i=0; i<n; i+=2) {
            K key = (K) args[i];
            V value = (V) args[i+1];

            map.put(key, value);
        }

        return map;
    }

}
