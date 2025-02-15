package jext.optim.heuristics.genetics.domain.reals;

import jext.optim.domain.reals.Vector;
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

    private final float step;

    public IntermediateCrossover(final float step) {
        this.step = step;
    }

    @Override
    public ChromosomePair<Vector> crossover(Chromosome<Vector> first, Chromosome<Vector> second, RandomGenerator rng) {

        Vector v1 = first.candidate();
        Vector v2 = second.candidate();

        Vector r1 = v1.clone();
        Vector r2 = v2.clone();
        int n = v1.length();

        for (int i=0; i<n; i++) {
            float alpha = rng.nextFloat(-step, 1+ step);
            float beta = rng.nextFloat(-step, 1+ step);

            r1.linear(i, v2, alpha);
            r2.linear(i, v1, beta );
        }

        return ChromosomePair.of(r1, r2, first);
    }
}
