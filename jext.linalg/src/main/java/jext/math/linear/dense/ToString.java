package jext.math.linear.dense;

public class ToString {

    public static String toString(float[] v, String fmt) {
        return toString(v, 0, v.length, fmt);
    }

    public static String toString(float[] A, int m, String fmt) {
        int n = A.length/m;
        StringBuilder sb = new StringBuilder("[\n");
        if (n > 0)
            sb.append("  ").append(toString(A, 0, m, fmt)).append("\n");
        for (int i=1,j=m; i<n; ++i,j+=m)
            sb.append("  ").append(toString(A, j, m, fmt)).append("\n");
        return sb.append("]").toString();
    }

    private static String toString(float[] v, int i, int n, String fmt) {
        StringBuilder sb = new StringBuilder("[");
        if (n > 0)
            sb.append(String.format(fmt, v[i++]));
        for (int l=1; l<n; ++l)
            sb.append(", ").append(String.format(fmt, v[i++]));
        return sb.append("]").toString();
    }

}
