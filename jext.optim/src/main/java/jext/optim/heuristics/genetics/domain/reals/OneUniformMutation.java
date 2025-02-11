package jext.optim.heuristics.genetics.domain.reals;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.util.AbstractMutationPolicy;
import jext.util.Arrays;

import java.util.random.RandomGenerator;

public class OneUniformMutation extends AbstractMutationPolicy<Vector> {

    private final double delta;

    public OneUniformMutation(double delta) {
        this.delta = delta;
    }

    @Override
    public Chromosome<Vector> mutate(Chromosome<Vector> original, RandomGenerator rng) {
        Vector vector = original.candidate();
        Range[] ranges = vector.ranges();
        double[] data = Arrays.copyOf(vector.data());

        int i = rng.nextInt(data.length);
        double off = rng.nextDouble(-delta, delta);

        data[i] = ranges[i].clip(data[i] + off);

        return new Chromosome<>(new Vector(data, vector), original);
    }
}
