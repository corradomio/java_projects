package jext.util;

import java.util.HashMap;
import java.util.Map;

public class Maps {

    public static<K, V> Map<K, V> asMap(Object... args) {
        Map<K, V> map = new HashMap<K, V>();
        for (int i=0; i<args.length; i+=2) {
            K key = (K) args[i+0];
            V value = (V) args[i+1];
            map.put(key, value);
        }
        return map;
    }
}
