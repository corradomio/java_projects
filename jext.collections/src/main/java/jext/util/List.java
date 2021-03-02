package jext.util;

import java.util.Collection;

public interface List<E> extends java.util.List<E> {

    List<E> addThis(E e);

    List<E> addAllThis(Collection<? extends E> c);
}
