package jext.optim.heuristics.ga.operator.reals;

import jext.optim.heuristics.ga.Chromosome;
import jext.optim.domain.reals.Vector;
import jext.optim.heuristics.ga.util.AbstractMutationPolicy;

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
