package jext.optim.heuristics.genetics.domain.reals;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.util.AbstractMutationPolicy;

import java.util.random.RandomGenerator;

/*
    Essentials of Metaheuristics - 2016
    pag. 19
 */
public class UniformMutation extends AbstractMutationPolicy<Vector> {

    private final double prob;
    private final double step;

    public UniformMutation(double prob, double noise) {
        this.prob = prob;
        this.step = noise;
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
        //         double offset = rng.nextDouble(-step, step);
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
                double offset = rng.nextDouble(-step, step);
                vector.add(i, offset);
            }
        }

        return new Chromosome<>(vector, original);
    }
}
