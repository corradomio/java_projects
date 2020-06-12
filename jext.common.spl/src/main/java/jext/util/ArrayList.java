package jext.util;

import java.util.Collection;

public class ArrayList<E> extends java.util.ArrayList<E> {

    public ArrayList() {
        super();
    }

    public ArrayList(Collection<? extends E> c) {
        super(c);
    }

    public ArrayList<E> add_(E e) {
        super.add(e);
        return this;
    }

    public ArrayList<E> addAll_(Collection<? extends E> c) {
        super.addAll(c);
        return this;
    }
}
