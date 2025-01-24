package org.hls.example;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.util.List;



public class Location extends Coords {

    // ----------------------------------------------------------------------
    // Loader from CSV
    // ----------------------------------------------------------------------

    public static List<Location> load(File centersFile) throws Exception {
        List<Location> locations;
        try(FileReader frdr = new FileReader(centersFile)){
            locations = new CsvToBeanBuilder(frdr)
                .withType(Location.class).build().parse();
        }

        return locations;
    }

    // ----------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------

    private int id;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public Location() {

    }

    public Location(int id, double longitude, double latitude) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
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

    public double getLongitude() { return longitude; }
    public double getLatitude() { return latitude; }

    public double getX() { return longitude; }
    public double getY() { return latitude; }
}
