package jext.util;

import java.util.Collection;

public class ArrayList<E> extends java.util.ArrayList<E> implements List<E> {

    public ArrayList() {
        super();
    }

    public ArrayList(Collection<? extends E> c) {
        super(c);
    }

    @Override
    public ArrayList<E> addThis(E e) {
        super.add(e);
        return this;
    }

    @Override
    public ArrayList<E> addAllThis(Collection<? extends E> c) {
        super.addAll(c);
        return this;
    }
}
