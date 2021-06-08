package jext.math.distribution;

import org.apache.commons.math3.distribution.UniformRealDistribution;

public class UniformDistribution implements ContinuousDistribution {

    private final double a, b;
    private final double f;

    public UniformDistribution() {
        this(0, 1);
    }

    public UniformDistribution(double a, double b) {
        if (a > b) throw new java.lang.IllegalArgumentException("Invalid range");
        this.a = a;
        this.b = b;
        this.f = 1./(0.+b-a);
    }

    @Override
    public double lowerLimit() {
        return a;
    }

    @Override
    public double upperLimit() {
        return b;
    }

    @Override
    public double pdf(double x) {
        if (x < a) return 0;
        if (x > b) return 0;
        return f;
    }

    @Override
    public double cdf(double x) {
        if (x < a) return 0;
        if (x > b) return 1;
        return a + (x-a)*f;
    }
}
