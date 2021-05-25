package jext.util;

import java.util.Collection;

public class HashSet<E> extends java.util.HashSet<E> implements Set<E> {

    public HashSet() {
        super();
    }

    public HashSet(Collection<? extends E> c) {
        super(c);
    }

    public HashSet(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public HashSet(int initialCapacity) {
        super(initialCapacity);
    }

    // public HashSet<E> add_(E e) {
    //     super.add(e);
    //     return this;
    // }

    // @Override
    // public Set<E> addAll_(Collection<? extends E> c) {
    //     super.addAll(c);
    //     return this;
    // }

}
