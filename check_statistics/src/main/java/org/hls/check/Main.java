package org.hls.check;

import jext.math.distribution.ContinuousDistribution;
import jext.math.distribution.NormalDistribution;
import jext.math.distribution.RejectSampling;
import jext.math.distribution.Sampling;
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

        Sampling s;
        // s = new CDFSampling(cd, rg);
        s = new RejectSampling(cd, rg);

        for(int i=0; i<10; ++i) {
            double f = s.sample();

        }
    }
}
