package jext.optim.heuristics.ga.util;

import static jext.util.MathUtils.log2;

public class Entropy {

    public static double value(double[] values) {
        int n = values.length;
        double total = 0;
        for(int i=0; i<n; ++i)
            total += values[i];
        return value(values, total);
    }

    public static float value(float[] values, float total) {
        int n = values.length;
        float ent = 0;
        for(int i=0; i<n; ++i) {
            double p = values[i]/total;
            ent -= p*log2(p);
        }

        return ent;
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
