package org.hls.check;

import org.jblas.*;
import static org.jblas.DoubleMatrix.*;
import static org.jblas.MatrixFunctions.*;

/**
 * <p>A simple example which computes kernel ridge regression using jblas.</p>
 *
 * <p>This code is by no means meant to be an example of how you should do
 * machine learning in Java, as there is absolutely no encapsulation. It
 * is rather just an example of how to use jblas to perform different kinds
 * of computations.</p>
 *
 * <p>Usage:
 * <pre>
 *    java -cp ... KRR kernel-width lambda
 * </pre>
 * For example, try {@code ... KRR 1 1}</p>
 */
public class KRR {
    /** Print usage */
    public static void usage() {
        System.out.println("Usage: java -cp ... KRR kernel-width lambda");
    }

    /** Extract parameters and call run() */
    public static void main(String[] args) {
        if (args.length == 0) {
            usage();
        } else {
            int n = 1000;
            double w = Double.parseDouble(args[0]);
            double lambda = Double.parseDouble(args[1]);

            new KRR().run(n, w, lambda);
        }
    }

    /**
     * Construct an example, train KRR, predict on 1000 new points,
     * and print the mean squared error.
     */
    void run(int n, double w, double lambda) {
        DoubleMatrix[] ds = sincDataset(n, 0.1);
        DoubleMatrix alpha = learnKRR(ds[0], ds[1], w, lambda);
        DoubleMatrix Yh = predictKRR(ds[0], ds[0], w, alpha);
        DoubleMatrix XE = rand(1000).mul(8).sub(4);
        System.out.printf("Mean squared error = %.5f\n", mse(Yh, ds[1]));
    }

    /**
     * The sinc function.
     *
     * <p>This version is not save since it divides by zero if one
     * of the entries of x are zero.</p>
     */
    DoubleMatrix sinc(DoubleMatrix x) {
        return sin(x).div(x);
    }

    /**
     * The sinc function (save version).
     *
     * <p>This version is save, as it replaces zero entries of x by 1.
     * Then, sinc(0) = sin(0) / 1 = 1.</p>
     *
     */
    DoubleMatrix safeSinc(DoubleMatrix x) {
        DoubleMatrix xIsZero = x.eq(0);
        return sin(x).div(x.add(xIsZero)).add(xIsZero);
    }


    /**
     * Create a sinc data set.
     *
     *<p>X ~ uniformly from -4..4<br/>
     *   Y ~ sinc(x) + noise * gaussian noise.<br/></p>
     */
    DoubleMatrix[] sincDataset(int n, double noise) {
        DoubleMatrix X = rand(n).mul(8).sub(4);
        DoubleMatrix Y = safeSinc(X) .add( randn(n).mul(noise) );

        return new DoubleMatrix[] {X, Y};
    }

    /**
     * Compute the alpha for Kernel Ridge Regression.
     *
     * <p>Computes alpha = (K + lambda I)^-1 Y.</p>
     */
    DoubleMatrix learnKRR(DoubleMatrix X, DoubleMatrix Y,
                          double w, double lambda) {
        int n = X.rows;
        DoubleMatrix K = gaussianKernel(w, X, X);
        K.addi(eye(n).muli(lambda));
        DoubleMatrix alpha = Solve.solveSymmetric(K, Y);
        return alpha;
    }

    /**
     * Compute the Gaussian kernel for the rows of X and Z, and kernel width w.
     */
    DoubleMatrix gaussianKernel(double w, DoubleMatrix X, DoubleMatrix Z) {
        DoubleMatrix d = Geometry.pairwiseSquaredDistances(X.transpose(), Z.transpose());
        return exp(d.div(w).neg());
    }

    /**
     * Predict KRR on XE which has been trained on X, w, and alpha.
     *
     * In a real world application, you would put all the data from training
     * in a class of its own, of course!
     */
    DoubleMatrix predictKRR(DoubleMatrix XE, DoubleMatrix X, double w, DoubleMatrix alpha) {
        DoubleMatrix K = gaussianKernel(w, XE, X);
        return K.mmul(alpha);
    }

    double mse(DoubleMatrix Y1, DoubleMatrix Y2) {
        DoubleMatrix diff = Y1.sub(Y2);
        return pow(diff, 2).mean();
    }
}