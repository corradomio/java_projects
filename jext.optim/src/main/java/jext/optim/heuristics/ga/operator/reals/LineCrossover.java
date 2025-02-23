package jext.optim.heuristics.ga.operator.reals;

import jext.optim.domain.reals.Vector;
import jext.optim.heuristics.ga.Chromosome;
import jext.optim.heuristics.ga.ChromosomePair;
import jext.optim.heuristics.ga.util.AbstractCrossoverPolicy;

import java.util.random.RandomGenerator;

/*
    Essentials of Metaheuristics - 2016
    pag. 42

    Line Recombination
 */

public class LineCrossover extends AbstractCrossoverPolicy<Vector> {

    private final double step;

    public LineCrossover(final double step) {
        this.step = (double) step;
    }

    @Override
    public ChromosomePair<Vector> crossover(Chromosome<Vector> first, Chromosome<Vector> second, RandomGenerator rng) {

        Vector v1 = first.candidate();
        Vector v2 = second.candidate();

        double alpha = rng.nextDouble(-step, 1+ step);
        double beta  = rng.nextDouble(-step, 1+ step);

        Vector r1 = v1.linear(v2, alpha);
        Vector r2 = v2.linear(v1, beta );

        return ChromosomePair.of(r1, r2, first);
    }
}
