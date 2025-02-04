package org.hls.examples;

import com.opencsv.bean.CsvToBeanBuilder;
import org.hls.examples.utils.LatLonUtils;

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

    public final int id;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public Location() {
        super();
        this.id = 0;
    }

    public Location(int id, double longitude, double latitude) {
        super(longitude, latitude);
        this.id = id;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public int id() {
        return id;
    }

    // public double longitude() { return longitude; }
    // public double latitude() { return latitude; }

    public double x() { return longitude; }
    public double y() { return latitude; }

    public double distance(Location other) {
        return LatLonUtils.distance(this.latitude, this.longitude, other.latitude, other.longitude);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        Location that = (Location) obj;
        return this.id == that.id;
    }
}
