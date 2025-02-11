package jext.optim.heuristics.genetics.domain.permutation;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.util.AbstractMutationPolicy;

import java.util.Arrays;
import java.util.random.RandomGenerator;

public class InsertMutation extends AbstractMutationPolicy<Permutation> {

    @Override
    public Chromosome<Permutation> mutate(Chromosome<Permutation> original, RandomGenerator rng) {
        Permutation permutation = original.candidate();
        int[] perm = Arrays.copyOf(permutation.permutation(), permutation.length());
        int n = perm.length;
        Utils.insert(perm, rng.nextInt(n), rng.nextInt(n));
        return new Chromosome<>(new Permutation(perm), original);
    }
}
