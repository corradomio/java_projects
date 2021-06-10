package jext.math.linear.sparse;

public class Linear {

    // s = u . v
    public static float dot(Data u, Data v) {
        float s = 0;
        for (Loc i : u.rows())
            s += u.get(i)*v.get(i);
        return s;
    }

    // R = A[i:].v
    public static float dot(Data A, int i, Data v) {
        float s = 0;
        for(Loc r : A.cols(i))
            s += A.get(r)*v.get(r.c);
        return s;
    }

    // R[ij] = A[i:].B[:j]
    public static float dot(Data A, int i, Data B, int j) {
        float s = 0;
        for(Loc c : A.cols(i))
            s += A.get(c)*B.get(c.c, j);
        return s;
    }

    // R = A.B
    public static void dot(Data R, Data A, Data B) {
        for(Loc r : A.rows())
            for (Loc c : B.cols()) {
                float v = dot(A, r.r, B, c.c);
                R.add(r.r, c.c, v);
            }
    }

    // R = u outer v
    public static void outer(Data R, Data u, Data v) {
        for(Loc i : u.rows())
            for(Loc j : v.rows())
                R.set(i.r, j.r, u.get(i)*v.get(j));
    }

    // ----------------------------------------------------------------------
    // Linear combinations
    // ----------------------------------------------------------------------

    // r = s*u + t*v
    // R = s*A + t*B
    public static void linear(Data r, float s, Data u, float t, Data v) {
        // s == 1 && t == 0

        Coords c = u.union(v);

        // (1,0) r = u
        if (s == 1 && t == 0)
            for (Loc loc : u)  r.set(loc, u.get(loc));
        // (s,0) r = s*u
        else if (t == 0)
            for (Loc loc : u)  r.set(loc, s*u.get(loc));
        // (0,1) r = v
        else if (s == 0 && t == 1)
            for (Loc loc : v)  r.set(loc, v.get(loc));
        // (0,t) r = t*v
        else if (s == 0)
            for (Loc loc : v)  r.set(loc, t*v.get(loc));
        // (1,1) r = u + v
        else if (s == 1 && t == 1)
            for (Loc loc : c)  r.set(loc, u.get(loc) + v.get(loc));
        // (s,t) r = s*u + t*v
        else
            for (Loc loc : c)  r.set(loc, s*u.get(loc) + t*v.get(loc));
    }

}
