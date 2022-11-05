package jext.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

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

    // ----------------------------------------------------------------------

    public static <E> E get(Map<String,Object> map, String... keys) {
        if (map == null)
            return null;
        if (keys == null || keys.length == 0)
            return (E)map;

        int l = keys.length - 1;
        Map<String,Object> cmap = map;
        for (int i=0; i<l && cmap != null; ++i)
            cmap = (Map<String,Object>) cmap.get(keys[i]);
        return cmap != null ? (E)cmap.get(keys[l]) : null;
    }

    public static Object put(Map<String,Object> map, Object ... keysValue) {
        if (keysValue == null || keysValue.length == 0)
            return map;

        Object[] keys = keysValue;
        int v = keysValue.length - 1;
        int l = v - 1;
        Map<String,Object> cmap = map;
        for (int i=0; i<l && cmap != null; ++i) {
            if (!cmap.containsKey((String)keys[i]))
                cmap.put((String)keys[i], new HashMap<>());
            cmap = (Map<String,Object>) cmap.get(keys[i]);
        }
        return cmap != null ? cmap.put((String)keys[l], keysValue[v]) : null;
    }

    // ----------------------------------------------------------------------

    public static <K, V> Map<K, V> difference(Map<K, V> cmap, Map<K, V> omap) {
        Map<K, V> dmap = new HashMap<>();
        for (K key : cmap.keySet()) {
            if (!omap.containsKey(key)) {
                dmap.put(key, cmap.get(key));
                continue;
            }

            V cvalue = cmap.get(key);
            V ovalue = omap.get(key);

            if (cvalue == null && ovalue == null)
                continue;
            if (cvalue == null || ovalue == null) {
                dmap.put(key, cvalue);
                continue;
            }
            if (!cvalue.equals(ovalue)) {
                dmap.put(key, cvalue);
                continue;
            }
        }

        return dmap;
    }

    // ----------------------------------------------------------------------

    public static String  getOrDefault(Map<String,Object> map, String key, String defaultValue) {
        if (!map.containsKey(key))
            return defaultValue;
        Object value = map.get(key);
        if (value == null)
            return defaultValue;
        else
            return value.toString();
    }

    public static int getOrDefault(Map<String,Object> map, String key, int defaultValue) {
        if (!map.containsKey(key))
            return defaultValue;
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

    public static boolean getOrDefault(Map<String,Object> map, String key, boolean defaultValue) {
        if (!map.containsKey(key))
            return defaultValue;
        Object value = map.get(key);
        if (value instanceof Boolean)
            return (Boolean) value;
        if (value instanceof String)
            return Boolean.parseBoolean((String) value);
        else
            return Boolean.parseBoolean(value.toString());
    }

    // ----------------------------------------------------------------------

    public static double getOrDefault(Map<String,Object> map, String key, int index, double defaultValue){
        double[] dArray = getDoubleArray(map, key);

        if(dArray == null || dArray.length<=index)
            return defaultValue;

        return dArray[index];
    }

    public static boolean getOrDefault(Map<String,Object> map, String key, int index, boolean defaultValue){
        boolean[] dArray = getBooleanArray(map, key);

        if(dArray == null || dArray.length<=index)
            return defaultValue;

        return dArray[index];
    }

    // ----------------------------------------------------------------------

    public static int getInt(Map<String,Object> map, String... keys) {
        Object value = get(map, keys);
        if (value == null) return 0;
        if (value instanceof Number)
            return ((Number)value).intValue();
        // if (value instanceof String)
        //     return Integer.parseInt((String)value);
        else
            return Integer.parseInt(value.toString());
    }

    public static long getLong(Map<String,Object> map, String... keys) {
        Object value = get(map, keys);
        if (value == null) return 0;
        if (value instanceof Number)
            return ((Number)value).longValue();
        // if (value instanceof String)
        //     return Long.parseLong((String)value);
        else
            return Long.parseLong(value.toString());
    }

    public static String getString(Map<String,Object> map, String... keys) {
        Object value = get(map, keys);
        if (value == null) return null;
        else return value.toString();
    }

    // ----------------------------------------------------------------------

    public static boolean[] getBooleanArray(Map<String,Object> map, String... keys) {
        return ArrayUtils.asBooleanArray(get(map, keys));
    }

    public static int[] getIntArray(Map<String,Object> map, String... keys) {
        return ArrayUtils.asIntArray(get(map, keys));
    }

    public static double[] getDoubleArray(Map<String,Object> map, String... keys) {
        return ArrayUtils.asDoubleArray(get(map, keys));
    }

    public static String[] getStringArray(Map<String,Object> map, String... keys) {
        return ArrayUtils.asStringArray(get(map, keys));
    }

    // ----------------------------------------------------------------------

    public static Map<String,Object> excluding(Map<String,Object> map, String ... keys) {
        Map<String,Object> dup = new HashMap<>();
        map.forEach((k, v) -> {
            if (!isExcluded(keys, k))
                dup.put(k, v);
        });

        return (map.size() == dup.size()) ? map : dup;
    }

    private static boolean isExcluded(String[] keys, String k) {
        for(String t : keys)
            if (t.equals(k))
                return true;
        return false;
    }

    // ----------------------------------------------------------------------

}
