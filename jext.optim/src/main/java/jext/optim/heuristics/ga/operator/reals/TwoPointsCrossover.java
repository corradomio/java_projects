package jext.optim.heuristics.ga.operator.reals;

import jext.optim.heuristics.ga.Chromosome;
import jext.optim.heuristics.ga.ChromosomePair;
import jext.optim.domain.reals.Vector;
import jext.optim.heuristics.ga.util.AbstractCrossoverPolicy;

import java.util.random.RandomGenerator;

public class TwoPointsCrossover extends AbstractCrossoverPolicy<Vector> {

    @Override
    public ChromosomePair<Vector> crossover(Chromosome<Vector> first, Chromosome<Vector> second, RandomGenerator rng) {

        Vector v1 = first.candidate();
        Vector v2 = second.candidate();

        Vector r1 = v1.clone();
        Vector r2 = v2.clone();

        int n = v1.length();
        int i = rng.nextInt(n);
        int j = rng.nextInt(n);
        if (i > j){ int t = i; i = j; j = t; }

        r1.set(i, v2, j-i);
        r2.set(i, v1, j-i);

        return ChromosomePair.of(r1, r2, first);
    }
}
