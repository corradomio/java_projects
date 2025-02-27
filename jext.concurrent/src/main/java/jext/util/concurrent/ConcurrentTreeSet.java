package jext.util.concurrent;

import java.util.TreeSet;

public class ConcurrentTreeSet<E> extends TreeSet<E> {

    @Override
    public synchronized boolean contains(Object e) {
        return super.contains(e);
    }

    @Override
    public synchronized boolean add(E e) {
        return super.add(e);
    }

}
