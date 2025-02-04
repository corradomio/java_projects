package org.hls.examples;

import java.io.File;
import java.util.List;

public class Solve {

    public static void main(String[] args) throws Exception {
        List<Location> locations = Location.load(new File("locations.csv"));
        List<Location> toVisit = Location.load(new File("locations_to_visit.csv"));
        List<Vehicle> vehicles = Vehicle.load(new File("vehicles.csv"));

        Distance d = Distance.distanceMatrix(locations);
        {
            Solution s = Solution.clustering(toVisit, vehicles, d);
            double totalDistance = s.locationsToVisit().totalDistance();
            s.checkIfAllLocationsAreVisited();
            System.out.printf("Total distance: %.3f\n", totalDistance);
            s.save(new File("clustering.csv"));
        }


        // System.out.println("-----------");
        // {
        //     Solution s = Solution.random(toVisit, vehicles, d, 42);
        //     double totalDistance = s.locationsToVisit().totalDistance();
        //     s.checkIfAllLocationsAreVisited();
        //     System.out.printf("Total distance: %.3f\n", totalDistance);
        //     s.save(new File("random.csv")   );
        // }


    }
}
