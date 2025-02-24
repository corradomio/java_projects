package jext.optim.heuristics.ga.operator.partition;

import jext.optim.domain.partition.Partition;
import jext.optim.heuristics.ga.Chromosome;
import jext.optim.heuristics.ga.util.AbstractMutationPolicy;

import java.util.random.RandomGenerator;

public class OneElementMutation extends AbstractMutationPolicy<Partition> {

    @Override
    public Chromosome<Partition> mutate(Chromosome<Partition> original, RandomGenerator rng) {
        Partition candidate = original.candidate().clone();

        int e = rng.nextInt(candidate.length());
        int p = rng.nextInt(candidate.nparts());

        candidate.set(e, p);

        return new Chromosome<>(candidate, original);
    }
}
