package jext.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public abstract class AbstractList<E> implements List<E> {

    private List<E> content;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------


    // ----------------------------------------------------------------------
    // Valid Properties/Operations
    // ----------------------------------------------------------------------

    // valid methods:
    //  size()
    //  isEmpty()
    //  Iterator<E> iterator()
    //  Stream<E>   stream()

    @Override
    public int size() {
        return content().size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return content().iterator();
    }

    @Override
    public Stream<E> stream() {
        return content().stream();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        content().forEach(action);
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    protected List<E> content() {
        if (content == null)
            content = getContent();
        return content;
    }

    protected abstract List<E> getContent();

    // ----------------------------------------------------------------------
    // Unused
    // ----------------------------------------------------------------------

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
