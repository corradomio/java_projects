package jext.optim.heuristics.ga.operator.reals;

import jext.optim.domain.reals.Vector;
import jext.optim.heuristics.ga.Chromosome;
import jext.optim.heuristics.ga.util.AbstractMutationPolicy;

import java.util.random.RandomGenerator;

/*
    Essentials of Metaheuristics - 2016
    pag. 23
 */

public class NormalMutation extends AbstractMutationPolicy<Vector> {

    private final double prob;
    private final double variance;

    public NormalMutation(double prob, double variance) {
        this.prob = (double) prob;
        this.variance = (double) variance;
    }

    @Override
    public Chromosome<Vector> mutate(Chromosome<Vector> original, RandomGenerator rng) {

        Vector candidate = original.candidate().clone();
        int n = candidate.length();

        for (int i=0; i<n; ++i) {
            double r = rng.nextDouble();
            if (r < prob) {
                double step = (double) rng.nextGaussian(0, variance);
                candidate.add(i, step);
            }
        }

        return new Chromosome<>(candidate, original);
    }
}
