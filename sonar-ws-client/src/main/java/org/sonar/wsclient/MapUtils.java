package org.sonar.wsclient;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {
    public static Map<String, Object> asMap(Object ... values) {
        Map<String, Object> map = new HashMap<>();
        for(int i=0; i<values.length; i+=2) {
            String key = values[i+0].toString();
            Object val = values[i+1];
            map.put(key, val);
        }
        return map;
    }

    public static int getInt(Map<String, Object> map, String... keys) {
        int l = keys.length-1;
        for(int i=0; i<l; ++i) {
            map = (Map<String, Object>) map.get(keys[i]);
        }
        Object value = map.get(keys[l]);
        if (value == null)
            return 0;
        if (value instanceof String)
            return Integer.parseInt((String) value);
        else
            return ((Number)value).intValue();
    }
}
