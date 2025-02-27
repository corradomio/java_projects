package org.hls.examples;

import jext.util.TPrint;
import jext.util.concurrent.Parallel;

import java.io.File;
import java.util.List;

public class SortByDistance {

    public static void main(String[] args) throws Exception {
        Parallel.setup();

        LocationList locations = LocationList.load(new File("Meters Data.csv"));

        //
        //
        //
        for (String field : locations.fields())
            System.out.printf("%s: %d\n", field, locations.get(field).size());

        //
        // : 140746
        // A: 28954
        // R: 60323
        // C: 13373
        // D: 13879
        // U: 10971
        // F: 13246
        //

        for (String field : new String[]{"U", "F"}) {
            // TPrint.printf("Processing %s\n", field);

            double maxDistance = 1;

            List<List<Location>> sorted = locations.sort(field, maxDistance);

            locations.saveDFM(new File("meters_data-" + field + "-dfm.csv"), field);
            locations.saveDFF(new File("meters_data-" + field + "-dff.csv"), field);

            Parallel.shutdown();

            int maxLocations = 0;
            int minLocations = 1000000;
            for (List<Location> ll : sorted) {
                if (ll.size() > maxLocations)
                    maxLocations = ll.size();
                if (ll.size() < minLocations)
                    minLocations = ll.size();
            }

            System.out.printf("Min/Max number of locations at max distance %f: %d, %d\n", maxDistance, minLocations, maxLocations);
            System.out.println("end");
        }
    }
}
