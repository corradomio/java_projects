package jext.util;

public interface Bag<E> extends Iterable<E> {

    void add(E e);

    int count(E e);

}
