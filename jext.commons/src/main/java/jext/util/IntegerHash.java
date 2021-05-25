package jext.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class IntegerHash {

    public static int hash(Object... values) {
        return Objects.hash(values);
    }

    public static int hashCode(Object o) {
        if (o == null)
            return 0;
        if (o instanceof String)    // speedup
            return o.hashCode();
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
    // Continue to compose hash code
    // ----------------------------------------------------------------------

    /** Compose all hashes */
    public static int concat(int... hashes) {
        if (hashes == null || hashes.length == 0)
            return 0;
        int result = 0;
        for (int hash : hashes)
            result = result*31 + hash;
        return result;
    }

    /** Continue to compose the hash code */
    public static int append(int hash, Object... values) {
        if (values == null)
            return hash;

        int result = hash;
        for (Object element : values)
            result = 31 * result + Objects.hashCode(element);

        return result;
    }

    public static <E> int append(int hash, List<E> c) {
        if (c == null)
            return hash;

        int result = hash;
        for (E e : c)
            result = 31*result + Objects.hashCode(e);
        return result;
    }

    public static <E> int append(int hash, Collection<E> c) {
        if (c == null)
            return hash;

        int result = hash;
        Iterator<?> i = c.iterator();
        while (i.hasNext()) {
            result += Objects.hashCode(i.next());
        }
        return result;
    }

    public static <K,V> int append(int hash, Map<K,V> m) {
        if (m == null)
            return hash;

        int result = 0;
        for(Map.Entry<K,V> e : m.entrySet())
            result += Objects.hashCode(e.getKey()) ^ Objects.hashCode(e.getValue());

        return result;
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
}
