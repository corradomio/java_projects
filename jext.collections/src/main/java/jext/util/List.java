package jext.util;

import java.util.Collection;

public interface List<E> extends java.util.List<E> {

    List<E> add_(E e);

    List<E> addAll_(Collection<? extends E> c);
}
