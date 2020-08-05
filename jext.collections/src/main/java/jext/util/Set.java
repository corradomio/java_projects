package jext.util;

import java.util.Collection;

public interface Set<E> extends java.util.Set<E> {

    Set<E> add_(E e);

    Set<E> addAll_(Collection<? extends E> c);
}
