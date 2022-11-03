package jext.graph.util;


import java.util.Arrays;
import java.util.List;

public class PropertyUtils {

    private static final boolean[] NO_BOOLS = new boolean[0];
    private static final long[] NO_LONGS = new long[0];
    private static final double[] NO_DOUBLES = new double[0];
    private static final String[] NO_STRINGS = new String[0];
    private static final Object[] NO_OBJECTS = new Object[0];

    // -- bool

    public static boolean atBoolArray(Object v, int rev) {
        boolean[] array = asBoolArray(v);
        int len = array.length;
        if (rev < len)
            return array[rev];
        if (len == 0)
            return false;
        else
            return array[len-1];
    }

    public static boolean[] asBoolArray(Object v) {
        if (v == null)
            return NO_BOOLS;
        if (v instanceof boolean[])
            return (boolean[]) v;
        if (v instanceof Boolean[]) {
            Boolean[] b = (Boolean[]) v;
            int n = b.length;
            boolean[] a = new boolean[n];
            for (int i=0; i<n; ++i)
                a[i] = b[i];
            return a;
        }
        if (v instanceof List) {
            List<Boolean> l = (List<Boolean>) v;
            int n = l.size();
            boolean[] a = new boolean[n];
            for (int i=0; i<n; ++i)
                a[i] = l.get(i);
            return a;
        }
        throw new UnsupportedOperationException();
    }

    public static boolean[] boolArray(int at, boolean value) {
        return boolArray(null, at, value);
    }

    public static boolean[] boolArray(Object v, int at, boolean value) {
        boolean[] a = asBoolArray(v);
        int n = a == null ? -1 : a.length;
        if (a == null) {
            a = new boolean[at + 1];
        }
        else if (n <= at) {
            // boolean[] b = new boolean[at + 1];
            // System.arraycopy(a, 0, b, 0, n);
            boolean defval = n > 0 ? a[n-1] : false;
            boolean[] b = Arrays.copyOf(a, at+1);
            Arrays.fill(b, n, at, defval);
            a = b;
        }
        a[at] = value;
        return a;
    }

    // -- int/long

    public static long atLongArray(Object v, int rev) {
        long[] array = asLongArray(v);
        int len = array.length;
        if (rev < len)
            return array[rev];
        if (len == 0)
            return 0;
        else
            return array[len-1];
    }

    public static long[] asLongArray(Object v) {
        if (v == null)
            return NO_LONGS;
        if (v instanceof long[])
            return (long[]) v;
        if (v instanceof Number[]) {
            Number[] b = (Number[]) v;
            int n = b.length;
            long[] a = new long[n];
            for (int i=0; i<n; ++i)
                a[i] = b[i].longValue();
            return a;
        }
        if (v instanceof List) {
            List<Number> l = (List<Number>) v;
            int n = l.size();
            long[] a = new long[n];
            for (int i=0; i<n; ++i)
                a[i] = l.get(i).longValue();
            return a;
        }
        throw new UnsupportedOperationException();
    }

    public static long[] longArray(int at, long value) {
        return longArray(null, at, value);
    }

    public static long[] longArray(Object v, int at, long value) {
        long[] a = asLongArray(v);
        int n = a == null ? -1 : a.length;
        if (a == null) {
            a = new long[at + 1];
        }
        else if (n <= at) {
            // long[] b = new long[at + 1];
            // System.arraycopy(a, 0, b, 0, n);
            long defval = n > 0 ? a[n-1] : 0;
            long[] b = Arrays.copyOf(a, at+1);
            Arrays.fill(b, n, at, defval);
            a = b;
        }
        a[at] = value;
        return a;
    }

    // -- float/double

    public static double atDoubleArray(Object v, int rev) {
        double[] array = asDoubleArray(v);
        int len = array.length;
        if (rev < len)
            return array[rev];
        if (len == 0)
            return 0;
        else
            return array[len-1];
    }

    public static double[] asDoubleArray(Object v) {
        if (v == null)
            return NO_DOUBLES;
        if (v instanceof double[])
            return (double[]) v;
        if (v instanceof Number[]) {
            Number[] b = (Number[]) v;
            int n = b.length;
            double[] a = new double[n];
            for (int i=0; i<n; ++i)
                a[i] = b[i].doubleValue();
            return a;
        }
        if (v instanceof List) {
            List<Number> l = (List<Number>) v;
            int n = l.size();
            double[] a = new double[n];
            for (int i=0; i<n; ++i)
                a[i] = l.get(i).doubleValue();
            return a;
        }
        throw new UnsupportedOperationException();
    }

