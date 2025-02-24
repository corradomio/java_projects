package jext.optim.heuristics.ga.operator.permutation;

import jext.optim.domain.permutation.Permutation;
import jext.optim.heuristics.ga.Chromosome;
import jext.optim.heuristics.ga.util.AbstractMutationPolicy;

import java.util.random.RandomGenerator;

public class TwoOptMutation  extends AbstractMutationPolicy<Permutation> {

    @Override
    public Chromosome<Permutation> mutate(Chromosome<Permutation> original, RandomGenerator rng) {

        Permutation candidate = original.candidate().clone();
        int n = candidate.length();
        int i = rng.nextInt(n);
        int j = i + 1; if (j == n) j = 0;

        candidate.swap(i, j);

        return new Chromosome<>(candidate, original);
    }
}
