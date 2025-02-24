package jext.optim.heuristics.ga.operator.reals;

import jext.optim.domain.reals.Vector;
import jext.optim.heuristics.ga.Chromosome;
import jext.optim.heuristics.ga.util.AbstractMutationPolicy;

import java.util.random.RandomGenerator;

public class OneNormalMutation extends AbstractMutationPolicy<Vector> {

    private final double variance;

    public OneNormalMutation(double variance) {
        this.variance = variance;
    }

    @Override
    public Chromosome<Vector> mutate(Chromosome<Vector> original, RandomGenerator rng) {

        Vector candidate = original.candidate().clone();
        int n = candidate.length();

        int i = rng.nextInt(n);
        double step = (double) rng.nextGaussian(0, variance);

        candidate.add(i, step);

        return new Chromosome<>(candidate, original);
    }
}
