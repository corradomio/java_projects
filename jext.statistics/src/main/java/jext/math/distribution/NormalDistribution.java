package jext.math.distribution;

import org.apache.commons.math3.special.Erf;

import static java.lang.Math.exp;
import static java.lang.Math.sqrt;
import static java.lang.Math.PI;
import static jext.math.Math.sq;
import static jext.math.Math.div;

public class NormalDistribution implements ContinuousDistribution {

    private final double mu;    // mean
    private final double ro;    // sdev
    private final double f1;
    private final double f2;

    public NormalDistribution() {
        this(0, 1);
    }

    public NormalDistribution(double mu, double ro) {
        this.mu = mu;
        this.ro = ro;
        this.f1 = 1/(ro *sqrt(2*PI));
        this.f2 = 1/(ro*sqrt(2));
    }

    @Override
    public double lowerLimit() {
        return mu-100*ro;
    }

    @Override
    public double upperLimit() {
        return mu+100*ro;
    }

    @Override
    public double pdf(double x) {
        return f1*exp(-0.5*sq(div(x - mu, ro)));
    }

    @Override
    public double cdf(double x) {
        return 0.5*(1 + Erf.erf(f2*(x-mu)));
    }
}
