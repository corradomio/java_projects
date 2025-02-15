package jext.optim.domain.partition;

import jext.optim.heuristics.genetics.FitnessFunction;
import jext.optim.heuristics.genetics.GeneticAlgorithm;
import jext.optim.heuristics.genetics.util.Entropy;

import java.util.random.RandomGenerator;

public class PartitionFitnessFunction implements FitnessFunction<Partition> {

    public static PartitionFitnessFunction random(int n) {
        RandomGenerator rand = GeneticAlgorithm.getRandomGenerator();
        float[] values = new float[n];
        for (int i = 0; i < n; i++)
            values[i] = rand.nextFloat(0, 1);
        return new PartitionFitnessFunction(values);
    }

    // ----------------------------------------------------------------------
    // Entropy:       minimize to have all sets with the same weight
    // Gini Impurity: maximize to have all sets with the same weight
    //

    private final float[] elementsValue;

    public PartitionFitnessFunction(float[] elementsValue) {
        this.elementsValue = elementsValue;
    }

    @Override
    public float fitness(Partition partition) {
        int n = partition.length();
        int nparts = partition.nparts();
        float total = 0;
        float[] values = new float[nparts];

        for(int i=0; i<n; ++i) {
            int p = partition.get(i);
            float v = this.elementsValue[i];
            values[p] += v;
            total += v;
        }

        return Entropy.value(values, total);
    }

    public float[] values(Partition partition) {
        int nparts = partition.nparts();
        float[] partsValue = new float[nparts];
        int[] elements = partition.elements();
        int n = elements.length;
        for (int i=0; i<n; ++i) {
            int p = elements[i];
            partsValue[p] += this.elementsValue[i];
        }
        return partsValue;
    }
}
