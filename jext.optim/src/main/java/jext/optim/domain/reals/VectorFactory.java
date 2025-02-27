package jext.optim.domain.reals;

import jext.optim.domain.CandidateFactory;

import java.util.random.RandomGenerator;

public class VectorFactory  implements CandidateFactory<Vector> {
    private final int length;
    private final Range range;

    public VectorFactory(int length, Range range) {
        this.length = length;
        this.range = range;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public Vector candidate(RandomGenerator rng) {
        double[] data = new double[length];

        for (int i=0; i<length; ++i)
            data[i] = rng.nextDouble(range.min, range.max);

        return new Vector(data, range);
    }

}
