package jext.optim.heuristics.genetics.domain.reals;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.util.AbstractMutationPolicy;
import jext.util.Arrays;

import java.util.random.RandomGenerator;

/*
    Essentials of Metaheuristics - 2016
    pag. 23
 */
public class NormalMutation extends AbstractMutationPolicy<Vector> {

    private final double prob;
    private final double delta;

    public NormalMutation(double prob, double variance) {
        this.prob = prob;
        this.delta = variance;
    }

    @Override
    public Chromosome<Vector> mutate(Chromosome<Vector> original, RandomGenerator rng) {
        Vector vector = original.candidate();
        Range[] ranges = vector.ranges();
        double[] data = Arrays.copyOf(vector.data());
        int n = data.length;

        for (int i=0; i<n; ++i) {
            double r = rng.nextDouble();
            if (r < prob) {
                double offset = rng.nextGaussian(0, delta);
                data[i] = ranges[i].clip(data[i]+offset);
            }
        }

        return new Chromosome<>(new Vector(data, vector), original);
    }
}
