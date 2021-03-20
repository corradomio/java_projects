package jext.math.linalg;

public class Matrices {

    public static Matrix identity(int n) {
        return diagonal(Linalg.ones(n));
    }

    public static Matrix diagonal(int n) {
        return diagonal(Linalg.zeros(n));
    }

    public static Matrix diagonal(int n, float v) {
        return diagonal(n).set(v);
    }

    public static Matrix diagonal(float[] diag) {
        return matrix(diag);
    }

    public static Matrix zeros(int n, int m) {
        return matrix(Linalg.zeros(n, m));
    }

    public static Matrix ones(int n, int m) {
        return matrix(Linalg.ones(n, m));
    }

    public static Matrix constant(int n, float v) {
        return constant(n, n, v);
    }

    public static Matrix constant(int n, int m, float v) {
        return matrix(Linalg.zeros(n, m)).set(v);
    }

    public static Matrix matrix(int n) {
        return matrix(n, n);
    }

    public static Matrix matrix(int n, int m) {
        return matrix(Linalg.zeros(n, m));
    }

    public static Matrix matrix(float[] diag) {
        return new DiagonalMatrix(diag);
    }

    public static Matrix matrix(float[][] mat) {
        return new DenseMatrix(mat);
    }

}
