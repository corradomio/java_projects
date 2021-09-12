package jext.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetUtils {

    public static <T> boolean empty(Set<T> s) {
        return s.size() == 0;
    }

    public static <T> Set<T> asSet(T... args) {
        return asSet(Arrays.asList(args));
    }

    public static <T> Set<T> asSet(Collection<T> c) {
        if (c instanceof Set)
            return (Set<T>) c;
        else
            return new HashSet<>(c);
    }

    // ----------------------------------------------------------------------

    public static <T> boolean isSameset(Set<T> s1, Set<T> s2) {
        return s1.equals(s2);
        // if (s1.size() != s2.size())
        //     return false;
        //
        // for(T e : s1)
        //     if (!s2.contains(e))
        //         return false;
        // return true;
    }

    public static <T> boolean isSubset(Set<T> s1, Set<T> s2) {
        return isSuperset(s2, s1);
        // if (s1.size() > s2.size())
        //     return false;
        //
        // for(T e : s1)
        //     if (!s2.contains(e))
        //         return false;
        // return true;
    }

    public static <T> boolean isSuperset(Set<T> s1, Set<T> s2) {
        return s1.containsAll(s2);
    }

    public static <T> boolean hasIntersection(Set<T> s1, Set<T> s2) {
        return hasIntersection(s1, s2, 1);
    }

    public static <T> boolean hasIntersection(Set<T> s1, Set<T> s2, int mincard) {
        int card = 0;
        for (T e : s1)
            if (s2.contains(e) && (++card >= mincard))
                return true;
        return false;
    }

    // ----------------------------------------------------------------------

    public static <T> Set<T> union(Set<T>... sets){
        Set<T> r = new HashSet<T>();
        for (Set<T> s : sets)
            r.addAll(s);
        return r;
    }

    public static <T> Set<T> union(Set<T> s1, Set<T> s2) {
        Set<T> r = new HashSet<T>(s1);
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

    public static <T> Set<T> simmdiff(Set<T> s1, Set<T> s2) {
        Set<T> r = new HashSet<T>();
        r.addAll(difference(s1, s2));
        r.addAll(difference(s2, s1));
        return r;
    }

    // ----------------------------------------------------------------------

    public static <T> Set<T> unionOrdered(Set<T> s1, Set<T> s2) {
        Set<T> r = new TreeSet<T>(s1);
        r.addAll(s2);
        return r;
    }

    public static <T> Set<T> intersectionOrdered(Set<T> s1, Set<T> s2) {
        Set<T> r = new TreeSet<T>(s1);
        r.retainAll(s2);
        return r;
    }

    public static <T> Set<T> differenceOrdered(Set<T> s1, Set<T> s2) {
        Set<T> r = new TreeSet<T>(s1);
        r.removeAll(s2);
        return r;
    }

    public static <T> Set<T> simmdiffOrdered(Set<T> s1, Set<T> s2) {
        Set<T> r = new TreeSet<T>();
        r.addAll(difference(s1, s2));
        r.addAll(difference(s2, s1));
        return r;
    }

}
