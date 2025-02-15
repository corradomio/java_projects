package jext.optim.heuristics.genetics.util;

import java.util.random.RandomGenerator;

/*
    Introduction to Evolutionary Computation - 2015
    pag. 24
 */

public class BoxMuller {

    public static void nextGaussian(float[] g, float mean, float variance, RandomGenerator rng) {
        float x=0, y=0, t, w = 1;
        while (w >= 1) {
            x = rng.nextFloat(-1, 1);
            y = rng.nextFloat(-1, 1);
            w = x*x + y*y;
        }

        t = (float) (variance*Math.sqrt(-2*Math.log(w)/w));

        g[0] = mean + x*t;
        g[1] = mean + y*t;
    }
}
