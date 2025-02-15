package ae.ac.ebtic.tools.vr.data;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.util.List;



public class Location extends LonLat {

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
        super(longitude, latitude);
        this.id = id;
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
}
