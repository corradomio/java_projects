package ae.ac.ebtic.tools.vr.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Note: Vehicle extends Coords because in this way it is simple to draw it in the plots.
 * However, the only important information is 'location_id',the starting location.
 */
public class Vehicle {

    // ----------------------------------------------------------------------
    // Loader from CSV
    // ----------------------------------------------------------------------

//    public static List<Vehicle> load(File vehiclesFile) throws Exception {
//        List<Vehicle> vehicles;
//        try (FileReader frdr = new FileReader(vehiclesFile)) {
//            vehicles = new CsvToBeanBuilder(frdr)
//                .withType(Vehicle.class).build().parse();
//        }
//
//        return vehicles;
//    }

    // ----------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------

    private int id;
    private String desc;
    private int locId;

    private List<LonLat> locationsVisited = new ArrayList<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public Vehicle() {
    }

    public Vehicle(int id, String desc, int locId) {
        this.id = id;
        this.desc = desc;
        this.locId = locId;
    }

// ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public String name() {
        return String.format("V%02d", id);
    }

    public int getId() {
        return id;
    }

    // public void setId(int id) {
    //     this.id = id;
    // }

    public int getLocId() {
        return locId;
    }

    public String getDesc() {
        return desc;
    }


    // public void setLocation_id(int location_id) {
    //     this.location_id = location_id;
    // }

    public List<LonLat> getLocationsVisited() {
        return locationsVisited;
    }

    // public void setLocationsVisited(List<Coords> locationsVisited) {
    //     this.locationsVisited = locationsVisited;
    // }

    // ----------------------------------------------------------------------
    // Support for HashMap
    // ----------------------------------------------------------------------

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj){
        Vehicle that = (Vehicle) obj;
        return this.id == that.id;
    }

}
