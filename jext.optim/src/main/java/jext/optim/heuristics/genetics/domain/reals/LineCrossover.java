package jext.optim.heuristics.genetics.domain.reals;

import jext.optim.domain.reals.Vector;
import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.ChromosomePair;
import jext.optim.heuristics.genetics.util.AbstractCrossoverPolicy;

import java.util.random.RandomGenerator;

/*
    Essentials of Metaheuristics - 2016
    pag. 42

    Line Recombination
 */

public class LineCrossover extends AbstractCrossoverPolicy<Vector> {

    private final float step;

    public LineCrossover(final double step) {
        this.step = (float) step;
    }

    @Override
    public ChromosomePair<Vector> crossover(Chromosome<Vector> first, Chromosome<Vector> second, RandomGenerator rng) {

        Vector v1 = first.candidate();
        Vector v2 = second.candidate();

        float alpha = rng.nextFloat(-step, 1+ step);
        float beta  = rng.nextFloat(-step, 1+ step);

        Vector r1 = v1.linear(v2, alpha);
        Vector r2 = v2.linear(v1, beta );

        return ChromosomePair.of(r1, r2, first);
    }
}
