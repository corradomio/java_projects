package jext.math.linear.sparse;


import jext.math.linear.sparse.util.Loc;

public class Linear {

    public static void linear(Data r, float s, Data u, float t, Data v) {
        for (int i=0; i<u.n; ++i) {

        }
    }

    // s = u . v
    public static float dotv(Data u, Data v) {
        if (u.n > v.n) {
            Data t = u;
            u = v;
            v = t;
        }

        float s = 0;
        for (Loc loc : u)
            s += u.get(loc)*v.get(loc);
        return s;
    }

}
