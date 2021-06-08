package org.hls.check;

import jext.math.Interval;
import jext.math.distribution.CDFSampler;
import jext.math.distribution.ContinuousDistribution;
import jext.math.distribution.NormalDistribution;
import jext.math.random.JavaRandomGenerator;
import jext.math.random.RandomGenerator;

public class Main {

    public static void main(String[] args) {
        // Random r = new Random(0);
        // RandomGenerator rg = new LinearCongruentialGenerator(0).withBitsMask(0,16);
        //
        // for (int i=0; i<10; ++i)
        //     System.out.println(rg.nextLong());

        RandomGenerator rg = new JavaRandomGenerator();
        ContinuousDistribution cd = new NormalDistribution();

        for (double x : Interval.from(-2,2,.1))
            System.out.printf("% .1f: %.6f, %.6f\n", x, cd.pdf(x), cd.cdf(x));

        CDFSampler sampler = new CDFSampler(cd, rg);

        for(int i=0; i<10; ++i)
            System.out.println(sampler.sample());
    }
}
