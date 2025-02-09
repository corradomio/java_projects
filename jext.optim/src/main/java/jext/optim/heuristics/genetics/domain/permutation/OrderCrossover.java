package jext.optim.heuristics.genetics.domain.permutation;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.CrossoverPolicy;
import jext.optim.heuristics.genetics.ChromosomePair;

import java.util.random.RandomGenerator;

public class OrderCrossover extends CrossoverPolicy<Permutation> {

    @Override
    public ChromosomePair<Permutation> crossover(Chromosome<Permutation> first, Chromosome<Permutation> second, RandomGenerator rng) {
        int[] p1 = first.candidate().permutation();
        int[] p2 = second.candidate().permutation();
        int n = p1.length;

        int i = rng.nextInt(n);
        int j = rng.nextInt(n);

        int[] c1 = Utils.ordx(p1, p2, i, j);
        int[] c2 = Utils.ordx(p2, p1, i, j);

        return ChromosomePair.makePair(new Permutation(c1), new Permutation(c2), first);
    }
}
