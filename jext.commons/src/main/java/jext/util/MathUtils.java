package jext.util;

public class MathUtils {

    public static int sign(double x) {
        if (x < 0) return -1;
        if (x > 0) return +1;
        return 0;
    }

    public static double sq(double x) { return x*x; }
    public static float  sq(float  x) { return x*x; }

    private static double ILOG2 = (1./Math.log(2));
    private static float ILOG2F = (float)ILOG2;

    public static double log2(double x) { return x == 0 ? 0 : ILOG2*Math.log(x); }
    public static float  log2(float  x) { return x == 0 ? 0 : ILOG2F*(float)Math.log(x); }

    public static float pow(float x, float y) { return (float)Math.pow(x, y); }
    public static double pow(double x, double y) { return Math.pow(x, y); }

    public static float sqrt(float x) { return (float) Math.sqrt(x); }
    public static double sqrt(double x) { return Math.sqrt(x); }
}
