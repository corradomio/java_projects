package jext.optim.heuristics.genetics.domain.reals;

import jext.optim.heuristics.genetics.CandidateFactory;

import java.util.random.RandomGenerator;

public class VectorFactory  implements CandidateFactory<Vector> {
    private final int length;
    private final Range[] ranges;

    public VectorFactory(int length, Range range) {
        this.length = length;
        this.ranges = new Range[length];
        for (int i=0; i<length; i++)
            this.ranges[i] = range;
    }

    public VectorFactory(Range[] ranges) {
        this.length = ranges.length;
        this.ranges = ranges;
    }

    @Override
    public Vector candidate(RandomGenerator rng) {
        double[] data = new double[length];

        for (int i=0; i<length; ++i)
            data[i] = rng.nextDouble(ranges[i].min, ranges[i].max);

        return new Vector(data, ranges);
    }

}
