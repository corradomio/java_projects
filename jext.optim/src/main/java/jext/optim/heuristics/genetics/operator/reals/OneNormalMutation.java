package jext.optim.heuristics.genetics.operator.reals;

import jext.optim.heuristics.genetics.domain.reals.Vector;
import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.util.AbstractMutationPolicy;

import java.util.random.RandomGenerator;

public class OneNormalMutation extends AbstractMutationPolicy<Vector> {

    private final double variance;

    public OneNormalMutation(double variance) {
        this.variance = variance;
    }

    @Override
    public Chromosome<Vector> mutate(Chromosome<Vector> original, RandomGenerator rng) {

        Vector vector = original.candidate().clone();
        int n = vector.length();

        int i = rng.nextInt(n);
        double step = (double) rng.nextGaussian(0, variance);

        vector.add(i, step);

        return new Chromosome<>(vector, original);
    }
}
