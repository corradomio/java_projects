package org.hls.examples.utils;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Location extends Coords {
    private int id;

    public Location() { }

    public Location(int id, double longitude, double latitude) {
        super(longitude, latitude);
        this.id = id;
    }

    public static List<Location> load(File locationsFile) throws IOException {
        List<Location> locations;
        try(FileReader frdr = new FileReader(locationsFile)){
            locations = new CsvToBeanBuilder(frdr)
                .withType(Location.class).build().parse();
        }
        return locations;
    }
}
