package jext.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class IntegerHash {

    public static int digest(Object value) {
        return hashCode(value);
    }

    public static int hash(Object... values) {
        return Objects.hash(values);
    }

    public static long concat(int... hashes) {
        int result = 0;
        for(int hash : hashes)
            result = result*31 + hash;
        return result;
    }

    // ----------------------------------------------------------------------
    // Hash code of collections & maps
    // ----------------------------------------------------------------------

    public static <E> int hashCode(List<E> c) {
        return c.hashCode();
    }

    public static <E> int hashCode(Collection<E> c) {
        return c.hashCode();
    }

    public static <K,V> int hashCode(Map<K, V> m) {
        return m.hashCode();
    }

    // ----------------------------------------------------------------------
    // Hash code of objects
    // ----------------------------------------------------------------------

    public static int hashCode(Object o) {
        if (o == null)
            return 0;
        // if (o instanceof String)
        //     return hashCode((String) o);
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
            // if (o instanceof Long)
            //     return ((Long) o).intValue();
            // if (o instanceof Double)
            //     return (int)Double.doubleToLongBits((Double) o);
        else
            return o.hashCode();
    }

    // ----------------------------------------------------------------------
    // Hash code of arrays
    // ----------------------------------------------------------------------

    public static int hashCode(long a[]) {
        return Arrays.hashCode(a);
    }

    public static int hashCode(int a[]) {
        return Arrays.hashCode(a);
    }

    public static int hashCode(short a[]) {
        return Arrays.hashCode(a);
    }

    public static int hashCode(char a[]) {
        return Arrays.hashCode(a);
    }

    public static int hashCode(byte a[]) {
        return Arrays.hashCode(a);
    }

    public static int hashCode(boolean a[]) {
        return Arrays.hashCode(a);
    }

    public static int hashCode(float a[]) {
        return Arrays.hashCode(a);
    }

    public static int hashCode(double a[]) {
        return Arrays.hashCode(a);
    }

    // public static long hashCode(Object a[]) {
    public static int hashCode(Object... a) {
        return Arrays.hashCode(a);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
