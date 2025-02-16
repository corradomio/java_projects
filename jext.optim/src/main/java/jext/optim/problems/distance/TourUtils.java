package jext.optim.problems.distance;

import java.util.Random;

public class TourUtils {

    /** Create a rando tour of ``n'' locations */
    public  static int[] randomTour(int n) {
        return randomTour(defaultTour(n));
    }

    /** Create a random tour using ``locations'' */
    public  static int[] randomTour(int[] locations) {
        Random random = new Random();
        int n = locations.length;

        int[] rtour = new int[n];
        for(int i = 0; i < n; i++) {
            rtour[i] = locations[i];
        }

        for (int i = 0; i < n; ++i) {
            int index = random.nextInt(i + 1);
            // Simple swap
            int a = rtour[index];
            rtour[index] = rtour[i];
            rtour[i] = a;
        }

        return rtour;
    }

    /** Create the default tour [0,1,...n-1] */
    public static int[] defaultTour(int n) {
        int[] ids = new int[n];
        for(int i=0; i<n; ++i)
            ids[i] = i;
        return ids;
    }

    /** Rotate the tour to have ``start'' in position 0 */
    public static int[] reorderTour(int[] tour, int start) {
        int size = tour.length;
        int at;
        for (at = 0; at <size; ++at)
            if (tour[at] == start)
                break;

        int[] reordered = new int[size];
        for (int i = 0; i < size; ++i)
            reordered[i] = tour[(at + i)%size];
        return reordered;
    }

    /** Resolve the locat tour ``tour'' using the effective ``locations'' */
    public static  int[] resolveTour(int[] locations, int[] tour) {
        int n = tour.length;
        int[] ltour = new int[n];
        for (int i=0; i<n; ++i)
            ltour[i] = locations[tour[i]];
        return ltour;
    }

    /** Normalize the tour locations order: tour[1] must be < tour[n-1] */
    public static int[] normalizeTour(int[] tour) {
        int n = tour.length;
        if (n > 2 && tour[1] < tour[n-1])
            return tour;
        int[] ntour = new int[n];
        ntour[0] = tour[0];
        for (int i=1; i<n; ++i)
            ntour[i] = tour[n-i];

        return ntour;
    }

    /** Convert a tour in a string ``[ t1, t2, ... ]'' */
    public static String toString(int[] tour) {
        StringBuilder sb = new StringBuilder("[");
        if (tour.length > 0)
            sb.append(tour[0]);
        for(int i=1; i<tour.length; ++i)
            sb.append(", ").append(tour[i]);
        sb.append(" ]");
        return sb.toString();
    }

}
