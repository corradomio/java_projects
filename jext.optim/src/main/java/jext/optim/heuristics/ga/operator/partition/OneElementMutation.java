package jext.optim.heuristics.ga.operator.partition;

import jext.optim.heuristics.ga.Chromosome;
import jext.optim.domain.partition.Partition;
import jext.optim.heuristics.ga.util.AbstractMutationPolicy;

import java.util.random.RandomGenerator;

public class OneElementMutation extends AbstractMutationPolicy<Partition> {

    @Override
    public Chromosome<Partition> mutate(Chromosome<Partition> original, RandomGenerator rng) {
        Partition partition = original.candidate().clone();

        int e = rng.nextInt(partition.length());
        int p = rng.nextInt(partition.nparts());

        partition.set(e, p);

        return new Chromosome<>(partition, original);
    }
}
