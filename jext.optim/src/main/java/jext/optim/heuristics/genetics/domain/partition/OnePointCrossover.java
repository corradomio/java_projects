package jext.optim.heuristics.genetics.domain.partition;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.ChromosomePair;
import jext.optim.heuristics.genetics.util.AbstractCrossoverPolicy;

import java.util.random.RandomGenerator;

public class OnePointCrossover extends AbstractCrossoverPolicy<Partition> {

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
        //
        // System.arraycopy(e1, 0, r2, 0, i);
        // System.arraycopy(e2, 0, r1, 0, i);
        //
        // return ChromosomePair.of(new Partition(r1, nparts), new Partition(r2, nparts), first);

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
