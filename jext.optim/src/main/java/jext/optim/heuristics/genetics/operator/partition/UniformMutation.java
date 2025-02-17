package jext.optim.heuristics.genetics.operator.partition;

import jext.optim.heuristics.genetics.domain.partition.Partition;
import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.util.AbstractMutationPolicy;

import java.util.random.RandomGenerator;

public class UniformMutation  extends AbstractMutationPolicy<Partition> {

    private final double prob;

    public UniformMutation() {
        this.prob = -1;
    }

    public UniformMutation(double prob) {
        this.prob = prob;
    }

    @Override
    public Chromosome<Partition> mutate(Chromosome<Partition> original, RandomGenerator rng) {
        Partition partition = original.candidate().clone();
        int n = partition.length();
        int nparts = partition.nparts();
        double prob = (this.prob == -1) ? 1.f/n : this.prob;

        for (int i = 0; i < n; i++) {
            double r = rng.nextDouble();
            if (r < prob) {
                int p = rng.nextInt(nparts);
                partition.set(i, p);
            }
        }

        return new Chromosome<>(partition, original);
    }
}
