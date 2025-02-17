package jext.optim.heuristics.genetics.operator.partition;

import jext.optim.heuristics.genetics.domain.partition.Partition;
import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.util.AbstractMutationPolicy;

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
