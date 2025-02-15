package jext.optim.heuristics.genetics.domain.partition;

import jext.optim.domain.partition.Partition;
import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.util.AbstractMutationPolicy;

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
