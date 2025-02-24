package jext.optim.heuristics.ga.operator.reals;

import jext.optim.domain.reals.Vector;
import jext.optim.heuristics.ga.Chromosome;
import jext.optim.heuristics.ga.util.AbstractMutationPolicy;

import java.util.random.RandomGenerator;

public class OneUniformMutation extends AbstractMutationPolicy<Vector> {

    private final double step;

    public OneUniformMutation(double step) {
        this.step = step;
    }

    @Override
    public Chromosome<Vector> mutate(Chromosome<Vector> original, RandomGenerator rng) {

        Vector candidate = original.candidate().clone();
        int n = candidate.length();

        int i = rng.nextInt(n);
        double offset = rng.nextDouble(-step, step);

        candidate.add(i, offset);

        return new Chromosome<>(candidate, original);
    }
}
