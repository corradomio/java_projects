package jext.optim.heuristics.genetics.domain.permutation;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.util.AbstractMutationPolicy;

import java.util.random.RandomGenerator;

public class ScrambleMutation extends AbstractMutationPolicy<Permutation> {

    @Override
    public Chromosome<Permutation> mutate(Chromosome<Permutation> original, RandomGenerator rng) {
        // Permutation permutation = original.candidate();
        // int[] perm = Arrays.copyOf(permutation.permutation(), permutation.length());
        // int n = perm.length;
        // ArrayOps.shuffle(perm, rng.nextInt(n), rng.nextInt(n), rng);
        // return new Chromosome<>(new Permutation(perm), original);

        Permutation p = original.candidate().clone();

        int n = p.length();
        int i = rng.nextInt(n);
        int j = rng.nextInt(n);

        p.shuffle(i, j, rng);

        return new Chromosome<>(p, original);
    }
}
