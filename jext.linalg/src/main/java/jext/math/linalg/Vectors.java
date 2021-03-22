package jext.math.linalg;

public class Vectors {

    public static Vector zeros(int n) {
        return vect(Linalg.zeros(n));
    }

    public static Vector ones(int n) {
        return vect(Linalg.ones(n));
    }

    public static Vector vect(int n, float v) {
        return zeros(n).set(v);
    }

    public static Vector vect(float[] d) {
        return new DenseVector(d);
    }

}
