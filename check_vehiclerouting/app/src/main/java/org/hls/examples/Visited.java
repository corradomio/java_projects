package org.hls.examples;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Visited {

    // ----------------------------------------------------------------------
    // Loader from CSV
    // ----------------------------------------------------------------------

    public static List<Location>[] load(File vehiclesFile) throws Exception {
        List<Visited> visited;
        try (FileReader frdr = new FileReader(vehiclesFile)) {
            visited = new CsvToBeanBuilder(frdr)
                .withType(Visited.class).build().parse();
        }

        // count number of vehicles
        int m = 0;
        for (Visited v : visited)
            if (v.vehicleId() >= m)
                m = v.vehicleId()+1;

        List<Location>[] v2locs = new List[m];
        for (int i=0; i<m; ++i)
            v2locs[i] = new ArrayList<>();

        for(Visited v : visited) {
            int vid = v.vehicleId();
            Location l = new Location(v.locationId(), v.longitude, v.latitude);
            v2locs[vid].add(l);
        }

        return v2locs;
    }


    private int vehicle_id;
    private int location_id;
    private double longitude;
    private double latitude;

    public Visited() {

    }

    public int vehicleId() {
        return vehicle_id;
    }

    public int locationId() {
        return location_id;
    }
}
