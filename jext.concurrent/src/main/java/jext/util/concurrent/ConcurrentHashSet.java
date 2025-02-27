package jext.util.concurrent;

import java.util.HashSet;

public class ConcurrentHashSet<E> extends HashSet<E> {

    @Override
    public synchronized boolean contains(Object e) {
        return super.contains(e);
    }

    @Override
    public synchronized boolean add(E e) {
        return super.add(e);
    }

}
