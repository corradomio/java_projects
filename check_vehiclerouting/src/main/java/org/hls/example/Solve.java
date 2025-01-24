package org.hls.example;

import java.io.File;
import java.util.List;

public class Solve {

    public static void main(String[] args) throws Exception {
        List<Location> locations = Location.load(new File("locations.csv"));
        List<Location> toVisit = Location.load(new File("locations_to_visit.csv"));
        List<Vehicle> vehicles = Vehicle.load(new File("vehicles.csv"));

        PlanarGraph pg = PlanarGraph.of(locations);

        Distance d = Distance.distanceMatrix(locations);

        System.out.println("Done");
    }
}
