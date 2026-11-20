package jext.geotools;

public class Op {

    public static final double TWO_PI = 2*Math.PI;
    public static final double HALF_PI = Math.PI/2;
    //
    // public static int mod(int i, int n) {
    //     int m = i%n;
    //     if (m < 0) m += n;
    //     return m;
    // }

    public static int inc(int i, int n) {
        i++;
        if (i >= n) i -= n;
        return i;
    }
    public static int dec(int i, int n) {
        i--;
        if (i < 0 ) i += n;
        return i;
    }

    // public static int steps(int b, int e, int n) {
    //     if (b < e)
    //         return (e - b);
    //     else
    //         return (e - b) + n;
    // }

    public static double max(double a, double b, double c) {
        return Math.max(Math.max(a, b), c);
    }

}
