package org.hls.examples;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Note: Vehicle extends Coords because in this way it is simple to draw it in the plots.
 * However, the only important information is 'location_id',the starting location.
 */
public class Vehicle extends Coords {

    // ----------------------------------------------------------------------
    // Loader from CSV
    // ----------------------------------------------------------------------

    public static List<Vehicle> load(File vehiclesFile) throws Exception {
        List<Vehicle> vehicles;
        try (FileReader frdr = new FileReader(vehiclesFile)) {
            vehicles = new CsvToBeanBuilder(frdr)
                .withType(Vehicle.class).build().parse();
        }

        return vehicles;
    }

    // ----------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------

    private int id;
    private Location start;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public Vehicle() {

    }

    public Vehicle(int id, Location l) {
        super(l.longitude, l.latitude);
        this.id = id;
        this.start = l;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public int id() {
        return id;
    }

    public int locationId() {
        return start.id;
    }

    public Location location() {
        return start;
    }

}
