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
    private int location_id;
    private List<Coords> locationsVisited = new ArrayList<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public Vehicle() {

    }

    public Vehicle(int id, Location l) {
        this.id = id;
        this.location_id = l.getId();
        this.longitude = l.getLongitude();
        this.latitude = l.getLatitude();
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public int getId() {
        return id;
    }

    // public void setId(int id) {
    //     this.id = id;
    // }

    public int getLocationId() {
        return location_id;
    }

    // public void setLocation_id(int location_id) {
    //     this.location_id = location_id;
    // }

    public List<Coords> getLocationsVisited() {
        return locationsVisited;
    }

    // public void setLocationsVisited(List<Coords> locationsVisited) {
    //     this.locationsVisited = locationsVisited;
    // }
}
