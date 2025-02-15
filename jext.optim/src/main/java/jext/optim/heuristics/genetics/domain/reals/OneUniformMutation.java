package jext.optim.heuristics.genetics.domain.reals;

import jext.optim.domain.reals.Vector;
import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.util.AbstractMutationPolicy;

import java.util.random.RandomGenerator;

public class OneUniformMutation extends AbstractMutationPolicy<Vector> {

    private final float step;

    public OneUniformMutation(float step) {
        this.step = step;
    }

    @Override
    public Chromosome<Vector> mutate(Chromosome<Vector> original, RandomGenerator rng) {

        Vector vector = original.candidate().clone();
        int n = vector.length();

        int i = rng.nextInt(n);
        float offset = rng.nextFloat(-step, step);

        vector.add(i, offset);

        return new Chromosome<>(vector, original);
    }
}
