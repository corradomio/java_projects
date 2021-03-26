package jext.math.linear.sparse;


import jext.math.linear.sparse.util.Loc;

public class Linear {

    // s = u . v
    public static float dotv(Data u, Data v) {
        Coords c = u.intersection(v);
        float s = 0;
        for (Loc loc : c)
            s += u.get(loc)*v.get(loc);
        return s;
    }


    // ----------------------------------------------------------------------
    // Linear combinations
    // ----------------------------------------------------------------------

    // r = s*u + t*v
    // R = s*A + t*B
    public static void linear(Data r, float s, Data u, float t, Data v) {
        Coords c = u.union(v);
        for (Loc loc : c)
            r.set(loc, s*u.get(loc) + t*v.get(loc));
    }


}
