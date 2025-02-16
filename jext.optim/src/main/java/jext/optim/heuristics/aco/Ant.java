package jext.optim.heuristics.aco;

import jext.util.MathUtils;

import java.util.Arrays;
import java.util.random.RandomGenerator;

public class Ant implements Comparable<Ant> {

    private final AntColony colony;
    // n of elements of the tour
    private final int n;
    // locations visited
    private final boolean[] visited;
    // locations composing the tour
    private final int[] tour;
    // sum of the locations distance
    private float tourLength;

    // used to find the list of neighbour locations not already visited
    private final int[] neighbors;
    // pheromones deposited on the edges between the current location and
    // the neighbour locations
    private final float[] pheromones;
    // sum of the pheromones of the neighbor locations
    private float totalPheromones;
    // number of neighbours
    private int neighboursLen;

    public Ant(AntColony colony) {
        this.colony = colony;
        this.n = colony.getCandidateSize();
        this.visited = new boolean[n];
        this.tour = new int[n];
        this.neighbors = new int[n];
        this.pheromones = new float[n];
    }

    public int[] getTour() {
        return tour;
    }

    public float getTourLength() {
        return tourLength;
    }

    public void findTour(RandomGenerator rng) {
        // random starting point
        int t, s = rng.nextInt(n);

        // initialization
        Arrays.fill(visited, false);
        tourLength = 0;
        tour[0] = s;
        visited[s] = true;

        for (int i=1; i<n; ++i) {
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
        int[] nearestNeighbours = colony.getNearestNeighbours(s);
        float[][] choices = colony.getChoices();
        float choice;

        neighboursLen = 0;
        totalPheromones = 0;
        for (int i=0; i<n; ++i) {
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
        float r = rng.nextFloat();
        float cumul = 0;
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
        tourLength += colony.getDistance(s, t);
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
