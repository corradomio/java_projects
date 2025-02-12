package jext.optim.heuristics.genetics.domain.partition;

import jext.optim.heuristics.genetics.FitnessFunction;
import jext.optim.heuristics.genetics.GeneticAlgorithm;
import jext.optim.heuristics.genetics.util.Entropy;

import java.util.random.RandomGenerator;

public class PartitionFitnessFunction implements FitnessFunction<Partition> {

    public static PartitionFitnessFunction random(int n) {
        RandomGenerator rand = GeneticAlgorithm.getRandomGenerator();
        double[] values = new double[n];
        for (int i = 0; i < n; i++)
            values[i] = rand.nextDouble(0, 1);
        return new PartitionFitnessFunction(values);
    }

    // ----------------------------------------------------------------------
    // Entropy:       minimize to have all sets with the same weight
    // Gini Impurity: maximize to have all sets with the same weight
    //

    private final double[] elementsValue;

    public PartitionFitnessFunction(double[] elementsValue) {
        this.elementsValue = elementsValue;
    }

    @Override
    public double fitness(Partition partition) {
        int n = partition.length();
        int nparts = partition.nparts();
        double total = 0;
        double[] values = new double[nparts];

        for(int i=0; i<n; ++i) {
            int p = partition.get(i);
            double v = this.elementsValue[i];
            values[p] += v;
            total += v;
        }

        return Entropy.value(values, total);
    }

    public double[] values(Partition partition) {
        int nparts = partition.nparts();
        double[] partsValue = new double[nparts];
        int[] elements = partition.elements();
        int n = elements.length;
        for (int i=0; i<n; ++i) {
            int p = elements[i];
            partsValue[p] += this.elementsValue[i];
        }
        return partsValue;
    }
}
