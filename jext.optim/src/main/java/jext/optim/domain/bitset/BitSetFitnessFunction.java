package jext.optim.domain.bitset;

import jext.math.random.UniformRandomGenerator;
import jext.optim.domain.FitnessFunction;
import jext.optim.heuristics.ga.Chromosome;

import java.util.random.RandomGenerator;

public class BitSetFitnessFunction implements FitnessFunction<BitSet> {

    public static BitSetFitnessFunction random(int n) {
        RandomGenerator rand = UniformRandomGenerator.getRandomGenerator();
        double[] values = new double[n];
        for (int i = 0; i < n; i++)
            values[i] = rand.nextDouble(-1, 1);
        return new BitSetFitnessFunction(values);
    }

    // ----------------------------------------------------------------------

    private double[] values;

    public BitSetFitnessFunction(double[] values) {
        this.values = values;
    }

    @Override
    public double fitness(BitSet bitSet) {
        double fitness = 0;
        for (int i = 0; i < bitSet.length(); i++)
            if (bitSet.get(i))
                fitness += values[i];
        return fitness;
    }

    public Chromosome<BitSet> getBestSolution() {
        BitSet bs = new BitSet(values.length);
        for (int i=0; i<values.length; i++)
            if (values[i] > 0)
                bs.set(i);

        return new Chromosome<>(bs, this, true);
    }

    public Chromosome<BitSet> getWorstSolution() {
        BitSet bs = new BitSet(values.length);
        for (int i=0; i<values.length; i++)
            if (values[i] < 0)
                bs.set(i);

        return new Chromosome<>(bs, this, true);
    }
}
