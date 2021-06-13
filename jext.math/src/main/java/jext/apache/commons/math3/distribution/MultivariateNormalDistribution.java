package jext.apache.commons.math3.distribution;

import org.apache.commons.math3.distribution.AbstractMultivariateRealDistribution;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;

import static java.lang.Math.PI;
import static java.lang.Math.exp;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static jext.math.Linalg.copy;
import static jext.math.Linalg.determinant;
import static jext.math.Linalg.inverse;
import static jext.math.Linalg.multiply;

/**
 * https://en.wikipedia.org/wiki/Multivariate_normal_distribution
 */
public class MultivariateNormalDistribution extends AbstractMultivariateRealDistribution {

    private int n;
    private final double[] means;

    private final double[] covarianceDiagonalMatrix;
    private final double[] covarianceDiagonalMatrixInverse;
    private final double[][] covarianceMatrix;
    private final double[][] covarianceMatrixInverse;
    private final double covarianceDeterminant;

    private double factor;

    public MultivariateNormalDistribution(double[] means, double variance) {
        this(means, copy(variance, means.length));
    }

    public MultivariateNormalDistribution(double[] means, double[] variances) {
        this(new Well19937c(), means, variances);
    }

    public MultivariateNormalDistribution(double[] means, double[][] variances) {
        this(new Well19937c(), means, variances);
    }

    public MultivariateNormalDistribution(RandomGenerator rng, double[] means, double variance) {
        this(rng, means, copy(variance, means.length));
    }

    public MultivariateNormalDistribution(RandomGenerator rng, double[] means, double[] variances) {
        super(rng, means.length);
        this.n = means.length;
        this.means = means;
        this.covarianceDiagonalMatrix = variances;
        this.covarianceDiagonalMatrixInverse = inverse(variances);

        this.covarianceMatrix = null;
        this.covarianceMatrixInverse = null;

        this.covarianceDeterminant = determinant(variances);
        this.factor = 1./sqrt(pow(2*PI, n)*covarianceDeterminant);
    }

    public MultivariateNormalDistribution(RandomGenerator rng, double[] means, double[][] variances) {
        super(rng, means.length);
        this.n = means.length;
        this.means = means;
        this.covarianceDiagonalMatrix = null;
        this.covarianceDiagonalMatrixInverse = null;

        this.covarianceMatrix = variances;
        this.covarianceMatrixInverse = inverse(variances);

        this.covarianceDeterminant = determinant(variances);
        this.factor = 1./sqrt(pow(2*PI, n)*covarianceDeterminant);
    }

    @Override
    public double density(double[] x) {
        double t;
        if (covarianceDiagonalMatrix != null)
            t = multiply(x, means, covarianceDiagonalMatrixInverse);
        else
            t = multiply(x, means, covarianceMatrixInverse);

        return factor*exp(-0.5*t);
    }

    @Override
    public double[] sample() {
        return new double[0];
    }

}
