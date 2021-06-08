package jext.math.distribution;

public interface Distribution {

    double lowerLimit();
    double upperLimit();

    /** Probabilistic density function */
    double pdf(double x);

    /** Cumulative distribution function */
    double cdf(double x);
}
