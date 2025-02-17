package jext.optim.heuristics.genetics.operator.partition;

import jext.optim.heuristics.genetics.domain.partition.Partition;
import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.ChromosomePair;
import jext.optim.heuristics.genetics.util.AbstractCrossoverPolicy;

import java.util.random.RandomGenerator;

public class OnePointCrossover extends AbstractCrossoverPolicy<Partition> {

    @Override
    public ChromosomePair<Partition> crossover(Chromosome<Partition> first, Chromosome<Partition> second, RandomGenerator rng) {

        Partition p1 = first.candidate();
        Partition p2 = second.candidate();

        Partition r1 = p1.clone();
        Partition r2 = p2.clone();

        int n = p1.length();
        int i = rng.nextInt(n);

        r1.set(0, p2, i);
        r2.set(0, p1, i);

        return ChromosomePair.of(r1, r2, first);
    }
}
