package ae.ac.ebtic.tools.vr.data;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationList {
    List<Location> locations;
    Map<Integer, Location> locsMap;

    public LocationList(List<Location> locations) {
        this.locations = locations;
        populateHashMap();
    }

    public void populateHashMap(){
        this.locsMap = new HashMap<>();
        for (Location l : locations) {
            locsMap.put(l.getId(), l);
        }
    }

    public Location get(int i){
        return this.getLocations().get(i);
    }

    public List<Location> getLocations() {
        return locations;
    }

    public int[] toArray() {
        return locations.stream().mapToInt(Location::getId).toArray();
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
        populateHashMap();
    }

    public Map<Integer, Location> getLocsMap() {
        return locsMap;
    }

    public static LocationList load(File centersFile) throws Exception {
        List<Location> locations;
        try(FileReader frdr = new FileReader(centersFile)){
            locations = new CsvToBeanBuilder(frdr)
                    .withType(Location.class).build().parse();
        }
        return new LocationList(locations);
    }

    public void save(File locationsFile) throws IOException {
        try(FileWriter outputfile  = new FileWriter(locationsFile)) {
            CSVWriter writer = new CSVWriter(outputfile, ',', (char)0, '\\', "\n");

            writer.writeNext(new String[]{"id","longitude","latitude"});
            for (Location l : locations)
                writer.writeNext(new String[]{
                    String.valueOf(l.getId()),
                    String.valueOf(l.getLongitude()),
                    String.valueOf(l.getLatitude())
                });
        }
    }
}
