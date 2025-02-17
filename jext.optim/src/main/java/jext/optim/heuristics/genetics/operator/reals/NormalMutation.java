package jext.optim.heuristics.genetics.operator.reals;

import jext.optim.heuristics.genetics.domain.reals.Vector;
import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.util.AbstractMutationPolicy;

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

        Vector vector = original.candidate().clone();
        int n = vector.length();

        for (int i=0; i<n; ++i) {
            double r = rng.nextDouble();
            if (r < prob) {
                double step = (double) rng.nextGaussian(0, variance);
                vector.add(i, step);
            }
        }

        return new Chromosome<>(vector, original);
    }
}
