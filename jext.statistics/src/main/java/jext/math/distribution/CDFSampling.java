package jext.math.distribution;

import jext.math.random.RandomGenerator;

public class CDFSampling implements Sampling {

    private final Distribution distribution;
    private RandomGenerator generator;
    private final double eps = 1.e-8;

    public CDFSampling(Distribution cd, RandomGenerator rg) {
        this.distribution = cd;
        this.generator = rg;
    }

    @Override
    public double sample() {
        double r = generator.nextDouble();
        double a = distribution.lowerLimit();
        double b = distribution.upperLimit();

        double p = 0; // prev value
        double x = 0.5*(a+b);
        double f = distribution.cdf(x);

        while ((b-a) > eps || (f-p) > eps) {
            p = f;
            if (f < r)
                // upper half
                a = x;
            else
                // lower half
                b = x;
            x = (a+b)/2;
            f = distribution.cdf(x);
        }
        return x;
    }
}
