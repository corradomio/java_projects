package jext.math.distribution;

import jext.math.random.RandomGenerator;

public class CDFSampler implements Sampler {

    private final Distribution distribution;
    private RandomGenerator generator;
    private final double eps = 1.e-8;

    public CDFSampler(Distribution cd, RandomGenerator rg) {
        this.distribution = cd;
        this.generator = rg;
    }

    @Override
    public double sample() {
        double r = generator.nextDouble();
        return sample(r);
    }

    public double sample(double r) {
        double a = distribution.lowerLimit();
        double b = distribution.upperLimit();

        double p = 0; // prev value
        double c = 0.5*(a+b);
        double f = distribution.cdf(c);

        while ((b-a) > eps || (f-p) > eps) {
            p = f;
            if (f < r)
                // upper half
                a = c;
            else
                // lower half
                b = c;
            c = (a+b)/2;
            f = distribution.cdf(c);
        }
        return c;
    }
}
