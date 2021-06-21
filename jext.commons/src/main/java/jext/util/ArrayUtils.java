package jext.util;

import java.util.Arrays;

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
    // asArray
    // ----------------------------------------------------------------------

    public static int[] asArray(int... a) {
        return a;
    }

    public static boolean[] asArray(boolean... a) {
        return a;
    }

    public static int indexOf(int[] a, int v) {
        int n = a.length;
        for(int i=0; i<n; ++i)
            if (a[i] == v)
                return i;
        return -1;
    }

    // ----------------------------------------------------------------------
    // append
    // ----------------------------------------------------------------------

    // public static boolean[] append(boolean[] a, boolean v) {
    //     if (a == null || a.length == 0)
    //         return set(null, 0, v);
    //     else
    //         return set(a, a.length, v);
    // }

    // public static int[] append(int[] a, int v) {
    //     if (a == null || a.length == 0)
    //         return set(null, 0, v);
    //     else
    //         return set(a, a.length, v);
    // }

    // ----------------------------------------------------------------------
    // set
    // ----------------------------------------------------------------------

    public static boolean[] set(int index, boolean v) {
        return set(null, index, v);
    }

    public static boolean[] set(boolean[] a, int index, boolean v) {
        if (index <= -1)
            index = a.length + index + 1;
        if (a == null)
            a = new boolean[index+1];
        if (a.length <= index)
            a = Arrays.copyOf(a, index+1);
        a[index] = v;
        return a;
    }

    public static int[] set(int[] a, int index, int v) {
        if (index <= -1)
            index = a.length + index + 1;
        if (a == null)
            a = new int[index+1];
        if (a.length <= index)
            a = Arrays.copyOf(a, index+1);
        a[index] = v;
        return a;
    }

    /**
     * Insert 'v' in 'a' keeping the order
     */
    public static int[] setOrdered(int[] a, int v) {
        // a i null or empty
        if (a == null || a.length == 0)
            return new int[]{v};
        int l = a.length-1;
        // a contains more values (used in DEBUG)
        if (a[l] > v) {
            int r = 0;
            while (a[r] <= v) ++r;
            a = Arrays.copyOf(a, r);
            l = a.length - 1;
        }
        // the last value of a il less than 'v'
        // -> append 'v' to a
        if (a[l] < v) {
            a = Arrays.copyOf(a, l+2);
            a[l+1] = v;
        }
        return a;
    }
}
