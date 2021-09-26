package jext.util;

import java.util.HashMap;
import java.util.Iterator;

public class HashBag<E> extends HashMap<E, Integer> implements Bag<E> {

    @Override
    public void add(E e) {
        if (!super.containsKey(e))
            super.put(e, 1);
        else
            super.put(e, super.get(e) + 1);
    }

    @Override
    public int count(E e) {
        return super.getOrDefault(e, 0);
    }

    @Override
    public Iterator<E> iterator() {
        return super.keySet().iterator();
    }
}
