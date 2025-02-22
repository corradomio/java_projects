package jext.optim.domain.partition;

import jext.optim.domain.CandidateFactory;

import java.util.random.RandomGenerator;

public class PartitionFactory  implements CandidateFactory<Partition> {

    private final int length;
    private final int nparts;

    public PartitionFactory(int length, int nparts) {
        this.length = length;
        this.nparts = nparts;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public Partition candidate(RandomGenerator rng) {
        int[] elements = new int[length];
        for (int i = 0; i < length; i++) {
            elements[i] = rng.nextInt(nparts);
        }
        return new Partition(elements, nparts);
    }
}
