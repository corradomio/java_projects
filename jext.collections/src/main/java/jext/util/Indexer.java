package jext.util;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;


public class Indexer<T> extends HashMap<T, Integer> {

    private List<T> items = new ArrayList<>();
    private Map<Integer, T> inverse = new HashMap<>();

    public Indexer() {

    }

    public void add(T item) {
        if (!this.containsKey(item)) {
            int idx = this.size();
            this.items.add(item);
            this.put(item, idx);
            this.inverse.put(idx, item);
        }
    }

    public int index(T item) {
        return this.get(item);
    }

    public List<T> items() {
        return items;
    }

    public T[] items(int[] idxs, Class<T> clazz) {
        int j = 0;
        T[] items = (T[])Array.newInstance(clazz, idxs.length);
        for(int i : idxs)
            items[j++] = inverse.get(i);
        return (T[])items;
    }

}
