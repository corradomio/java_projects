package jext.optim.heuristics.genetics.domain.permutation;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.util.AbstractMutationPolicy;

import java.util.random.RandomGenerator;

public class SwapMutation extends AbstractMutationPolicy<Permutation> {

    @Override
    public Chromosome<Permutation> mutate(Chromosome<Permutation> original, RandomGenerator rng) {
        // Permutation permutation = original.candidate();
        // int[] perm = Arrays.copyOf(permutation.permutation(), permutation.length());
        // int n = perm.length;
        // ArrayOps.swap(perm, rng.nextInt(n), rng.nextInt(n));
        // return new Chromosome<>(new Permutation(perm), original);

        Permutation p = original.candidate().clone();

        int n = p.length();
        int i = rng.nextInt(n);
        int j = rng.nextInt(n);

        p.swap(i, j);

        return new Chromosome<>(p, original);

    }
}
