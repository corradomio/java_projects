package jext.optim.heuristics.genetics.operator.reals;

import jext.optim.heuristics.genetics.domain.reals.Vector;
import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.util.AbstractMutationPolicy;

import java.util.random.RandomGenerator;

public class OneUniformMutation extends AbstractMutationPolicy<Vector> {

    private final double step;

    public OneUniformMutation(double step) {
        this.step = step;
    }

    @Override
    public Chromosome<Vector> mutate(Chromosome<Vector> original, RandomGenerator rng) {

        Vector vector = original.candidate().clone();
        int n = vector.length();

        int i = rng.nextInt(n);
        double offset = rng.nextDouble(-step, step);

        vector.add(i, offset);

        return new Chromosome<>(vector, original);
    }
}
