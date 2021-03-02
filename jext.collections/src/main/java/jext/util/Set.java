package jext.util;

import java.util.Collection;

public interface Set<E> extends java.util.Set<E> {

    Set<E> addThis(E e);

    Set<E> addAllThis(Collection<? extends E> c);
}
