package jext.optim.heuristics.aco.tsp;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.pow;

public class PheromoneTrails {
    private final double pheromoneInfluence;
    private final double heuristicInfluence;
    private final double pheromoneDecay;

    private final int candidateSize;
    private final double[][] distanceMatrix;
    private final double[][] heuristics;
    private final double[][] pheromones;
    private final double[][] choices;

    private int colonySize;


    PheromoneTrails(
        double pheromoneInfluence,
        double heuristicInfluence,
        double pheromoneDecay,
        double[][] distanceMatrix
    ) {
        this.pheromoneInfluence = pheromoneInfluence;
        this.heuristicInfluence = heuristicInfluence;
        this.pheromoneDecay = pheromoneDecay;
        this.distanceMatrix = distanceMatrix;

        this.candidateSize = distanceMatrix.length;
        this.pheromones = new double[candidateSize][candidateSize];
        this.heuristics = new double[candidateSize][candidateSize];
        this.choices = new double[candidateSize][candidateSize];

    }

    public void initialize(int colonySize) {
        this.colonySize = colonySize;

        initializeHeuristics();
        initializePheromones();
        updateChoices();
    }

    private void initializeHeuristics() {
        // initialize heuristics
        for (int i=0; i<candidateSize; ++i)
            for (int j=0; j<candidateSize; ++j)
                if (i != j)
                    heuristics[i][j] = 1f/distanceMatrix[i][j];

    }

    private void initializePheromones() {

        // find the length of a tour
        double Cnn = new NearestNeighborhood(distanceMatrix).findNearestNeighborhoodDistance();

        // initialize pheromones
        for (int i=0; i<candidateSize; ++i)
            Arrays.fill(pheromones[i], colonySize/Cnn);
    }

    private void updateChoices() {
        int n = candidateSize;
        for (int i=0; i<n; ++i) {
            for (int j = 0; j < n; j++) {
                choices[i][j] = pow(pheromones[i][j], pheromoneInfluence)*pow(heuristics[i][j], heuristicInfluence);
            }
        }
    }

    public void updatePheromones(List<Ant> hive) {
        applyDecay();
        depositPheromones(hive);
        updateChoices();
    }

    private void applyDecay() {
        double decay = (1 - pheromoneDecay);

        // apply pheromones evaporation
        for(int i=0; i<candidateSize; i++) {
            for (int j=0; j<candidateSize; j++) {
                pheromones[i][j] *= decay;
            }
        }

    }

    private void depositPheromones(List<Ant> hive) {

        hive.forEach(ant -> {
            int[] tour = ant.getTour();
            double pheromone = 1/ant.getTourLength();
            pheromones[tour[candidateSize-1]][tour[0]] = pheromone;
            for(int i=1; i<candidateSize; i++)
                pheromones[tour[i-1]][tour[i]] += pheromone;
        });
    }

    // ----------------------------------------------------------------------

    public double[][] getChoices() {
        return choices;
    }
}
