package jext.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class AbstractSet<E> implements Set<E> {

    private Set<E> content;

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

    protected Set<E> content() {
        if (content == null)
            content = getContent();
        return content;
    }

    protected abstract Set<E> getContent();

    // ----------------------------------------------------------------------
    // Unused
    // ----------------------------------------------------------------------

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    // @Override
    public <T> T[] toArray(IntFunction<T[]> generator) {
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
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Spliterator<E> spliterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Stream<E> parallelStream() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
