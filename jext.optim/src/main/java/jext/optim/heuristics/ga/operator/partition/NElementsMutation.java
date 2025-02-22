package jext.optim.heuristics.ga.operator.partition;

import jext.optim.heuristics.ga.Chromosome;
import jext.optim.domain.partition.Partition;
import jext.optim.heuristics.ga.util.AbstractMutationPolicy;

import java.util.random.RandomGenerator;

public class NElementsMutation extends AbstractMutationPolicy<Partition> {

    private final int nelts;

    public NElementsMutation(int nelts) {
        this.nelts = nelts;
    }

    @Override
    public Chromosome<Partition> mutate(Chromosome<Partition> original, RandomGenerator rng) {
        Partition partition = original.candidate().clone();
        int n = partition.length();
        int nparts = partition.nparts();

        for (int i=0; i<nelts; ++i) {
            int e = rng.nextInt(n);
            int p = rng.nextInt(nparts);

            partition.set(e, p);
        }

        return new Chromosome<>(partition, original);
    }
}
