package jext.optim.domain.bitmat;

import jext.math.random.UniformRandomGenerator;
import jext.optim.domain.FitnessFunction;
import jext.optim.domain.bitset.BitSet;
import jext.optim.domain.bitset.BitSetFitnessFunction;

import java.util.random.RandomGenerator;

public class BitMatFitnessFunction implements FitnessFunction<BitMat> {

    public static BitMatFitnessFunction random(int rows, int cols) {
        RandomGenerator rand = UniformRandomGenerator.getRandomGenerator();
        double[][] matrix = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = rand.nextDouble();
            }
        }

        return new BitMatFitnessFunction(matrix);
    }

    private final double[][] matrix;
    private final int rows;
    private final int cols;

    public BitMatFitnessFunction(double[][] matrix) {
        this.matrix = matrix;
        this.rows = matrix.length;
        this.cols = matrix[0].length;
    }

    @Override
    public double fitness(BitMat candidate) {
        double fitness = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                fitness += candidate.get(i, j) ? matrix[i][j] : 0;
            }
        }

        return fitness;
    }
}
