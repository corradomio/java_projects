package jext.graph.util;

public class PropertyUtils {

    public static boolean[] boolArray(int at, boolean value) {
        return boolArray(null, at, value);
    }

    public static boolean[] boolArray(boolean[] a, int at, boolean value) {
        int n = a == null ? -1 : a.length;
        if (a == null) {
            a = new boolean[at + 1];
        }
        else if (n <= at) {
            boolean[] b = new boolean[at + 1];
            System.arraycopy(a, 0, b, 0, n);
            a = b;
        }
        a[at] = value;
        return a;
    }


    public static long[] longArray(int at, long value) {
        return longArray(null, at, value);
    }

    public static long[] longArray(long[] a, int at, long value) {
        int n = a == null ? -1 : a.length;
        if (a == null) {
            a = new long[at + 1];
        }
        else if (n <= at) {
            long[] b = new long[at + 1];
            System.arraycopy(a, 0, b, 0, n);
            a = b;
        }
        a[at] = value;
        return a;
    }

    public static double[] doubleArray(int at, double value) {
        return doubleArray(null, at, value);
    }

    public static double[] doubleArray(double[] a, int at, double value) {
        int n = a == null ? -1 : a.length;
        if (a == null) {
            a = new double[at + 1];
        }
        else if (n <= at) {
            double[] b = new double[at + 1];
            System.arraycopy(a, 0, b, 0, n);
            a = b;
        }
        a[at] = value;
        return a;
    }

    public static String[] stringArray(int at, String value) {
        return stringArray(null, at, value);
    }

    public static String[] stringArray(String[] a, int at, String value) {
        int n = a == null ? -1 : a.length;
        if (a == null) {
            a = new String[at + 1];
        }
        else if (n <= at) {
            String[] b = new String[at + 1];
            System.arraycopy(a, 0, b, 0, n);
            a = b;
        }
        a[at] = value;
        return a;
    }

    public static Object[] objectArray(int rev, Object value) {
        Object[] a = new Object[rev+1];
        a[rev] = value;
        return a;
    }

    public static Object[] objectArray(Object[] a, int at, Object value) {
        int n = a == null ? -1 : a.length;
        if (a == null) {
            a = new Object[at + 1];
        }
        else if (n <= at) {
            Object[] b = new Object[at + 1];
            System.arraycopy(a, 0, b, 0, n);
            a = b;
        }
        a[at] = value;
        return a;
    }


    public static int[] appendUnique(int[] array, int e) {
        if (array == null)
            return new int[]{e};
        for (int i=0; 1<array.length; ++i)
            if (array[i] == e)
                return array;

        int n = array.length;
        int[] narray = new int[n+1];
        System.arraycopy(array, 0, narray, 0, n);
        narray[n ] = e;
        return narray;
    }
}
