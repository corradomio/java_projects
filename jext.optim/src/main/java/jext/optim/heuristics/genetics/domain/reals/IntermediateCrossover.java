package jext.optim.heuristics.genetics.domain.reals;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.ChromosomePair;
import jext.optim.heuristics.genetics.util.AbstractCrossoverPolicy;

import java.util.random.RandomGenerator;

public class IntermediateCrossover extends AbstractCrossoverPolicy<Vector> {

    private final double delta;

    public IntermediateCrossover(final double delta) {
        this.delta = delta;
    }

    @Override
    public ChromosomePair<Vector> crossover(Chromosome<Vector> first, Chromosome<Vector> second, RandomGenerator rng) {
        Vector v1 = first.candidate();
        Vector v2 = second.candidate();
        Range[] ranges = v1.ranges();
        double[] d1 = v1.data();
        double[] d2 = v2.data();
        int n = d1.length;

        double[] r1 = new double[n];
        double[] r2 = new double[n];

        for (int i=0; i<n; ++i) {
            double alpha = rng.nextDouble(-delta, 1+delta);
            double beta = rng.nextDouble(-delta, 1+delta);

            r1[i] = ranges[i].clip(alpha*d1[i] + (1-alpha)*d2[i]);
            r2[i] = ranges[i].clip(beta*d1[i] + (1-beta)*d2[i]);
        }

        return ChromosomePair.makePair(new Vector(r1, v1), new Vector(r2, v2), first);
    }
}
