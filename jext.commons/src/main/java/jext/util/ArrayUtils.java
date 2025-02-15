package jext.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayUtils {

    // ----------------------------------------------------------------------
    // range
    // ----------------------------------------------------------------------

    public static int[] range(int max) {
        return range(0, max);
    }

    public static int[] range(int min, int max) {
        int n = max - min;
        int[] a = new int[n];
        for (int i=0; i<n; ++i)
            a[i] = min+i;
        return a;
    }

    // ----------------------------------------------------------------------
    // asList
    // ----------------------------------------------------------------------

    public static <T> List<T> asList(T... args) {
        List<T> list = new ArrayList<>();
        if (args == null)
            return list;
        for (int i=0; i<args.length; ++i)
            list.add(args[i]);
        return list;
    }

    public static List<Integer> asList(int[] a) {
        if (a == null)
            return Collections.emptyList();
        List<Integer> l = new ArrayList<>();
        for (int i : a)
            l.add(i);
        return l;
    }

    public static List<Long> asList(long[] a) {
        if (a == null)
            return Collections.emptyList();
        List<Long> l = new ArrayList<>();
        for (long i : a)
            l.add(i);
        return l;
    }

    public static List<Boolean> asList(boolean[] a) {
        if (a == null)
            return Collections.emptyList();
        List<Boolean> l = new ArrayList<>();
        for (boolean i : a)
            l.add(i);
        return l;
    }

    public static List<Double> asList(double[] a) {
        if (a == null)
            return Collections.emptyList();
        List<Double> l = new ArrayList<>();
        for (double i : a)
            l.add(i);
        return l;
    }

    public static List<Float> asList(float[] a) {
        if (a == null)
            return Collections.emptyList();
        List<Float> l = new ArrayList<>();
        for (float i : a)
            l.add(i);
        return l;
    }


    // ----------------------------------------------------------------------
    // equals
    // ----------------------------------------------------------------------
    // check if two arrays are equal

    public static boolean equals(int[] a, int[] b) {
        if (a.length != b.length)
            return false;

        for (int i=0; i<a.length; ++i)
            if (a[i] != b[i])
                return false;
        return true;
    }

    public static boolean equals(long[] a, long[] b) {
        if (a.length != b.length)
            return false;

        for (int i=0; i<a.length; ++i)
            if (a[i] != b[i])
                return false;
        return true;
    }


}
