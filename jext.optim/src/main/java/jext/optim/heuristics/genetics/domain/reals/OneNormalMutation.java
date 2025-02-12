package jext.optim.heuristics.genetics.domain.reals;

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
        // Vector vector = original.candidate();
        // Range range = vector.range();
        // double[] data = Arrays.copyOf(vector.data(), vector.length());
        //
        // int i = rng.nextInt(data.length);
        // double off = rng.nextGaussian(0, variance);
        //
        // data[i] = range.clip(data[i] + off);
        //
        // return new Chromosome<>(new Vector(data, vector), original);

        Vector vector = original.candidate().clone();
        int n = vector.length();

        int i = rng.nextInt(n);
        double step = rng.nextGaussian(0, variance);

        vector.add(i, step);

        return new Chromosome<>(vector, original);
    }
}
