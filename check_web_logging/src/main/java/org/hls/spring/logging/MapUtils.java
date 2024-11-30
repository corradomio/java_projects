package org.hls.spring.logging;

import java.util.*;

public class MapUtils {

    public static <K, V> Map<K, V> asMap(Object ... args) {
        return putAll(new HashMap<>(), args);
    }

    public static <K extends Comparable<K>, V> Map<K, V> asTreeMap(Object ... args) {
        return putAll(new TreeMap<>(), args);
    }

    public static <K, V> Map<K, V> asLinkedMap(Object ... args) {
        return putAll(new LinkedHashMap<>(), args);
    }

    public static <K, V> Map<K, V> putAll(Map<K, V> map, Object ... args) {
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

    public static <E> E get(Map<String, Object> map, String... keys) {
        return getDefault(map, null, keys);
    }

    public static <E> E getDefault(Map<String, Object> map, E defaultValue, String... keys) {
        if (map == null)
            return defaultValue;
        if (keys == null || keys.length == 0)
            return defaultValue;

        int l = keys.length - 1;
        Map<String, Object> cmap = map;
        for (int i=0; i<l && cmap != null; ++i)
            cmap = (Map<String, Object>) cmap.get(keys[i]);
        return cmap != null ? (E)cmap.get(keys[l]) : defaultValue;
    }

    public static Object put(Map<String, Object> map, Object ... keysValue) {
        if (keysValue == null || keysValue.length == 0)
            return map;

        Object[] keys = keysValue;
        int v = keysValue.length - 1;
        int l = v - 1;
        Map<String, Object> cmap = map;
        for (int i=0; i<l && cmap != null; ++i) {
            if (!cmap.containsKey((String)keys[i]))
                cmap.put((String)keys[i], new HashMap<>());
            cmap = (Map<String, Object>) cmap.get(keys[i]);
        }
        return cmap != null ? cmap.put((String)keys[l], keysValue[v]) : null;
    }

}
