package jext.util;

import java.util.HashMap;
import java.util.Iterator;

public class Bag<T> extends HashMap<T, Integer> implements Iterable<T> {

    public Bag() { }

    public void add(T item) {
        this.put(item, 1+ this.getOrDefault(item, 0));
    }

    public int maxCount() {
        int max = 0;
        for(T item: keySet()) {
            int count = this.get(item);
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

    public Pair<T, Integer>[] items() {
        Pair<T, Integer>[] items = new Pair[this.size()];

        int i =0;
        for(T item: keySet()) {
            items[i++] = new Pair<>(item, this.get(item));
        }

        // Arrays.sort(items, (o1, o2) -> {
        //     int i1 = o1.getValue();
        //     int i2 = o2.getValue();
        //     if (i1 == i2)
        //         return o1.getKey().compareTo(o2.getKey());
        //     else
        //         return -(i1-i2);
        // });

        return items;
    }

    @Override
    public Iterator<T> iterator() {
        return super.keySet().iterator();
    }
}
