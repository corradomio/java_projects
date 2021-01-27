package jext.util;

import java.util.HashMap;


public class Indexer<T> extends HashMap<T, Integer> {

    private List<T> items = new ArrayList<>();

    public Indexer() {

    }

    public void add(T item) {
        if (!this.containsKey(item)) {
            this.items.add(item);
            this.put(item, this.size());
        }
    }

    public int index(T item) {
        return this.get(item);
    }

    public List<T> items() {
        return items;
    }

}
