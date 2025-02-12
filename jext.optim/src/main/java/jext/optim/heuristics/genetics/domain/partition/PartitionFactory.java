package jext.optim.heuristics.genetics.domain.partition;

import jext.optim.heuristics.genetics.CandidateFactory;

import java.util.random.RandomGenerator;

public class PartitionFactory  implements CandidateFactory<Partition> {

    private final int nelts;
    private final int nparts;

    public PartitionFactory(int nelts, int nparts) {
        this.nelts = nelts;
        this.nparts = nparts;
    }

    @Override
    public Partition candidate(RandomGenerator rng) {
        int[] elements = new int[nelts];
        for (int i = 0; i < nelts; i++) {
            elements[i] = rng.nextInt(nparts);
        }
        return new Partition(elements, nparts);
    }
}
