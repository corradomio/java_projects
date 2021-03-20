package jext.math.linalg;

public class DenseVector implements Vector {

    private final int n;
    private final Dim dim;
    public final float[] vec;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public DenseVector(float[] v) {
        vec = v;
        n = v.length;
        dim = new Dim(n);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public Dim dim() { return dim; }

    public int len() { return n; }

    public float[] data() {
        return vec;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public float at(int i) { return vec[i]; }

    public Vector at(int i, float v) {
        vec[i] = v;
        return this;
    }

    public Vector set(float v){
        for(int i=0; i<n; ++i)
            vec[i] = v;
        return this;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    // r = t*v + s*u
    public Vector linear(float s, float t, Vector v) {
        float[] r = Linalg.zeros(n);
        Linear.linear(r, s, vec, t, ((DenseVector)v).vec);
        return Vectors.vect(r);
    }

    public float dot(Vector u) {
        return Multiply.dot(vec, u.data());
    }

    public Vector hprod(Vector u) {
        float[] r = Linalg.zeros(n);
        Multiply.hprod(r, vec, u.data());
        return Vectors.vect(r);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return toString("%.3f");
    }

    public String toString(String fmt) {
        StringBuilder sb = new StringBuilder(String.format("Vector[%d]", n)).append("{");
        if (n > 0)
            sb.append(String.format(fmt, vec[0]));
        for(int i=1; i<n; ++i)
            sb.append(", ").append(String.format(fmt, vec[i]));
        return sb.append("}").toString();
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}
