package jext.optim.heuristics.aco.tsp;

import jext.util.MathUtils;

import java.util.Arrays;
import java.util.random.RandomGenerator;

public class Ant implements Comparable<Ant> {

    private final TSPAntColony colony;
    // n of elements of the tour
    private final int candidateSize;
    // locations visited
    private final boolean[] visited;
    // locations composing the tour
    private final int[] tour;
    // sum of the locations distance
    private double tourLength;

    // used to find the list of neighbour locations not already visited
    private final int[] neighbors;
    // pheromones deposited on the edges between the current location and
    // the neighbour locations
    private final double[] pheromones;
    // sum of the pheromones of the neighbor locations
    private double totalPheromones;
    // number of neighbours
    private int neighboursLen;

    private final ConstructionGraph constructionGraph;
    private final PheromoneTrails pheromoneTrails;

    public Ant(TSPAntColony colony) {
        this.colony = colony;
        this.constructionGraph = colony.getConstructionGraph();
        this.pheromoneTrails = colony.getPheromoneTrails();

        this.candidateSize = colony.getCandidateSize();
        this.visited = new boolean[candidateSize];
        this.tour = new int[candidateSize];
        this.neighbors = new int[candidateSize];
        this.pheromones = new double[candidateSize];
    }

    public int[] getTour() {
        return tour;
    }

    public double getTourLength() {
        return tourLength;
    }

    public void findTour(RandomGenerator rng) {
        // random starting point
        int t, s = rng.nextInt(candidateSize);

        // initialization
        Arrays.fill(visited, false);
        tourLength = 0;
        tour[0] = s;
        visited[s] = true;

        for (int i = 1; i< candidateSize; ++i) {
            // find the list of valid neighbours
            findNeighbours(s);
            // find the random next location to visit
            t = findNext(rng);
            // update the tour
            updateTour(i, s, t);
            // continue
            s = t;
        }
    }

    private void findNeighbours(int s) {
        int[] nearestNeighbours = constructionGraph.getNearestNeighbours(s);
        double[][] choices = pheromoneTrails.getChoices();
        double choice;

        neighboursLen = 0;
        totalPheromones = 0;
        for (int i = 0; i< candidateSize; ++i) {
            int t = nearestNeighbours[i];
            if (visited[t]) continue;

            choice = choices[t][s];
            neighbors[neighboursLen] = t;
            pheromones[neighboursLen] = choice;
            totalPheromones += choice;

            neighboursLen++;
        }
    }

    private int findNext(RandomGenerator rng) {
        double r = rng.nextDouble();
        double cumul = 0;
        for(int i = 0; i< neighboursLen; ++i) {
            cumul += pheromones[i]/ totalPheromones;
            if (cumul >= r) return neighbors[i];
        }
        return neighbors[neighboursLen-1];
    }

    private void updateTour(int i, int s, int t) {
        // update the information about the tour
        tour[i] = t;
        visited[t] = true;
        tourLength += constructionGraph.getDistance(s, t);
    }

    @Override
    public int compareTo(Ant that) {
        return MathUtils.sign(tourLength - that.tourLength);
    }

    @Override
    public String toString() {
        return String.format("%s: %f", Arrays.toString(tour), tourLength);
    }
}
