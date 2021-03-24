package jext.math.linear.sparse;

public class Linear {

    public static void linear(Data r, float s, Data u, float t, Data v) {
        for (int i=0; i<u.n; ++i) {

        }
    }

    // s = u . v
    public static float dotv(Data u, Data v) {
        int n = Math.min(u.n, v.n);

        float s = 0;

        for (int i=0; i<n; ++i) {
            long loc = u.coords[i];
            int j = v.locate(loc, false);
            if (j != -1)
                s += u.data[i]*v.data[j];
        }
        return s;
    }

}
