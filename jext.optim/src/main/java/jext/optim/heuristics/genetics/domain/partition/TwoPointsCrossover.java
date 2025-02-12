package jext.optim.heuristics.genetics.domain.partition;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.ChromosomePair;
import jext.optim.heuristics.genetics.util.AbstractCrossoverPolicy;

import java.util.random.RandomGenerator;

public class TwoPointsCrossover extends AbstractCrossoverPolicy<Partition> {

    @Override
    public ChromosomePair<Partition> crossover(Chromosome<Partition> first, Chromosome<Partition> second, RandomGenerator rng) {
        // Partition p1 = first.candidate();
        // Partition p2 = second.candidate();
        // int[] e1 = p1.elements();
        // int[] e2 = p2.elements();
        // int n = p1.length();
        // int nparts = p1.nparts();
        // int[] r1 = Arrays.copyOf(e1, n);
        // int[] r2 = Arrays.copyOf(e2, n);
        //
        // int i = rng.nextInt(n);
        // int j = rng.nextInt(n);
        // if (i > j){ int t = i; i = j; j = t; }
        //
        // System.arraycopy(e1, i, r2, i, j-i);
        // System.arraycopy(e2, i, r1, i, j-i);
        //
        // return ChromosomePair.of(new Partition(r1, nparts), new Partition(r2, nparts), first);

        Partition p1 = first.candidate();
        Partition p2 = second.candidate();

        Partition r1 = p1.clone();
        Partition r2 = p2.clone();

        int n = p1.length();
        int i = rng.nextInt(n);
        int j = rng.nextInt(n);
        if (i > j){ int t = i; i = j; j = t; }

        r1.set(i, p2, j-i);
        r2.set(i, p1, j-i);

        return ChromosomePair.of(r1, r2, first);
    }
}
