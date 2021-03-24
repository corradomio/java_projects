package jext.math.linear.sparse;

public class ToString {

    public static String toStringAsVector(Data data) {
        int n = data.n;
        long[] coords = data.coords;
        float[] values = data.data;
        StringBuilder sb = new StringBuilder("{ ");
        for (int i=0; i<n; ++i)
            sb.append(coords[i]).append(": ").append(values[i]).append(" ");
        return sb.append("}").toString();
    }
}
