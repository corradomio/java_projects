package org.hls.examples;

import java.util.List;

public class LocationsToVisit {

    private Distance distance;
    private List<Integer>[] v2locs;

    public LocationsToVisit(List<Integer>[] v2locs, Distance distance) {
        this.v2locs = v2locs;
        this.distance = distance;
    }

    public double totalDistance() {
        int m = v2locs.length;
        double total = 0;

        for (int vid=0; vid<m; ++vid)
            total += totalDistance(vid);

        return total;
    }

    public double totalDistance(int vid) {
        List<Integer> l = v2locs[vid];
        int l0, l1, n = l.size();
        double total = 0;

        // add the distance from the last location and the first one
        l0 = l.get(n-1);
        l1 = l.get(0);
        total += distance.matrix[l0][l1];

        // add the distance from the current location and the next one
        for(int i=0; i<n-1; ++i) {
            l0 = l.get(i);
            l1 = l.get(i+1);
            total += distance.matrix[l0][l1];
        }

        System.out.printf("vehicle %d: %.3f\n", vid, total);
        return total;
    }
}
