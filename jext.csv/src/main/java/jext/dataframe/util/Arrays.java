package jext.dataframe.util;

import java.util.Collection;

public class Arrays {

    public static String toString(int[] data) {
        if (data == null || data.length == 0)
            return "[]";
        if (data.length == 1)
            return "[" + data[0] + "]";
        StringBuilder sb = new StringBuilder("[").append(data[0]);
        for (int i=1; i<data.length; ++i)
            sb.append(", ").append(data[i]);
        return sb.append("]").toString();
    }

    public static String toString(double[] data) {
        if (data == null || data.length == 0)
            return "[]";
        if (data.length == 1)
            return "[" + data[0] + "]";
        StringBuilder sb = new StringBuilder("[").append(data[0]);
        for (int i=1; i<data.length; ++i)
            sb.append(", ").append(data[i]);
        return sb.append("]").toString();
    }

    public static String toString(Object[] data) {
        if (data == null || data.length == 0)
            return "[]";
        if (data.length == 1)
            return "[" + data[0] + "]";
        StringBuilder sb = new StringBuilder("[").append(data[0]);
        for (int i=1; i<data.length; ++i)
            sb.append(", ").append(data[i]);
        return sb.append("]").toString();
    }

    public static String toString(Collection<?> data) {
        if (data == null || data.size() == 0)
            return "[]";
        boolean rest = false;
        StringBuilder sb = new StringBuilder("[");
        for (Object e : data) {
            if (rest) sb.append(", ");
            sb.append(e.toString());
            rest = true;
        }
        return sb.append("]").toString();
    }
}
