package jext.optim.heuristics.genetics.domain.permutation;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.FitnessFunction;
import jext.optim.heuristics.genetics.GeneticAlgorithm;

import java.util.Random;
import java.util.random.RandomGenerator;

public class PermutationFitnessFunction implements FitnessFunction<Permutation> {

    public static PermutationFitnessFunction random(int n) {
        RandomGenerator rand = GeneticAlgorithm.getRandomNumberGenerator();
        double[][] distances = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; ++j) {
                double dist = rand.nextDouble(0,1);
                distances[i][j] = dist;
                distances[j][i] = dist;
            }
        }

        return new PermutationFitnessFunction(distances);
    }

    // ----------------------------------------------------------------------

    private double[][] distances;

    public PermutationFitnessFunction(double[][] distances) {
        this.distances = distances;
    }

    @Override
    public double fitness(Permutation permutation) {
        int[] perm = permutation.permutation();
        int n = perm.length;
        double distance = distances[perm[n-1]][perm[0]];
        for (int i = 1; i < n; i++)
            distance += distances[perm[i-1]][perm[i]];
        return distance;
    }

    public Chromosome<Permutation> getDefaultChromosome() {
        int[] perm = Utils.defaultPerm(distances.length);
        return new Chromosome<>(new Permutation(perm), this, false);
    }
}
