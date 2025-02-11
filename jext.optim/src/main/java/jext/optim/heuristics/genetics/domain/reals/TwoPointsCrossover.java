package jext.optim.heuristics.genetics.domain.reals;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.ChromosomePair;
import jext.optim.heuristics.genetics.util.AbstractCrossoverPolicy;

import java.util.Arrays;
import java.util.random.RandomGenerator;

public class TwoPointsCrossover extends AbstractCrossoverPolicy<Vector> {

    @Override
    public ChromosomePair<Vector> crossover(Chromosome<Vector> first, Chromosome<Vector> second, RandomGenerator rng) {
        Vector v1 = first.candidate();
        Vector v2 = second.candidate();
        double[] d1 = v1.data();
        double[] d2 = v2.data();
        int n = d1.length;

        double[] r1 = Arrays.copyOf(d1, n);
        double[] r2 = Arrays.copyOf(d2, n);

        int i = rng.nextInt(n);
        int j = rng.nextInt(n);
        if (i > j){ int t = i; i = j; j = t; }

        System.arraycopy(d1, i, r2, i, j-i);
        System.arraycopy(d2, i, r1, i, j-i);

        return ChromosomePair.makePair(new Vector(r1, v1), new Vector(r2, v2), first);
    }
}
