package org.hls.examples;

import com.ibm.icu.impl.Assert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Solution {

    public static Solution random(List<Location> toVisit, List<Vehicle> vehicles, Distance d, int seed) {
        Random rand = seed == 0 ? new Random() : new Random(seed);

        int n = toVisit.size();
        int m = vehicles.size();

        int[][] solution = new int[n][2];

        for (int l = 0; l < n; l++) {
            int lid = toVisit.get(l).id();
            int v = rand.nextInt(m);
            int vid = vehicles.get(v).id();

            solution[l][0] = lid;
            solution[l][1] = vid;
        }

        return new Solution(solution, toVisit, vehicles, d);
    }

    public static Solution clustering(List<Location> toVisit, List<Vehicle> vehicles, Distance d) {
        int i=0,j=0;
        int n = toVisit.size();
        int m = vehicles.size();

        // locations to visit id
        int[] visitIds = new int[n];
        for (Location l : toVisit)
            visitIds[i++] = l.id();

        // vehicles starting locations id
        int[] startIds = new int[m];
        for (Vehicle v : vehicles)
            startIds[j++] = v.locationId();

        // solution: for each location, the nearest vehicle
        int[][] solution = new int[n][2];
        double[][] distances = d.matrix;

        for(i=0; i<n; ++i) {

            int lid = visitIds[i];
            int sVehicle = -1;
            double bestDist = Double.MAX_VALUE;
            for(j=0; j<m; ++j) {
                int sid = startIds[j];
                // distance location->start location
                double dist = distances[lid][sid];

                if (dist < bestDist) {
                    sVehicle = j;
                    bestDist = dist;
                }
            }

            // location to visit
            solution[i][0] = lid;
            // vehicle visiting it
            solution[i][1] = sVehicle;
        }

        return new Solution(solution, toVisit, vehicles, d);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    // locations to visit
    private List<Location> toVisit;
    private Map<Integer, Location> toVisitMap = new HashMap<>();
    // vehicles with starting location
    private List<Vehicle> vehicles;
    // distances
    private Distance distance;

    // s[:][0]  : location to visit
    // s[:][1]  : vehicle visiting the location
    private int[][] solution;
    // vehicle -> ordered list of locations to visit
    private List<Integer>[] v2locs;

    public Solution(int[][] solution, List<Location> toVisit, List<Vehicle> vehicles, Distance distance) {
        // locationId -> vehicleId
        this.solution = solution;
        this.toVisit = toVisit;
        this.vehicles = vehicles;
        this.distance = distance;

        for(Location l : toVisit)
            toVisitMap.put(l.id(), l);

        for (Vehicle v : vehicles)
            toVisitMap.put(v.locationId(), v.location());
    }

    private void locationsVisitedByVehicles() {
        int m = vehicles.size();

        // scan all vehicles and initialize the list of locations visited with the vehicle's start location
        // v2locs = new List[m];
        Set<Integer> v2sets[] = new Set[m];

        // Initialize 'v2locs' with the starting location
        int j=0;
        for (Vehicle v : vehicles) {
            v2sets[j] = new HashSet<>();
            v2sets[j].add(v.locationId());
            ++j;
        }

        // scan all 'locations to visit/vehicle visiting it' and
        // collect the locations to visit for each vehicle
        int n = solution.length;
        for (int i=0; i<n; ++i) {
            int lid = solution[i][0];
            int vid = solution[i][1];

            v2sets[vid].add(lid);
        }

        v2locs = new List[m];
        for (j=0; j<m; ++j)
            v2locs[j] = new ArrayList<>(v2sets[j]);
    }

    private void orderLocations() {
        // scan all 'vehicles/locations to visit'
        // and order the locations (solve a mini TSP)
        int m = vehicles.size();
        for(int j=0; j<m; ++j) {
            // vehicle ordered locations
            v2locs[j] = orderLocations(j);;
        }
    }

    private List<Integer> orderLocations(int vid) {
        double dist, bestDist;
        int curr, test, next;
        // vehicle visited locations
        List<Integer> vvlocs = v2locs[vid];
        // vehicle ordered locations
        List<Integer> volocs = new ArrayList<>();
        // visited locations
        Set<Integer> visited = new HashSet<>();
        //
        int m = vvlocs.size();

        // start with first location
        curr = vvlocs.get(0);
        volocs.add(curr);
        visited.add(curr);

        // search the next location to visit

        while(volocs.size() < m) {
            next = -1;
            bestDist = Double.MAX_VALUE;

            for(Integer l : vvlocs) {
                if (visited.contains(l)) continue;

                test = l;
                dist = distance(curr, test);
                if (dist < bestDist) {
                    bestDist = dist;
                    next = test;
                }
            }

            if (next != -1) {
                volocs.add(next);
                visited.add(next);
            }
            else {
                // the vehicle must visit multiple times the same location ???
                m--;
            }
        }

        return volocs;
    }

    private double distance(int i, int j) {
        return this.distance.matrix[i][j];
    }

    public LocationsToVisit locationsToVisit() {
        // locations to visit for each vehicle
        locationsVisitedByVehicles();
        orderLocations();

        return new LocationsToVisit(v2locs, distance);
    }

    public void checkIfAllLocationsAreVisited() {
        List<Integer> visited = new ArrayList<>();
        for(List<Integer> vlocs : v2locs)
            visited.addAll(vlocs);

        if (visited.size() != (vehicles.size() + toVisit.size())) {
            System.out.println("OPPS");
            System.out.printf("  toVisit: %d\n", toVisit.size());
            System.out.printf("nVehicles: %d\n", vehicles.size());
            System.out.printf(" nVisited: %d\n", visited.size());
            System.out.printf("difference: %d\n", (vehicles.size() + toVisit.size()) - visited.size());
        }
    }

    public void save(File file) {
        int m = v2locs.length;
        try(Writer wrt = new FileWriter(file)) {
            wrt.write("vehicle_id,location_id,longitude,latitude\n");

            for(int j=0; j<m; ++j) {
                for (Integer lid : v2locs[j]) {
                    Location l = toVisitMap.get(lid);
                    wrt.write(String.format("%d,%d,%f,%f\n", j, lid, l.longitude, l.latitude));
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
