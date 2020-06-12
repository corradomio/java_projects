package jext.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Collections {

    // -- utilities

    public static List<Integer> asList(int[] a) {
        List<Integer> list = new ArrayList<>(a.length);
        for (int i=0; i<a.length; ++i)
            list.add(a[i]);
        return list;
    }

    public static <T> Set<T> asSet(T... s) {
        Set<T> set = new HashSet<>();
        if (s != null)
            set.addAll(Arrays.asList(s));
        return set;
    }

    public static <T> List<T> asList(T... a) {
        if (a == null)
            return new ArrayList<>();
        else
            return Arrays.asList(a);
    }

    public static <T> boolean isOneOf(T item, T... list) {
        if (item == null)
            return false;

        for(T i : list)
            if (item.equals(i))
                return true;

        return false;
    }

}
