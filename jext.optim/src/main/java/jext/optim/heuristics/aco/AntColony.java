package jext.optim.heuristics.aco;

import jext.util.ArrayUtils;
import jext.util.MathUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.random.RandomGenerator;

import static jext.util.MathUtils.pow;

public class AntColony {

    private static final float EPS = 1e-10f;
    private static boolean isSmall(float x) {
        if (x > +EPS) return false;
        if (x < -EPS) return false;
        return true;
    }

    private final int colonySize;
    private final float elitismRate;
    private final float pheromoneInfluence;
    private final float heuristicInfluence;
    private final float pheromoneDecay;

    private final List<Ant> hive = new ArrayList<>();

    private final int candidateSize;
    private final int elitismSize;
    private final float[][] distanceMatrix;
    private final float[][] heuristics;
    private final float[][] pheromones;
    private final float[][] choices;
    private final int[][] nearestNeighbours;

    public AntColony(
        int colonySize,
        double elitismRate,
        double pheromoneInfluence, double heuristicInfluence,
        double pheromoneDecay,
        float[][] distanceMatrix
    ) {
        this.colonySize = colonySize;
        this.elitismRate = (float) elitismRate;
        this.pheromoneInfluence = (float) pheromoneInfluence;
        this.heuristicInfluence = (float) heuristicInfluence;
        this.pheromoneDecay = (float) pheromoneDecay;
        this.distanceMatrix = distanceMatrix;
        this.elitismSize = (elitismRate >=1) ? (int)elitismRate : (int)(colonySize*elitismRate);

        this.candidateSize = distanceMatrix.length;
        this.heuristics = new float[candidateSize][candidateSize];
        this.pheromones = new float[candidateSize][candidateSize];
        this.nearestNeighbours = new int[candidateSize][candidateSize];
        this.choices = new float[candidateSize][candidateSize];
    }

    public int getCandidateSize() {
        return candidateSize;
    }

    public float getDistance(int i, int j) {
        return distanceMatrix[i][j];
    }

    public int[] getNearestNeighbours(int s) {
        return nearestNeighbours[s];
    }

    public float[][] getChoices() {
        return choices;
    }

    public Tour getBestTour() {
        int[] tour = null;
        float tourLength = Float.MAX_VALUE;;
        for(Ant ant : hive) {
            if (ant.getTourLength() < tourLength) {
                tour = ant.getTour();
                tourLength = ant.getTourLength();
            }
        }

        return new Tour(tour, tourLength);
    }

    public void initialize() {
        int n = candidateSize;

        // find the length of a tour
        float Cnn = new NearestNeighborhood(distanceMatrix).findNearestNeighborhoodDistance();

        // initialize pheromones
        for (int i=0; i<n; ++i)
            Arrays.fill(pheromones[i], colonySize/Cnn);

        // initialize heuristics
        for (int i=0; i<n; ++i)
            for (int j=0; j<n; ++j)
                if (i != j)
                    heuristics[i][j] = 1f/distanceMatrix[i][j];

        // initialize nearest neighbours
        for (int i=0; i<n; ++i)
            for (int j=0; j<n; ++j)
                nearestNeighbours[i][j] = j;

        // sort nearest neighbours
        for (int i=0; i<n; i++) {
            final int s = i;
            ArrayUtils.sort(nearestNeighbours[s], (a, b) ->
                MathUtils.sign(distanceMatrix[s][a] - distanceMatrix[s][b])
            );
        }

        // create the ants
        for (int i=0; i<colonySize; i++)
            hive.add(new Ant(this));

        // initialize the ants with a first tour
        // this is necessary to support 'elitism'
        final RandomGenerator rng = AntColonyOptimization.getRandomGenerator();
        hive.stream().limit(elitismSize).forEach(ant -> ant.findTour(rng));
    }

    public AntColony nextGeneration() {
        findTours();
        sortAnts();
        updatePheromones();
        updateChoices();

        return this;
    }

    private void findTours() {
        RandomGenerator rng = AntColonyOptimization.getRandomGenerator();
        hive.stream().skip(elitismSize).parallel().forEach(ant -> ant.findTour(rng));
    }

    private void sortAnts() {
        hive.sort(Ant::compareTo);
    }

    private void updatePheromones() {
        float decay = (1 - pheromoneDecay);

        // apply pheromones evaporation
        for(int i=0; i<candidateSize; i++) {
            for (int j=0; j<candidateSize; j++) {
                pheromones[i][j] *= decay;
            }
        }

        // deposit ant pheromones
        hive.forEach(ant -> {
            int[] tour = ant.getTour();
            float pheromone = 1/ant.getTourLength();
            pheromones[tour[candidateSize-1]][tour[0]] = pheromone;
            for(int i=1; i<candidateSize; i++)
                pheromones[tour[i-1]][tour[i]] += pheromone;
        });
    }

    private void updateChoices() {
        int n = candidateSize;
        for (int i=0; i<n; ++i) {
            for (int j = 0; j < n; j++) {
                choices[i][j] = pow(pheromones[i][j], pheromoneInfluence)*pow(heuristics[i][j], heuristicInfluence);
            }
        }
    }

}
