package jext.jgrapht.util;

import org.jgrapht.Graph;
import org.jgrapht.GraphType;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Utils {

    public static <T> T[] toArray(Collection<T> coll) {
        T[] array = (T[]) new Object[coll.size()];
        coll.toArray(array);
        return array;
    }

    public static <T> Set<T> asSet(T ... elements) {
        return new HashSet<>(Arrays.asList(elements));
    }

    public static <T> boolean isSuperset(Set<T> s1, Set<T> s2) {
        return s1.containsAll(s2);
    }

    public static <T> boolean isSameSet(Set<T> s1, Set<T> s2) {
        return s1.containsAll(s2) && s2.containsAll(s1);
    }

    public static <T> Set<T> difference(Set<T> s1, Set<T> s2) {
        Set<T> r = new HashSet<T>(s1);
        r.removeAll(s2);
        return r;
    }

    // ----------------------------------------------------------------------

    public static <T> Set<T> union(Set<T> s1, Set<T> s2) {
        Set<T> r = new HashSet<T>();
        r.addAll(s1);
        r.addAll(s2);
        return r;
    }

    public static <T> Set<T> intersection(Set<T> s1, Set<T> s2) {
        Set<T> r = new HashSet<T>(s1);
        r.retainAll(s2);
        return r;
    }

    // public static <T> Set<T> difference(Set<T> s1, Set<T> s2) {
    //     Set<T> r = new HashSet<T>(s1);
    //     r.removeAll(s2);
    //     return r;
    // }

}
