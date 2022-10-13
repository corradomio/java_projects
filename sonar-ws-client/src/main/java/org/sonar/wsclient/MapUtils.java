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
}
