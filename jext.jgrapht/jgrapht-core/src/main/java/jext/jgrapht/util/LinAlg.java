package jext.jgrapht.util;

public class LinAlg {

    public static double[] newVector(int n) {
        return new double[n];
    }

    public static double[][] newMatrix(int n) {
        return newMatrix(n, n);
    }

    public static double[][] newMatrix(int nr, int nc) {
        double[][] m = new double[nr][];
        for (int i=0; i<nr; ++i)
            m[i] = new double[nc];
        return m;
    }

    public static int[] intVector(int n) {
        return new int[n];
    }

    public static int[][] intMatrix(int n) {return intMatrix(n, n); }

    public static int[][] intMatrix(int nr, int nc) {
        int[][] m = new int[nr][];
        for (int i=0; i<nr; ++i)
            m[i] = new int[nc];
        return m;
    }
}
