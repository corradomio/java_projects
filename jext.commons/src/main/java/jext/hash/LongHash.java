package jext.hash;

import jext.util.HashCode64;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*
    Objects.hash(Object... values)
    Objects.hashCode(Object o);
    Arrays.hashCode(type[] a)
 */

public class LongHash {

    public static long hash(Object... values) {
        return hashCode(values);
    }

    // ----------------------------------------------------------------------
    // Continue to compose hash code
    // ----------------------------------------------------------------------

    /** Continue to compose the hash code */
    public static long append(long hash, Object... values) {
        if (values == null)
            return hash;

        long result = hash;
        for (Object element : values)
            result = 31 * result + (element == null ? 0 : hashCode(element));

        return result;
    }

    public static <E> long append(long hash, List<E> c) {
        if (c == null)
            return hash;

        long result = hash;
        for (E e : c)
            result = 31*result + (e==null ? 0 : hashCode(e));
        return result;
    }

    public static <E> long append(long hash, Collection<E> c) {
        if (c == null)
            return hash;

        long result = hash;
        Iterator<?> i = c.iterator();
        while (i.hasNext()) {
            Object obj = i.next();
            if (obj != null)
                result += hashCode(obj);
        }
        return result;
    }

    public static <K,V> long append(long hash, Map<K,V> m) {
        if (m == null)
            return hash;

        long result = 0;
        for(Map.Entry<K,V> e : m.entrySet())
            result += hashCode(e.getKey()) ^ hashCode(e.getValue());

        return result;
    }

    // ----------------------------------------------------------------------
    // Hash code of arrays
    // ----------------------------------------------------------------------

    public static long hashCode(long a[]) {
        if (a == null)
            return 0;

        long result = 1;
        for (long element : a) {
            long elementHash = element ^ (element >>> 32);
            result = 31 * result + elementHash;
        }

        return result;
    }

    public static long hashCode(int a[]) {
        if (a == null)
            return 0;

        long result = 1;
        for (int element : a)
            result = 31 * result + element;

        return result;
    }

    public static long hashCode(short a[]) {
        if (a == null)
            return 0;

        long result = 1;
        for (short element : a)
            result = 31 * result + element;

        return result;
    }

    public static long hashCode(char a[]) {
        if (a == null)
            return 0;

        long result = 1;
        for (char element : a)
            result = 31 * result + element;

        return result;
    }

    public static long hashCode(byte a[]) {
        if (a == null)
            return 0;

        long result = 1;
        for (byte element : a)
            result = 31 * result + element;

        return result;
    }

    public static long hashCode(boolean a[]) {
        if (a == null)
            return 0;

        long result = 1;
        for (boolean element : a)
            result = 31 * result + (element ? 1231 : 1237);

        return result;
    }

    public static long hashCode(float a[]) {
        if (a == null)
            return 0;

        long result = 1;
        for (float element : a)
            result = 31 * result + Float.floatToIntBits(element);

        return result;
    }

    public static long hashCode(double a[]) {
        if (a == null)
            return 0;

        long result = 1;
        for (double element : a) {
            long bits = Double.doubleToLongBits(element);
            result = 31 * result + (int)(bits ^ (bits >>> 32));
        }
        return result;
    }

    // public static long hashCode(Object a[]) {
    public static long hashCode(Object... a) {
        if (a == null)
            return 0;

        long result = 1;
        for (Object element : a)
            result = 31 * result + (element == null ? 0 : hashCode(element));

        return result;
    }

    // ----------------------------------------------------------------------
    // Hash code of collections & maps
    // ----------------------------------------------------------------------

    public static <E> long hashCode(List<E> c) {
        long result = 1;
        for (E e : c)
            result = 31*result + (e==null ? 0 : hashCode(e));
        return result;
    }

    public static <E> long hashCode(Collection<E> c) {
        if (c == null)
            return 0;

        long result = 0;
        Iterator<E> i = c.iterator();
        while (i.hasNext()) {
            E obj = i.next();
            if (obj != null)
                result += hashCode(obj);
        }
        return result;
    }

    public static <K,V> long hashCode(Map<K, V> m) {
        if (m == null)
            return 0;

        long result = 0;
        for(Map.Entry<K,V> e : m.entrySet())
            result += hashCode(e.getKey()) ^ hashCode(e.getValue());

        return result;
    }

    // ----------------------------------------------------------------------
    // Hash code of objects
    // ----------------------------------------------------------------------

    public static long hashCode(String value) {
        if (value == null)
            return 0;

        long h = 0;
        int length = value.length();
        for (int i = 0; i < length; i++) {
            h = 31 * h + value.charAt(i);
        }
        return h;
    }

    public static long hashCode(Object o) {
        if (o == null)
            return 0;
        if (o instanceof String)
            return hashCode((String) o);
        if (o instanceof HashCode64)
            return ((HashCode64) o).hashCode64();
        if (o instanceof List)
            return hashCode((List<?>) o);
        if (o instanceof Collection)
            return hashCode((Collection<?>) o);
        if (o instanceof long[])
            return hashCode((long[]) o);
        if (o instanceof int[])
            return hashCode((int[]) o);
        if (o instanceof short[])
            return hashCode((short[]) o);
        if (o instanceof char[])
            return hashCode((char[]) o);
        if (o instanceof byte[])
            return hashCode((byte[]) o);
        if (o instanceof boolean[])
            return hashCode((boolean[]) o);
        if (o instanceof float[])
            return hashCode((float[]) o);
        if (o instanceof double[])
            return hashCode((double[]) o);
        if (o instanceof Object[])
            return hashCode((Object[]) o);
        if (o instanceof Long)
            return (Long) o;
        if (o instanceof Double)
            return Double.doubleToLongBits((Double) o);
        else
            return o.hashCode();
    }

}
