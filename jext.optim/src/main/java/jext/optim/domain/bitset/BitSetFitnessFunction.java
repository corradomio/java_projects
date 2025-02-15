package jext.optim.domain.bitset;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.FitnessFunction;
import jext.optim.heuristics.genetics.GeneticAlgorithm;

import java.util.random.RandomGenerator;

public class BitSetFitnessFunction implements FitnessFunction<BitSet> {

    public static BitSetFitnessFunction random(int n) {
        RandomGenerator rand = GeneticAlgorithm.getRandomGenerator();
        float[] values = new float[n];
        for (int i = 0; i < n; i++)
            values[i] = rand.nextFloat(-1, 1);
        return new BitSetFitnessFunction(values);
    }

    // ----------------------------------------------------------------------

    private float[] values;

    public BitSetFitnessFunction(float[] values) {
        this.values = values;
    }

    @Override
    public float fitness(BitSet bitSet) {
        float fitness = 0;
        for (int i = 0; i < bitSet.length(); i++)
            if (bitSet.get(i))
                fitness += values[i];
        return fitness;
    }

    public Chromosome<BitSet> getBestChromosome() {
        BitSet bs = new BitSet(values.length);
        for (int i=0; i<values.length; i++)
            if (values[i] > 0)
                bs.set(i);

        return new Chromosome<>(bs, this, true);
    }

    public Chromosome<BitSet> getWorstChromosome() {
        BitSet bs = new BitSet(values.length);
        for (int i=0; i<values.length; i++)
            if (values[i] < 0)
                bs.set(i);

        return new Chromosome<>(bs, this, true);
    }
}
