package jext.dll.openblas;

import java.util.HashMap;
import java.util.Map;

public class Maps {

    public static <K, V> Map<K, V> asMap(Object ... args) {
        Map<K, V> map = new HashMap<>();
        for(int i=0; i<args.length; i+=2)
            map.put((K)args[i+0], (V)args[i+1]);
        return map;
    }
}
