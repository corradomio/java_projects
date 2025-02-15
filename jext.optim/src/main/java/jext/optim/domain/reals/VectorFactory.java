package jext.optim.domain.reals;

import jext.optim.heuristics.genetics.CandidateFactory;

import java.util.random.RandomGenerator;

public class VectorFactory  implements CandidateFactory<Vector> {
    private final int length;
    private final Range range;

    public VectorFactory(int length, Range range) {
        this.length = length;
        this.range = range;
    }

    @Override
    public Vector candidate(RandomGenerator rng) {
        float[] data = new float[length];

        for (int i=0; i<length; ++i)
            data[i] = rng.nextFloat(range.min, range.max);

        return new Vector(data, range);
    }

}
