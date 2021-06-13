package org.hls.check;

import jext.apache.commons.math3.distribution.MultivariateNormalDistribution;
import jext.math.Linalg;
import jext.math.LongRational;

public class Main {
    public static void main(String[] args) {

        Math.pow(0,0);
        StrictMath.pow(0,0);

        // System.out.println(LongFraction.pow(2, 0));
        // System.out.println(LongFraction.pow(2, 1));
        // System.out.println(LongFraction.pow(2, 2));
        // System.out.println(LongFraction.pow(2, 3));
        // LongRational f = new LongRational(-4,6);
        // System.out.println(f);
        // System.out.println(f.pow(3));
        // System.out.println(f.pow(-3));
        //
        // System.out.println(LongRational.from(Math.PI, 1.e-4));
        // System.out.println(LongRational.from(Math.E,  1.e-4));
        // System.out.println(LongRational.from(6.75));

        double ro = -0.7;
        MultivariateNormalDistribution mvnd = new MultivariateNormalDistribution(
            new double[]{-1,1},
            new double[][]{{1,ro*2},{ro*2,4}}
        );

        System.out.println(mvnd.density(new double[]{0,0}));
    }
}
