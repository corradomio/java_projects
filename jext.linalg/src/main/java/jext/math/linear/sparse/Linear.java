package jext.math.linear.sparse;

import jext.math.linear.sparse.util.Loc;

public class Linear {

    // s = u . v
    public static float dot(Data u, Data v) {
        Coords c = u.intersection(v);
        // Coords c = u;
        // float s = 0;
        // for (Loc loc : c)
        //     s += u.get(loc)*v.get(loc);
        // return s;
        // if (u.n > v.n) { Data t = u; u = v; v = t; }
        float s = 0;
        for (Loc i : c)
            s += u.get(i)*v.get(i);
        return s;
    }

    // R = A[i:].v
    public static float dot(Data A, int i, Data v) {
        float s = 0;
        for(Loc r : A.row(i))
            for (Loc c : v)
                s += A.get(r)*v.get(c);
        return s;
    }

    // R = A[i:].B[:j]
    public static float dot(Data A, int i, Data B, int j) {
        float s = 0;
        for(Loc r : A.row(i))
            for (Loc c : B.col(j))
                s += A.get(r)*B.get(c);
        return s;
    }

    public static void outer(Data R, Data u, Data v) {
        for(Loc i : u)
            for(Loc j : v)
                R.set(i.at(), j.at(), u.get(i)*v.get(j));
    }

    // ----------------------------------------------------------------------
    // Linear combinations
    // ----------------------------------------------------------------------

    // r = s*u + t*v
    // R = s*A + t*B
    public static void linear(Data r, float s, Data u, float t, Data v) {
        Coords c = u.union(v);
        // s == 1 && t == 0
        if (s == 1 && t == 0)
            for (Loc loc : c)  r.set(loc, u.get(loc));
        else if (t == 0)
            for (Loc loc : c)  r.set(loc, s*u.get(loc));
        else if (s == 0 && t == 1)
            for (Loc loc : c)  r.set(loc, v.get(loc));
        else if (s == 0)
            for (Loc loc : c)  r.set(loc, t*v.get(loc));
        else
            for (Loc loc : c)  r.set(loc, s*u.get(loc) + t*v.get(loc));
    }


}
