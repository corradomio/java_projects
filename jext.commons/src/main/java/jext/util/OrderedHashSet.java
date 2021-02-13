package jext.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class OrderedHashSet<E> extends ArrayList<E> implements Set<E> {

    private Set<E> set = new HashSet<>();

    public OrderedHashSet() {
        super();
    }

    @Override
    public boolean contains(Object o) {
        return set.contains(o);
    }

    @Override
    public boolean add(E e) {
        if (contains(e))
            return false;
        super.add(e);
        set.add(e);
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        c.forEach(this::add);
        return true;
    }
}
