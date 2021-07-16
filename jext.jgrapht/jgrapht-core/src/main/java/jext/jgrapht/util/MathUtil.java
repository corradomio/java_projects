package jext.jgrapht.util;

public class MathUtils {

    public static long   sq(long x) { return x*x; }
    public static double sq(double x) { return x*x; }
    public static double div(double x, double y) { return y != 0. ? x/y : 0.; }
    public static double sqrt(double x) { return Math.sqrt(x); }
    public static double log2(double x) { return x == 0? 0. : Math.log(x)/Math.log(2); }
    public static double chop(double x) { return x < 0 ? 0. : x; }

    public static int chop(int min, int x, int max) {
        if (x < min) return min;
        if (x > max) return max;
        return x;
    }

    public static boolean inRange(double x, double min, double max) {
        return inRange(x, min, max, true, true);
    }
    public static boolean inRange(double x, double min, double max, boolean minIncl, boolean maxIncl) {
        if (minIncl && maxIncl)
            return min <= x && x <= max;
        if (!minIncl && maxIncl)
            return min < x && x <= max;
        if (minIncl && !maxIncl)
            return min <= x && x < max;
        else
            return min < x && x < max;
    }

    public static boolean inRange(long x, long min, long max) {
        return inRange(x, min, max, true, true);
    }
    public static boolean inRange(long x, long min, long max, boolean minIncl, boolean maxIncl) {
        if (minIncl && maxIncl)
            return min <= x && x <= max;
        if (!minIncl && maxIncl)
            return min < x && x <= max;
        if (minIncl && !maxIncl)
            return min <= x && x < max;
        else
            return min < x && x < max;
    }

    public static int sum(int[] v) {
        int s = 0;
        for(int e : v) s += e;
        return s;
    }

    public static long sum(long[] v) {
        long s = 0;
        for(long e : v) s += e;
        return s;
    }

    public static float sum(float[] v) {
        float s = 0;
        for(float e : v) s += e;
        return s;
    }

    public static double sum(double[] v) {
        double s = 0;
        for(double e : v) s += e;
        return s;
    }


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
