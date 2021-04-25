package jext.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class IterableUtils {

    public static <E> List<E> asList(Iterable<E> iter) {
        List<E> list = new ArrayList<>();
        for(E e : iter)
            list.add(e);
        return list;
    }

    public static <E> Set<E> asSet(Iterable<E> iter) {
        Set<E> set = new HashSet<>();
        for(E e : iter)
            set.add(e);
        return set;
    }

    public static <E extends Comparable<E>> Set<E> asTreeSet(Iterable<E> iter) {
        Set<E> set = new TreeSet<>();
        for(E e : iter)
            set.add(e);
        return set;
    }
}
