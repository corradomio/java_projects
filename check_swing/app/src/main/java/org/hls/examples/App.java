package org.hls.examples;

import com.opencsv.CSVReader;
import de.fhpotsdam.unfolding.marker.Marker;
import jext.swing.JMapPanel;
import jext.swing.map.PointMarker;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {

        AppForm frame = new AppForm();
        JMapPanel jmap = frame.getMapPanel();

        List<Marker> points = loadPoints("U");

        jmap.addMarkers(points);

        EventQueue.invokeLater(() -> {
            // AppForm frame = new AppForm();
            frame.setVisible(true);
        });
    }

    private static List<Marker> loadPoints(String useField) {
        List<Marker> pointMarkers = new ArrayList<>();

        String fileName = "Meters Data.csv";
        try (Reader reader = new FileReader(fileName)) {
            CSVReader csvReader = new CSVReader(reader);
            // skip header
            // Key,Field,Coordinates
            // ABEK2000057552,A,25.3969357 55.4302751
            //
            // Abu Dhabi Coordinates
            //
            //     latitude    longitude
            //     24.4539° N, 54.3773° E
            csvReader.readNext();

            // int index = 0;
            String[] parts;
            while ((parts = csvReader.readNext()) != null) {
                // Key,Field,Coordinates

                // WARN: the feeder id MUST BE AN INTEGER
                // NOTE: it seems it is NOT necessary.
                //       The code where the id is converted into an integer now it is commented!
                // String id = String.valueOf(++index);
                String id = parts[0];

                String field = parts[1];
                if (useField != null && !field.equals(useField))
                    continue;

                String[] latlon = parts[2].split("\\s");
                double latitude = Double.valueOf(latlon[0]);
                double longitude = Double.valueOf(latlon[1]);

                PointMarker marker = new PointMarker(
                    id,
                    latitude, longitude
                )
                    .color(Color.RED)
                    .scale(0.5f);

                pointMarkers.add(marker);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pointMarkers;
    }
}
