package jext.math.distribution;

/**
 * https://en.wikipedia.org/wiki/Probability_density_function
 */
public interface ContinuousDistribution extends Distribution {

    /** Probabilistic density function */
    double pdf(double x);

    /** Cumulative distribution function */
    double cdf(double x);
}
