package jext.util;

import java.util.Map;
import java.util.TreeMap;

public class NamespaceTree {

    private Map<String, Object> names = new TreeMap<>();

    public void add(String fullname) {
        String[] parts = fullname.split("\\.");
        int n = parts.length;

        Map<String, Object> current = names;
        for (int i=0; i<(n-1); i++) {
            current = select(current, parts[i]);
        }

        add(current, parts[n-1], fullname);

    }

    private static Map<String, Object> select(Map<String, Object> current, String key) {
        if (!current.containsKey(key))
            current.put(key, new TreeMap<>());
        return (Map<String, Object>) current.get(key);
    }

    private static void add(Map<String, Object> current, String key, String fullname) {
        current.put(key, fullname);
    }
}
