package ae.ac.ebtic.sql.bt.btproxy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MapUtils {

    public static <K, V> Map<K, V> asMap(Object ... args) {
        return putAll(new HashMap<>(), args);
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

    public static <E> E get(Map<String, Object> map, String... keys) {
        if (map == null)
            return null;
        if (keys == null || keys.length == 0)
            return (E)map;

        int l = keys.length - 1;
        Map<String, Object> cmap = map;
        for (int i=0; i<l && cmap != null; ++i)
            cmap = (Map<String, Object>) cmap.get(keys[i]);
        return cmap != null ? (E)cmap.get(keys[l]) : null;
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

    // ----------------------------------------------------------------------

    public static int getInt(Map<String, Object> map, String... keys) {
        Object value = get(map, keys);
        if (value == null) return 0;
        if (value instanceof Number)
            return ((Number)value).intValue();
        else
            return Integer.parseInt(value.toString());
    }

    // public static String getString(Map<String, Object> map, String... keys) {
    //     Object value = get(map, keys);
    //     if (value == null) return null;
    //     else return value.toString();
    // }

}
