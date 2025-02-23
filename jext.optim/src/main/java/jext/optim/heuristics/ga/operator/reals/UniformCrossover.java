package jext.optim.heuristics.ga.operator.reals;

import jext.optim.domain.reals.Vector;
import jext.optim.heuristics.ga.Chromosome;
import jext.optim.heuristics.ga.ChromosomePair;
import jext.optim.heuristics.ga.util.AbstractCrossoverPolicy;

import java.util.random.RandomGenerator;

public class UniformCrossover extends AbstractCrossoverPolicy<Vector> {

    private double prob;

    public UniformCrossover(double prob) {
        this.prob = prob;
    }

    @Override
    public ChromosomePair<Vector> crossover(Chromosome<Vector> first, Chromosome<Vector> second, RandomGenerator rng) {

        Vector v1 = first.candidate();
        Vector v2 = second.candidate();

        Vector r1 = v1.clone();
        Vector r2 = v2.clone();

        int n = v1.length();

        for (int i=0; i<n; i++) {
            double r = rng.nextDouble();
            if (r < prob) {
                r1.set(i, v2.get(i));
                r2.set(i, v1.get(i));
            }
        }

        return ChromosomePair.of(r1, r2, first);
    }
}
