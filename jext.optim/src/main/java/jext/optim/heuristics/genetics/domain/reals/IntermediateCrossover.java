package jext.optim.heuristics.genetics.domain.reals;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.ChromosomePair;
import jext.optim.heuristics.genetics.util.AbstractCrossoverPolicy;

import java.util.random.RandomGenerator;

/*
    Essentials of Metaheuristics - 2016
    pag. 42

    Intermediate Recombination
 */

public class IntermediateCrossover extends AbstractCrossoverPolicy<Vector> {

    private final double step;

    public IntermediateCrossover(final double step) {
        this.step = step;
    }

    @Override
    public ChromosomePair<Vector> crossover(Chromosome<Vector> first, Chromosome<Vector> second, RandomGenerator rng) {
        // Vector v1 = first.candidate();
        // Vector v2 = second.candidate();
        // Range range = v1.range();
        // double[] d1 = v1.data();
        // double[] d2 = v2.data();
        // int n = d1.length;
        //
        // double[] r1 = new double[n];
        // double[] r2 = new double[n];
        //
        // for (int i=0; i<n; ++i) {
        //     double alpha = rng.nextDouble(-step, 1+ step);
        //     double beta = rng.nextDouble(-step, 1+ step);
        //
        //     r1[i] = range.clip(alpha*d1[i] + (1-alpha)*d2[i]);
        //     r2[i] = range.clip(beta*d1[i] + (1-beta)*d2[i]);
        // }
        //
        // return ChromosomePair.of(new Vector(r1, v1), new Vector(r2, v2), first);

        Vector v1 = first.candidate();
        Vector v2 = second.candidate();

        Vector r1 = v1.clone();
        Vector r2 = v2.clone();
        int n = v1.length();

        for (int i=0; i<n; i++) {
            double alpha = rng.nextDouble(-step, 1+ step);
            double beta = rng.nextDouble(-step, 1+ step);

            r1.linear(i, v2, alpha);
            r2.linear(i, v1, beta );
        }

        return ChromosomePair.of(r1, r2, first);
    }
}
