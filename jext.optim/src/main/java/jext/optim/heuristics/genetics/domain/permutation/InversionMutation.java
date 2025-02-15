package jext.optim.heuristics.genetics.domain.permutation;

import jext.optim.domain.permutation.Permutation;
import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.util.AbstractMutationPolicy;

import java.util.random.RandomGenerator;

public class InversionMutation extends AbstractMutationPolicy<Permutation> {

    @Override
    public Chromosome<Permutation> mutate(Chromosome<Permutation> original, RandomGenerator rng) {

        Permutation p = original.candidate().clone();
        int n = p.length();
        int i = rng.nextInt(n);
        int j = rng.nextInt(n);

        p.reverse(i, j);

        return new Chromosome<>(p, original);
    }
}
