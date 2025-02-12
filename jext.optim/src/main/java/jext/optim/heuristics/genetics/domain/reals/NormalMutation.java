package jext.optim.heuristics.genetics.domain.reals;

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
        this.prob = prob;
        this.variance = variance;
    }

    @Override
    public Chromosome<Vector> mutate(Chromosome<Vector> original, RandomGenerator rng) {
        // Vector vector = original.candidate();
        // Range range = vector.range();
        // double[] data = Arrays.copyOf(vector.data(), vector.length());
        // int n = data.length;
        //
        // for (int i=0; i<n; ++i) {
        //     double r = rng.nextDouble();
        //     if (r < prob) {
        //         double offset = rng.nextGaussian(0, variance);
        //         data[i] = range.clip(data[i]+offset);
        //     }
        // }
        //
        // return new Chromosome<>(new Vector(data, vector), original);

        Vector vector = original.candidate().clone();
        int n = vector.length();

        for (int i=0; i<n; ++i) {
            double r = rng.nextDouble();
            if (r < prob) {
                double step = rng.nextGaussian(0, variance);
                vector.add(i, step);
            }
        }

        return new Chromosome<>(vector, original);
    }
}
