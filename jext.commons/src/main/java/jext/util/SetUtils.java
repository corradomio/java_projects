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

    public static <T> boolean isSameSet(Set<T> s1, Set<T> s2) {
        return s1.equals(s2);
    }

    public static <T> boolean isSubset(Set<T> s1, Set<T> s2) {
        return isSuperset(s2, s1);
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

    public static <T> Set<T> union(Collection<T>... sets){
        Set<T> r = new HashSet<T>();
        for (Collection<T> s : sets)
            r.addAll(s);
        return r;
    }

    public static <T> Set<T> union(Collection<T> s1, Collection<T> s2) {
        // Set<T> r = new HashSet<T>(s1);
        // r.addAll(s2);
        // return r;
        return union(s1, s2, false);
    }

    public static <T> Set<T> intersection(Collection<T> s1, Collection<T> s2) {
        // Set<T> r = new HashSet<T>(s1);
        // r.retainAll(s2);
        // return r;
        return intersection(s1, s2, false);
    }

    public static <T> Set<T> difference(Collection<T> s1, Collection<T> s2) {
        // Set<T> r = new HashSet<T>(s1);
        // r.removeAll(s2);
        // return r;
        return difference(s1, s2, false);
    }

    public static <T> Set<T> simmdiff(Collection<T> s1, Collection<T> s2) {
        // Set<T> r = new HashSet<T>();
        // r.addAll(difference(s1, s2));
        // r.addAll(difference(s2, s1));
        // return r;
        return simmdiff(s1, s2, false);
    }

    // ----------------------------------------------------------------------

    public static <T> Set<T> union(Collection<T> s1, Collection<T> s2, boolean ordered) {
        Set<T> r = ordered ? new TreeSet<T>(s1) : new HashSet<>(s1);
        r.addAll(s2);
        return r;
    }

    public static <T> Set<T> intersection(Collection<T> s1, Collection<T> s2, boolean ordered) {
        Set<T> r = ordered ? new TreeSet<T>(s1) : new HashSet<>(s1);
        r.retainAll(s2);
        return r;
    }

    public static <T> Set<T> difference(Collection<T> s1, Collection<T> s2, boolean ordered) {
        Set<T> r = ordered ? new TreeSet<T>(s1) : new HashSet<>(s1);
        r.removeAll(s2);
        return r;
    }

    public static <T> Set<T> simmdiff(Collection<T> s1, Collection<T> s2, boolean ordered) {
        Set<T> r = ordered ? new TreeSet<T>(s1) : new HashSet<>(s1);
        r.addAll(difference(s1, s2));
        r.addAll(difference(s2, s1));
        return r;
    }

}
