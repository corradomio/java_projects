package ae.ac.ebtic.tools.vr.data;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleList {
    private List<Vehicle> vehicles;

    // ----------------------------------------------------------------------

    public VehicleList(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }


    // ----------------------------------------------------------------------

    public Vehicle get(int i) {
        return this.getVehicles().get(i);
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    // public void setVehicles(List<Vehicle> vehicles) {
    //     this.vehicles = vehicles;
    // }

    public List<Location> getLocations(List<Location> locations) {
        return vehicles.stream().map(v -> locations.get(v.getLocId())).collect(Collectors.toList());
    }

    public List<String> getNames() {
        return vehicles.stream().map(Vehicle::name).toList();
    }


    // ----------------------------------------------------------------------
    // Loader from CSV
    // ----------------------------------------------------------------------

    public static VehicleList load(File vehiclesFile) throws Exception {
        List<Vehicle> vehicles;
        try (FileReader frdr = new FileReader(vehiclesFile)) {
            vehicles = new CsvToBeanBuilder(frdr)
                    .withType(Vehicle.class).build().parse();
        }

        return new VehicleList(vehicles);
    }

    public void save(File vehiclesFile) throws IOException {
        try(FileWriter outputfile  = new FileWriter(vehiclesFile)) {
            CSVWriter writer = new CSVWriter(outputfile, ',', (char)0, '\\', "\n");

            writer.writeNext(new String[]{"id","desc","locId"});
            for (Vehicle v : vehicles)
                writer.writeNext(new String[]{
                    String.valueOf(v.getId()),
                    String.valueOf(v.getDesc()),
                    String.valueOf(v.getLocId())
                });
        }
    }
}
