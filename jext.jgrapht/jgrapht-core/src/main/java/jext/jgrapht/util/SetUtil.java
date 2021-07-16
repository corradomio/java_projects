package jext.jgrapht.util;

import java.util.HashSet;
import java.util.Set;

public class SetUtil {

    public static <T> boolean isSuperset(Set<T> s1, Set<T> s2) {
        return s1.containsAll(s2);
    }

    public static <T> boolean hasIntersection(Set<T> s1, Set<T> s2) {
        for (T e : s1)
            if (s2.contains(e))
                return true;
        return false;
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

    public static <T> Set<T> difference(Set<T> s1, Set<T> s2) {
        Set<T> r = new HashSet<T>(s1);
        r.removeAll(s2);
        return r;
    }

}
