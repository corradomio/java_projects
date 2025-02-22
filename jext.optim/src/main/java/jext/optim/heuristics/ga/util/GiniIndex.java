package jext.optim.heuristics.ga.util;

import static jext.util.MathUtils.sq;

public class GiniIndex {

    public static double value(double[] values) {
        int n = values.length;
        double total = 0;
        for(int i=0; i<n; ++i)
            total += values[i];
        return value(values, total);
    }

    public static double value(double[] values, double total) {
        int n = values.length;
        double gi = 1;
        for(int i=0; i<n; ++i)
            gi -= sq(values[i]/total);
        return gi;
    }

}
