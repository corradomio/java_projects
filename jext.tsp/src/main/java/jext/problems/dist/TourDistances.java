package jext.problems.dist;

import jext.problems.Coords;
import jext.problems.Distances;
import jext.problems.tsp.TourUtils;

import java.util.Optional;

public class TourDistances implements Distances {

    private final Distances distances;
    private int[] deposits;
    private int[] locations;
    private int nDeposits;

    public TourDistances(Distances distances) {
        this.distances = distances;
        this.locations = TourUtils.defaultTour(distances.size());
    }

    public TourDistances withLocations(int[] locations) {
        this.deposits = new int[0];
        this.locations = locations;
        this.nDeposits = 0;

        return this;
    }

    public TourDistances withDepositsAndLocations(int[] deposits, int[] locations) {
        this.deposits = deposits;
        this.locations = locations;
        this.nDeposits = deposits.length;
        return this;
    }

    /** Length of the selected deposits & locations */
    @Override
    public int size() {
        return deposits.length + locations.length;
    }

    /** Number of all possible locations to visit */
    public int order() {
        return this.distances.size();
    }

    /**  Distance matrix */
    public Distances getDistances() {
        return distances;
    }

    /** List of coordinates */
    public Optional<Coords[]> coordinates() {
        if (!(distances instanceof MetricSpace))
            return Optional.empty();

        MetricSpace mspace = (MetricSpace) distances;
        int n = deposits.length;
        int m = locations.length;
        int k = 0;

        Coords[] coords = new Coords[n+m];

        // fill the deposits
        for (int i=0; i<n; ++i)
            coords[k++] = mspace.get(deposits[i]);

        // fill the locations
        for (int i=0; i<m; ++i)
            coords[k++] = mspace.get(locations[i]);

        return Optional.of(coords);
    }

    /**
     * Distance from the local location i and j.
     * The local locations i and j are converted in the effective locations
     * specified by ``locations''
     *
     * If ``deposi'' are present, they are located at the begin of the ``virtual matrix''
     *
     * @param i start location
     * @param j destination location
     * @return distance
     */
    @Override
    public float distance(int i, int j) {
        int li = toLocation(i);
        int lj = toLocation(j);

        return distances.distance(li, lj);
    }

    private int toLocation(int i) {
        if (i < nDeposits)
            return deposits[i];
        else
            return locations[i-nDeposits];
    }


    /**
     * Resolve the tour specified in local locations into the effective
     * locations specified by ``location''
     * @param tour local locations tour
     * @return effective tour
     */
    public int[] resolve(int[] tour) {
        tour = TourUtils.resolveTour(locations, tour);
        tour = TourUtils.reorderTour(tour, locations[0]);
        tour = TourUtils.normalizeTour(tour);
        return tour;
    }

    /**
     * Create the distance matrix based on the list of locations specified
     * @return the distance matrix
     */
    @Override
    public float[][] getMatrix() {
        int n = this.locations.length;
        float[][] dmatrix = new float[n][n];

        for (int i=0; i<n; ++i)
            for (int j=0; j<n; ++j)
                dmatrix[i][j] = distances.distance(i, j);

        return dmatrix;
    }
}
