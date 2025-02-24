package org.hls.examples;

import com.opencsv.CSVReader;
import jext.util.concurrent.Parallel;

import java.io.FileReader;
import java.util.List;

public class SortByDistance {

    public static void main(String[] args) throws Exception {
        Parallel.setup();

        Locations locations = new Locations();

        //
        // import data
        //
        CSVReader csvReader = new CSVReader(new FileReader("Meters Data.csv"));
        // skip header
        csvReader.readNext();
        String[] parts;
        while ((parts = csvReader.readNext()) != null) {
            // String Key = nextLine[0];
            // String Field = nextLine[1];
            // String Coordinates = nextLine[2];
            // System.out.printf("%s, %s, %s\n", Key, Field, Coordinates);

            locations.addLocation(parts);
        }

        //
        //
        //
        for (String field : locations.fields())
            System.out.printf("%s: %d\n", field, locations.get(field).size());

        //
        //
        //
        String field = "R";
        double maxDistance = 1;

        List<List<Location>> sorted = locations.sort(field, maxDistance);

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

        Parallel.shutdown();
    }

}
