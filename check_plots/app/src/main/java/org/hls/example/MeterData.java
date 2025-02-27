package org.hls.example;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class MeterData {

    public static List<MeterData> load(File meteDataFile) throws IOException {
        List<MeterData> metersData;
        try (FileReader frdr = new FileReader(meteDataFile)) {
            metersData = new CsvToBeanBuilder(frdr)
                .withType(MeterData.class).build().parse();
        }

        return metersData;
    }

    private String Key;
    private String Field;
    private String Coordinates;
    private double lon = -1;
    private double lat = -1;


    public double getLongitude() {
        checkCoordinates();
        return lon;
    }

    public double getLatitude() {
        checkCoordinates();
        return lat;
    }

    private void checkCoordinates() {
        if (lon != -1) return;
        String[] parts = Coordinates.split("\\s");
        lat = Double.valueOf(parts[0]);
        lon = Double.valueOf(parts[1]);
    }

}
