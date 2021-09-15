package jext.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapUtils {

    public static <K, V> Map<K, V> asMap(Object ... args) {
        return fillMap(new HashMap<>(), args);
    }

    public static <K, V> Map<K, V> asTreeMap(Object ... args) {
        return fillMap(new TreeMap<>(), args);
    }

    public static <K, V> Map<K, V> asLinkedMap(Object ... args) {
        return fillMap(new LinkedHashMap<>(), args);
    }

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

    // ----------------------------------------------------------------------

    public static <E> E get(Map<String, Object> map, String... keys) {
        if (keys == null || keys.length == 0)
            return (E)map;

        int l = keys.length - 1;
        Map<String, Object> cmap = map;
        for (int i=0; i<l; ++i)
            cmap = (Map<String, Object>) cmap.get(keys[i]);
        return (E)cmap.get(keys[l]);
    }

    public static <E> E getOrDefault(Map<String, Object> map, E defaultValue, String... keys) {
        if (keys == null || keys.length == 0)
            return (E)map;

        int l = keys.length - 1;
        Map<String, Object> cmap = map;
        for (int i=0; i<l; ++i) {
            cmap = (Map<String, Object>) cmap.get(keys[i]);
            if (cmap == null)
                return defaultValue;
        }
        return (E)cmap.getOrDefault(keys[l], defaultValue);
    }

    public static int getOrDefault(Map<String, Object> map, String key, int defval) {
        if (!map.containsKey(key))
            return defval;
        Object value = map.get(key);
        if (value instanceof Integer)
            return (Integer) value;
        if (value instanceof Long)
            return ((Long) value).intValue();
        if (value instanceof String)
            return Integer.parseInt((String) value);
        else
            return Integer.parseInt(value.toString());
    }

    public static long getOrDefault(Map<String, Object> map, String key, long defval) {
        if (!map.containsKey(key))
            return defval;
        Object value = map.get(key);
        if (value instanceof Integer)
            return ((Integer) value).longValue();
        if (value instanceof Long)
            return (Long) value;
        if (value instanceof String)
            return Long.parseLong((String) value);
        else
            return Integer.parseInt(value.toString());
    }

    public static int getInt(Map<String, Object> map, String... keys) {
        Object value = get(map, keys);
        if (value == null) return 0;
        if (value instanceof Integer)
            return (Integer)value;
        if (value instanceof Long)
            return ((Long)value).intValue();
        if (value instanceof String)
            return Integer.parseInt((String)value);
        else
            return Integer.parseInt(value.toString());
    }

    public static long getLong(Map<String, Object> map, String... keys) {
        Object value = get(map, keys);
        if (value == null) return 0;
        if (value instanceof Integer)
            return (Integer)value;
        if (value instanceof Long)
            return ((Long)value);
        if (value instanceof String)
            return Long.parseLong((String)value);
        else
            return Long.parseLong(value.toString());
    }

    public static String getString(Map<String, Object> map, String... keys) {
        Object value = get(map, keys);
        if (value == null) return null;
        else return value.toString();
    }

    public static int[] getIntArray(Map<String, Object> map, String... keys) {
        return ArrayUtils.asIntArray(get(map, keys));
    }

    public static long[] getLongArray(Map<String, Object> map, String... keys) {
        return ArrayUtils.asLongArray(get(map, keys));
    }

    public static boolean[] getBooleanArray(Map<String, Object> map, String... keys) {
        return ArrayUtils.asBooleanArray(get(map, keys));
    }

    public static String[] getStringArray(Map<String, Object> map, String... keys) {
        return ArrayUtils.asStringArray(get(map, keys));
    }

    // ----------------------------------------------------------------------

    public static Map<String, Object> exclude(Map<String, Object> map, String prefix) {
        Map<String, Object> dup = new HashMap<>();
        map.forEach((k, v) -> {
            if (!k.startsWith(prefix))
                dup.put(k, v);
        });

        return (map.size() == dup.size()) ? map : dup;
    }
}
