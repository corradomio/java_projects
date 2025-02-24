package jext.optim.heuristics.ga.operator.permutation;

import jext.optim.domain.permutation.Permutation;
import jext.optim.heuristics.ga.Chromosome;
import jext.optim.heuristics.ga.util.AbstractMutationPolicy;

import java.util.random.RandomGenerator;

public class ScrambleMutation extends AbstractMutationPolicy<Permutation> {

    @Override
    public Chromosome<Permutation> mutate(Chromosome<Permutation> original, RandomGenerator rng) {

        Permutation candidate = original.candidate().clone();

        int n = candidate.length();
        int i = rng.nextInt(n);
        int j = rng.nextInt(n);

        candidate.shuffle(i, j, rng);

        return new Chromosome<>(candidate, original);
    }
}
