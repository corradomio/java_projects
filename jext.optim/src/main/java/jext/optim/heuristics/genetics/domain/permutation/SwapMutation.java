package jext.optim.heuristics.genetics.domain.permutation;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.MutationPolicy;

import java.util.random.RandomGenerator;

public class SwapMutation extends MutationPolicy<Permutation> {
    @Override
    public Chromosome<Permutation> mutate(Chromosome<Permutation> chromosome, RandomGenerator rng) {
        Permutation permutation = chromosome.candidate();
        int[] perm = permutation.permutation();
        int n = perm.length;
        Utils.swap(perm, rng.nextInt(n), rng.nextInt(n));
        return chromosome;
    }
}
