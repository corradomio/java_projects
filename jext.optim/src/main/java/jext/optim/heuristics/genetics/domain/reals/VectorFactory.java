package jext.optim.heuristics.genetics.domain.reals;

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
        double[] data = new double[length];

        for (int i=0; i<length; ++i)
            data[i] = rng.nextDouble(range.min, range.max);

        return new Vector(data, range);
    }

}
