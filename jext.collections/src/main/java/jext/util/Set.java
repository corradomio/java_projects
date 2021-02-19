package jext.util;

import java.util.Collection;

public interface Set<E> extends java.util.Set<E> {

    Set<E> put(E e);

    Set<E> putAll(Collection<? extends E> c);
}
