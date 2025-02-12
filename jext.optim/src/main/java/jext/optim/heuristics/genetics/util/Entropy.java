package jext.optim.heuristics.genetics.util;

public class Entropy {

    private static double ILOG2 = 1./Math.log(2);
    public static double log2(double x) {
        return x == 0 ? 0 : ILOG2*Math.log(x);
    }

    public static double value(double[] values) {
        int n = values.length;
        double total = 0;
        for(int i=0; i<n; ++i)
            total += values[i];
        return value(values, total);
    }

    public static double value(double[] values, double total) {
        int n = values.length;
        double ent = 0;
        for(int i=0; i<n; ++i) {
            double p = values[i]/total;
            ent -= p*log2(p);
        }

        return ent;
    }

}
