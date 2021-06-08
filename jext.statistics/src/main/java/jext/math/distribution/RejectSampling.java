package jext.math.distribution;

import jext.math.random.RandomGenerator;

public class RejectSampling implements Sampling {

    private final Distribution distribution;
    private RandomGenerator generator;
    private double a, ab;
    private final double eps = 1.e-8;

    public RejectSampling(Distribution cd, RandomGenerator rg) {
        this.distribution = cd;
        this.generator = rg;
        this.a = distribution.lowerLimit();
        this.ab = distribution.upperLimit() - distribution.lowerLimit();
    }

    @Override
    public double sample() {
        double p = generator.nextDouble();
        double x = a + ab*p;

        while (p > distribution.pdf(x)) {
            x = a + ab*p;
            p = generator.nextDouble();
        }
        return x;
    }
}
