package jext.optim.heuristics.genetics.domain.reals;

import jext.optim.domain.reals.Vector;
import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.util.AbstractMutationPolicy;

import java.util.random.RandomGenerator;

/*
    Essentials of Metaheuristics - 2016
    pag. 23
 */

public class NormalMutation extends AbstractMutationPolicy<Vector> {

    private final float prob;
    private final float variance;

    public NormalMutation(double prob, double variance) {
        this.prob = (float) prob;
        this.variance = (float) variance;
    }

    @Override
    public Chromosome<Vector> mutate(Chromosome<Vector> original, RandomGenerator rng) {

        Vector vector = original.candidate().clone();
        int n = vector.length();

        for (int i=0; i<n; ++i) {
            float r = rng.nextFloat();
            if (r < prob) {
                float step = (float) rng.nextGaussian(0, variance);
                vector.add(i, step);
            }
        }

        return new Chromosome<>(vector, original);
    }
}
