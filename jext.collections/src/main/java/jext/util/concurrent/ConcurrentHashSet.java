package jext.util.concurrent;

import jext.util.HashSet;

public class ConcurrentHashSet<E> extends HashSet<E> {

    public ConcurrentHashSet() {
        super();
    }

    public synchronized boolean contains(Object e) {
        return super.contains(e);
    }

    public synchronized boolean add(E e) {
        return super.add(e);
    }

}
