package jext.util;

import java.util.Collection;
import java.util.Map;

/*
    Objects.hash(Object... values)
    Objects.hashCode(Object o);
    Arrays.hashCode(type[] a)
 */

public class LongHash {

    /**
     * Create a hash code based on the list of objects
     */
    public static long hash(Object... values) {
        // values is Object[]
        return hashCode(values);
    }

    /**
     * Concatenate multiple hash codes
     */
    public static long concat(long... hashes) {
        long result = 0;
        for(long hash : hashes)
            result = result*31 + hash;
        return result;
    }

    public static long concat(long hashCode, String hexString) {
        return concat(hashCode, fromString(hexString));
    }

    // ----------------------------------------------------------------------
    // Hash code for collections & maps
    // ----------------------------------------------------------------------

    /**
     * Hash code for Collection/List/Set and any other collection implementing
     * Iterable interface
     */
    public static <E> long hashCode(Iterable<E> iter) {
        if (iter == null)
            return 0;

        long result = 1;
        for (E e : iter)
            result = 31*result + (e==null ? 0 : hashCode(e));
        return result;
    }

    // public static <E> long hashCode(List<E> c) {
    //     if (c == null)
    //         return 0;
    //
    //     long result = 1;
    //     for (E e : c)
    //         result = 31*result + (e==null ? 0 : hashCode(e));
    //     return result;
    // }

    // public static <E> long hashCode(Set<E> c) {
    //     if (c == null)
    //         return 0;
    //
    //     long result = 1;
    //     for (E e : c)
    //         result = 31*result + (e==null ? 0 : hashCode(e));
    //     return result;
    // }

    // public static <E> long hashCode(Collection<E> c) {
    //     if (c == null)
    //         return 0;
    //
    //     long result = 1;
    //     for (E e : c)
    //         result = 31*result + (e==null ? 0 : hashCode(e));
    //     return result;
    // }

    /**
     * Hash code for map and dictionaries.
     * Note: the hashCode of a emptyMap is 0
     */
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

    /**
     * Specialized hashCode function to use with source code.
     * MacOS, Windows and Linux use different 'endOfLine' character sequences
     * This has as side effect that the same source file has a different digest.
     * This, in generale, is not a problem. The problem begins when the same
     * source file is modified by different platforms. In this case, the file
     * seems to be modified everywhere.
     *
     * Solution: it is applied a 'text normalization' removing multiple spaces
     * and considering '\t', '\f', '\n', and '\r' as ' ' (space
     *
     * @param value
     * @return
     */
    public static long hashCodeByLine(String value) {
        if (value == null)
            return 0;

        long h = 0;
        int length = value.length();
        for (int i = 0; i < length; i++) {
            char ch = value.charAt(i);
            if (ch == '\f' || ch == '\n' || ch == '\r')
                continue;
            h = 31 * h + ch;
        }
        return h;
    }

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

        if (o instanceof Iterable)
            return hashCode((Collection<?>) o);
        // if (o instanceof List)
        //     return hashCode((List<?>) o);
        // if (o instanceof Collection)
        //     return hashCode((Collection<?>) o);

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
        if (o instanceof Float)
            return Float.floatToIntBits((Float) o);
        if (o instanceof Double)
            return Double.doubleToLongBits((Double) o);

        else
            return o.hashCode();
    }

    // ----------------------------------------------------------------------
    // Hash code of arrays
    // ----------------------------------------------------------------------

    public static long hashCode(long a[]) {
        if (a == null)
            return 0;

        long result = 1;
        for (long element : a) {
            result = 31 * result + element;
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
            result = 31 * result + Double.doubleToLongBits(element);
        }
        return result;
    }

    public static long hashCode(Object a[]) {
        if (a == null)
            return 0;

        long result = 1;
        for (Object element : a)
            result = 31 * result + (element == null ? 0 : hashCode(element));

        return result;
    }

    // ----------------------------------------------------------------------
    // Conversion
    // ----------------------------------------------------------------------

    public static String asString(Object... values) {
        return toString(hash(values));
    }

    public static String toString(long hashCode) {
        return Long.toUnsignedString(hashCode, Character.MAX_RADIX);
    }

    public static long fromString(String hashCode) {
        if (hashCode.isEmpty())
            return 0;
        else
            return Long.parseUnsignedLong(hashCode, Character.MAX_RADIX);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
