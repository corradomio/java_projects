package jext.math.distribution;

/**
 * https://en.wikipedia.org/wiki/Probability_mass_function
 */
public interface DiscreteDistribution extends Distribution {

    /** Mass distribution function */
    double pdf(int x);

    /** Cumulative distribution function */
    double cdf(int x);
}
