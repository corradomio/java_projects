package org.cocome.tradingsystem.util.java;

import java.util.Iterator;

public class Iterables {
    /* module m2 */

    public static <E> Iterable<E> iterable(final E[] array) {
        return new Iterable<E>() {
            @Override
            public Iterator<E> iterator() {
                return new ArrayIterator<E>(array);
            }
        };
    }

    public static <E> Iterator<E> iterator(final E[] array) {
        return new ArrayIterator<E>(array);
    }

    private static final class ArrayIterator<E> implements Iterator<E> {
        /* module m2 */
        private ArrayIterator(final E[] array) {

        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }
    }
}
