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

}
