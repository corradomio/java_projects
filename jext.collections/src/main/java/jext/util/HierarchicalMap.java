package jext.util;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

/**
 * As a HashMap but with a hierarchical organization of the keys
 */
public class HierarchicalMap<V> extends AbstractMap<String, V> implements Map<String, V> {

    private static final String VALUE = "$value";

    private String separator = "/";
    private Map<String,Object> root = new HashMap<>();
    private int size = 0;

    public HierarchicalMap() {
        super();
    }

    public HierarchicalMap(String separator) {
        super();
        this.separator = separator;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V put(String path, V value) {
        String[] parts = path.split(separator);
        int n = parts.length;
        String key;

        Map<String,Object> current = root;
        for(int i=0; i<n; ++i) {
            key = parts[i];
            if (!current.containsKey(key))
                current.put(key, new java.util.HashMap<>());
            current = (Map<String,Object>) current.get(key);
        }
        if (!current.containsKey(VALUE)) {
            size += 1;
        }
        return ((Map<String, V>)current).put(VALUE, value);
    }

    @Override
    public V get(Object path) {
        String[] parts = ((String)path).split(separator);
        int n = parts.length;

        Map<String,Object> current = root;
        for(int i=0; i<n; ++i) {
            String key = parts[i];
            current = (Map<String,Object>) current.get(key);
            if (current == null)
                return null;
        }
        return ((Map<String, V>)current).get(VALUE);
    }

    @Override
    public Set<Entry<String, V>> entrySet() {
        throw new UnsupportedOperationException();
    }
}
