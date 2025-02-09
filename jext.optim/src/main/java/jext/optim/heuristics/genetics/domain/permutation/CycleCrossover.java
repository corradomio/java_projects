package jext.optim.heuristics.genetics.domain.permutation;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.CrossoverPolicy;
import jext.optim.heuristics.genetics.ChromosomePair;

import java.util.random.RandomGenerator;

public class CycleCrossover  extends CrossoverPolicy<Permutation> {

    @Override
    public ChromosomePair<Permutation> crossover(Chromosome<Permutation> first, Chromosome<Permutation> second, RandomGenerator rng) {
        int[] p1 = first.candidate().permutation();
        int[] p2 = second.candidate().permutation();

        int[] c1 = Utils.cycx(p1, p2);
        int[] c2 = Utils.cycx(p2, p1);

        return ChromosomePair.makePair(new Permutation(c1), new Permutation(c2), first);
    }
}
