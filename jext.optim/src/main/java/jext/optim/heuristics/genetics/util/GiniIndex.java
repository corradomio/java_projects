package jext.optim.heuristics.genetics.util;

import static jext.util.MathUtils.sq;

public class GiniIndex {

    public static float value(float[] values) {
        int n = values.length;
        float total = 0;
        for(int i=0; i<n; ++i)
            total += values[i];
        return value(values, total);
    }

    public static float value(float[] values, float total) {
        int n = values.length;
        float gi = 1;
        for(int i=0; i<n; ++i)
            gi -= sq(values[i]/total);
        return gi;
    }

}
