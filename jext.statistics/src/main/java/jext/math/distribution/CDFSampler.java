package jext.math.distribution;

import jext.math.random.RandomGenerator;

public class CDFSampler implements Sampler {

    private Distribution distribution;
    private RandomGenerator generator;

    public CDFSampler(Distribution cd, RandomGenerator rg) {
        this.distribution = cd;
        this.generator = rg;
    }


    @Override
    public double sample() {
        double r = generator.nextDouble();
        double a = distribution.lowerLimit();
        double b = distribution.upperLimit();
        double c = 0.5*(a+b);
        double p = 0;
        double f = distribution.cdf(c);
        double eps = 1.e-8;

        while ((b-a) > eps || (f-p) > eps) {
            if (f < r)
                a = c;
            else
                b = c;
            c = (a+b)/2;
            p = f;
            f = distribution.cdf(c);
        }
        return c;
    }
}
