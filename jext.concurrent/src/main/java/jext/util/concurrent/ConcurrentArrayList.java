package jext.util.concurrent;

import java.util.ArrayList;

public class ConcurrentArrayList<E> extends ArrayList<E> {

    @Override
    public synchronized boolean add(E e) {
        return super.add(e);
    }
}
