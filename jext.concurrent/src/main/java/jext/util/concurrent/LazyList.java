package jext.util.concurrent;

import jext.logging.Logger;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Another
 * @param <E>
 */
public class LazyList<E> extends AbstractList<E> implements List<E> {

    private static Logger logger = Logger.getLogger(LazyList.class);

    private List<E> list = new ArrayList<>();
    private transient boolean loading;
    private Future<?> future;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public LazyList() {
        super();
        loading = true;
    }

    public LazyList(ExecutorService service, Consumer<List<E>> populate) {
        super();
        loading = true;

        future = service.submit(() -> {
            try {
                populate.accept(this);
            }
            catch (Throwable t) {
                logger.error(t, t);
            }
            finally {
                loading = false;
            }
        });
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public boolean isLoading() {
        return loading;
    }

    public void done() {
        loading = false;
    }

    /**
     * Wait until the list is totally loaded
     * @return
     */
    public LazyList<E> waitForCompletion() {
        try {
            future.get();
        } catch (Exception e) { }
        return this;
    }

    /**
     * Wait until the list is loaded with the specified number of elements
     * @param nElements
     * @return
     */
    public LazyList<E> waitForContent(int nElements) {
        while(size() < nElements && loading)
            Sleep.sleep(100);
        return this;
    }

    // ----------------------------------------------------------------------
    // Important methods
    // ----------------------------------------------------------------------

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean add(E e) {
        synchronized (list) {
            return list.add(e);
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        synchronized (list) {
            return list.addAll(c);
        }
    }

    // ----------------------------------------------------------------------
    // Secondary methods
    // ----------------------------------------------------------------------

    @Override
    public Stream<E> stream() {
        return list.stream();
    }

    @Override
    public Stream<E> parallelStream() {
        return list.parallelStream();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        list.forEach(action);
    }

    // ----------------------------------------------------------------------
    // Secondary methods
    // ----------------------------------------------------------------------

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Iterator<E> iterator() {
        if (!loading)
            return list.iterator();
        else
            return list.iterator();
    }

    @Override
    public E get(int index) {
        // synchronized (list) {
            return list.get(index);
        // }
    }

    // @Override
    // public boolean contains(Object o) {
    //     return list.contains(o);
    // }

    // @Override
    // public boolean containsAll(Collection<?> c) {
    //     return list.containsAll(c);
    // }

    // @Override
    // public boolean addAll(int index, Collection<? extends E> c) {
    //     return list.addAll(index, c);
    // }

    // @Override
    // public boolean remove(Object o) {
    //     return list.remove(o);
    // }

    // @Override
    // public boolean removeAll(Collection<?> c) {
    //     return list.removeAll(c);
    // }

    // @Override
    // public boolean retainAll(Collection<?> c) {
    //     return list.retainAll(c);
    // }

    // @Override
    // public void replaceAll(UnaryOperator<E> operator) {
    //     list.replaceAll(operator);
    // }

    // @Override
    // public ListIterator<E> listIterator() {
    //     return list.listIterator();
    // }

    // @Override
    // public ListIterator<E> listIterator(int index) {
    //     return list.listIterator(index);
    // }

    // @Override
    // public List<E> subList(int fromIndex, int toIndex) {
    //     return list.subList(fromIndex, toIndex);
    // }

    // @Override
    // public Spliterator<E> spliterator() {
    //     return list.spliterator();
    // }

    // @Override
    // public Object[] toArray() {
    //     return list.toArray();
    // }

    // @Override
    // public <T> T[] toArray(T[] a) {
    //     return list.toArray(a);
    // }

    // @Override
    // public void sort(Comparator<? super E> c) {
    //     list.sort(c);
    // }

    // @Override
    // public boolean equals(Object o) {
    //     return list.equals(o);
    // }

    // @Override
    // public int hashCode() {
    //     return list.hashCode();
    // }

    // @Override
    // public E set(int index, E element) {
    //     return list.set(index, element);
    // }

    // @Override
    // public void add(int index, E element) {
    //     list.add(index, element);
    // }

    // @Override
    // public E remove(int index) {
    //     return list.remove(index);
    // }

    // @Override
    // public int indexOf(Object o) {
    //     return list.indexOf(o);
    // }

    // @Override
    // public int lastIndexOf(Object o) {
    //     return list.lastIndexOf(o);
    // }

    // @Override
    // public boolean removeIf(Predicate<? super E> filter) {
    //     return list.removeIf(filter);
    // }

}
