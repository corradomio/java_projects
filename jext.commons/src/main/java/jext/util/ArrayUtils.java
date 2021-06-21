package jext.util;

import java.util.Arrays;

public class ArrayUtils {

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

    public static boolean[] append(boolean[] a, boolean v) {
        if (a == null || a.length == 0)
            return set(null, 0, v);
        else
            return set(a, a.length, v);
    }

    public static int[] append(int[] a, int v) {
        if (a == null || a.length == 0)
            return set(null, 0, v);
        else
            return set(a, a.length, v);
    }

    // ----------------------------------------------------------------------
    // set
    // ----------------------------------------------------------------------

    public static boolean[] set(boolean[] a, int index, boolean v) {
        if (a == null)
            a = new boolean[index+1];
        if (a.length <= index)
            a = Arrays.copyOf(a, index+1);
        a[index] = v;
        return a;
    }

    public static int[] set(int[] a, int index, int v) {
        if (a == null)
            a = new int[index+1];
        if (a.length <= index)
            a = Arrays.copyOf(a, index+1);
        a[index] = v;
        return a;
    }
}