    public static double[] doubleArray(int at, double value) {
        return doubleArray(null, at, value);
    }

    public static double[] doubleArray(Object v, int at, double value) {
        double[] a = asDoubleArray(v);
        int n = a == null ? -1 : a.length;
        if (a == null) {
            a = new double[at + 1];
        }
        else if (n <= at) {
            // double[] b = new double[at + 1];
            // System.arraycopy(a, 0, b, 0, n);
            double defval = n > 0 ? a[n-1] : 0;
            double[] b = Arrays.copyOf(a, at+1);
            Arrays.fill(b, n, at, defval);
            a = b;
        }
        a[at] = value;
        return a;
    }

    // -- string

    public static String atStringArray(Object v, int rev) {
        String[] array = asStringArray(v);
        int len = array.length;
        if (rev < len)
            return array[rev];
        if (len == 0)
            return null;
        else
            return array[len-1];
    }

    public static String[] asStringArray(Object v) {
        if (v == null)
            return NO_STRINGS;
        if (v instanceof String[])
            return (String[]) v;
        if (v instanceof List) {
            List<Object> l = (List<Object>) v;
            int n = l.size();
            String[] a = new String[n];
            for (int i=0; i<n; ++i) {
                Object e = l.get(i);
                a[i] = e != null ? e.toString() : null;
            }
            return a;
        }
        throw new UnsupportedOperationException();
    }

    public static String[] stringArray(int at, String value) {
        return stringArray(null, at, value);
    }

    public static String[] stringArray(Object v, int at, String value) {
        String[] a = asStringArray(v);
        int n = a == null ? -1 : a.length;
        if (a == null) {
            a = new String[at + 1];
        }
        else if (n <= at) {
            // String[] b = new String[at + 1];
            // System.arraycopy(a, 0, b, 0, n);
            String defval = n > 0 ? a[n-1] : "";
            String[] b = Arrays.copyOf(a, at+1);
            Arrays.fill(b, n, at, defval);
            a = b;
        }
        a[at] = value;
        return a;
    }

    // -- object

    public static Object atObjectArray(Object v, int rev) {
        Object[] array = asObjectArray(v);
        int len = array.length;
        if (rev < len)
            return array[rev];
        if (len == 0)
            return null;
        else
            return array[len-1];
    }

    public static Object[] asObjectArray(Object v) {
        if (v == null)
            return NO_OBJECTS;
        if (v instanceof Object[])
            return (Object[]) v;
        if (v instanceof List) {
            List<Object> l = (List<Object>) v;
            int n = l.size();
            Object[] a = new Object[n];
            for (int i=0; i<n; ++i) {
                a[i] = l.get(i);
            }
            return a;
        }
        throw new UnsupportedOperationException();
    }

    public static Object[] objectArray(int rev, Object value) {
        Object[] a = new Object[rev+1];
        a[rev] = value;
        return a;
    }

    public static Object[] objectArray(Object v, int at, Object value) {
        Object[] a = asObjectArray(v);
        int n = a == null ? -1 : a.length;
        if (a == null) {
            a = new Object[at + 1];
        }
        else if (n <= at) {
            // Object[] b = new Object[at + 1];
            // System.arraycopy(a, 0, b, 0, n);
            Object defval = n > 0 ? a[n-1] : null;
            Object[] b = Arrays.copyOf(a, at+1);
            Arrays.fill(b, n, at, defval);
            a = b;
        }
        a[at] = value;
        return a;
    }

    // -- append/appendUnique

    public static int[] append(int[] a, int e) {
        if (a == null)
            return new int[]{e};

        int n = a.length;
        int[] b = new int[n+1];
        System.arraycopy(a, 0, b, 0, n);
        b[n] = e;
        return b;
    }

    public static int[] appendUnique(int[] a, int e) {
        int n = a==null ? -1 : a.length;
        for (int i=0; i<n; ++i)
            if (a[i] == e)
                return a;

        return append(a, e);
    }
}
