package jext.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ArrayUtils {

    public static int[] EMPTY_INT_ARRAY = new int[0];
    public static long[] EMPTY_LONG_ARRAY = new long[0];
    public static double[] EMPTY_DOUBLE_ARRAY = new double[0];
    public static boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
    public static String[] EMPTY_STRING_ARRAY = new String[0];

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

    public static List<Integer> asList(int[] a) {
        List<Integer> l = new ArrayList<>();
        for (int i : a)
            l.add(i);
        return l;
    }

    public static List<Long> asList(long[] a) {
        List<Long> l = new ArrayList<>();
        for (long i : a)
            l.add(i);
        return l;
    }

    public static List<Boolean> asList(boolean[] a) {
        List<Boolean> l = new ArrayList<>();
        for (boolean i : a)
            l.add(i);
        return l;
    }

    // ----------------------------------------------------------------------
    // asArray
    // ----------------------------------------------------------------------

    public static boolean isEmpty(int... a) {
        return a == null || a.length == 0;
    }

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

    public static int[] asIntArray(Object v) {
        if (v == null || v instanceof Collection && ((Collection)v).isEmpty())
            return EMPTY_INT_ARRAY;
        if (v instanceof Integer)
            return new int[]{ (int)v };
        if (v instanceof Long)
            return new int[]{ ((Long)v).intValue() };
        if (v instanceof int[])
            return (int[]) v;

        Collection c = ((Collection) v);
        int n = c.size();
        int[] a = new int[n];
        int i=0;
        for (Object e : c) {
            if (e instanceof Integer)
                a[i++] = ((Integer)e);
            else if (e instanceof Long)
                a[i++] = ((Long)e).intValue();
            else
                a[i++] = Integer.parseInt(e.toString());
        }
        return a;
    }

    public static long[] asLongArray(Object v) {
        if (v == null || v instanceof Collection && ((Collection)v).isEmpty())
            return EMPTY_LONG_ARRAY;
        if (v instanceof Integer)
            return new long[]{ (int)v };
        if (v instanceof Long)
            return new long[]{ (long)v };
        if (v instanceof long[])
            return (long[]) v;

        Collection c = ((Collection) v);
        int n = c.size();
        long[] a = new long[n];
        int i=0;
        for (Object e : c) {
            if (e instanceof Integer)
                a[i++] = ((Integer)e);
            else if (e instanceof Long)
                a[i++] = ((Long)e);
            else
                a[i++] = Long.parseLong(e.toString());
        }
        return a;
    }

    public static boolean[] asBooleanArray(Object v) {
        if (v == null || v instanceof Collection && ((Collection)v).isEmpty())
            return EMPTY_BOOLEAN_ARRAY;
        if (v instanceof Boolean)
            return new boolean[]{ (boolean)v };

        Collection c = ((Collection) v);
        int n = c.size();
        boolean[] a = new boolean[n];
        int i=0;
        for (Object e : c) {
            if (e instanceof Boolean)
                a[i++] = ((boolean)e);
            else
                a[i++] = Boolean.parseBoolean(e.toString());
        }
        return a;
    }

    public static String[] asStringArray(Object v) {
        if (v == null || v instanceof Collection && ((Collection)v).isEmpty())
            return EMPTY_STRING_ARRAY;
        if (v instanceof String)
            return new String[]{ (String)v };

        Collection c = ((Collection) v);
        int n = c.size();
        String[] a = new String[n];
        int i=0;
        for (Object e : c) {
            if (e instanceof String)
                a[i++] = ((String)e);
            else
                a[i++] = e.toString();
        }
        return a;
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

    public static boolean get(boolean[] a, int index) {
        if (a == null || a.length == 0 || index < 0)
            return true;
        if (index >= a.length)
            return false;
        else
            return a[index];
    }

    public static boolean[] set(int index, boolean v) {
        return set(null, index, v);
    }

    public static boolean[] set(boolean[] a, int index, boolean v) {
        if (a == null)
            a = new boolean[index+1];
        if (index <= -1)
            index = a.length + index + 1;
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
