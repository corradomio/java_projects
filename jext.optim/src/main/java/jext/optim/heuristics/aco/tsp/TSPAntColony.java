package jext.optim.heuristics.aco.tsp;

import jext.optim.heuristics.aco.AntColony;
import jext.optim.heuristics.aco.AntColonyOptimization;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class TSPAntColony implements AntColony {

    private final int colonySize;
    private final double elitismRate;

    private final List<Ant> hive = new ArrayList<>();

    private final int candidateSize;
    private final int elitismSize;

    private final ConstructionGraph constructionGraph;
    private final PheromoneTrails pheromoneTrails;

    public TSPAntColony(
        int colonySize, double elitismRate,
        double pheromoneInfluence, double heuristicInfluence,
        double pheromoneDecay,
        double[][] distanceMatrix
    ) {
        this.colonySize = colonySize;
        this.elitismRate = elitismRate;
        this.elitismSize = (elitismRate >=1) ? (int)elitismRate : (int)(colonySize*elitismRate);

        this.candidateSize = distanceMatrix.length;

        this.pheromoneTrails = new PheromoneTrails(
            pheromoneInfluence,
            heuristicInfluence,
            pheromoneDecay,
            distanceMatrix
        );
        this.constructionGraph = new ConstructionGraph(distanceMatrix);
    }

    public int getCandidateSize() {
        return candidateSize;
    }

    public ConstructionGraph getConstructionGraph() {
        return constructionGraph;
    }

    public PheromoneTrails getPheromoneTrails() {
        return pheromoneTrails;
    }

    // public double getDistance(int i, int j) {
    //     return constructionGraph.getDistance(i, j);
    // }

    // public int[] getNearestNeighbours(int s) {
    //     return constructionGraph.getNearestNeighbours(s);
    // }

    // public double[][] getChoices() {
    //     return pheromoneTrails.getChoices();
    // }

    @Override
    public Tour getFittestSolution() {
        int[] tour = null;
        double tourLength = Double.MAX_VALUE;;
        for(Ant ant : hive) {
            if (ant.getTourLength() < tourLength) {
                tour = ant.getTour();
                tourLength = ant.getTourLength();
            }
        }

        return new Tour(tour, tourLength);
    }

    public void initialize() {
        pheromoneTrails.initialize(colonySize);
        constructionGraph.initialize();

        // create the ants
        for (int i=0; i<colonySize; i++)
            hive.add(new Ant(this));

        // initialize the ants with a first tour
        // this is necessary to support 'elitism'
        final RandomGenerator rng = AntColonyOptimization.getRandomGenerator();
        hive.stream().limit(elitismSize).forEach(ant -> ant.findTour(rng));
    }

    public void nextGeneration() {
        findTours();
        sortAnts();
        updatePheromones();
    }

    private void findTours() {
        RandomGenerator rng = AntColonyOptimization.getRandomGenerator();
        hive.stream().skip(elitismSize).parallel().forEach(ant -> ant.findTour(rng));
    }

    private void sortAnts() {
        hive.sort(Ant::compareTo);
    }

    private void updatePheromones() {
        pheromoneTrails.updatePheromones(hive);
    }

}
