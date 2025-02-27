package org.hls.examples;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import jext.util.TPrint;
import jext.util.concurrent.Parallel;
import jext.util.concurrent.Serial;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LocationList {

    private static class Index {
        int index;

        public int index() {
            return index++;
        }
    }

    private final Map<String, List<Location>> locationsMap = new HashMap<>();
    private final Map<String, List<List<Location>>> sortedLocationsMap = new HashMap<>();
    private final Map<String, Index> indexMap = new HashMap<>();
    private double maxDistance;

    // ----------------------------------------------------------------------

    public LocationList() {

    }

    // ----------------------------------------------------------------------

    public Set<String> fields() {
        return locationsMap.keySet();
    }

    public List<Location> get(String field) {
        return locationsMap.get(field);
    }

    // ----------------------------------------------------------------------

    private void addLocation(String[] parts) {
        Location l = new Location(parts);

        if (!locationsMap.containsKey(l.field)) {
            indexMap.put(l.field, new Index());
            locationsMap.put(l.field, new ArrayList<>());
        }
        l.index = indexMap.get(l.field).index();
        locationsMap.get(l.field).add(l);

    }

    public List<List<Location>> sort(String field, final double maxDistance) {
        List<Location> tosort = get(field);

        this.maxDistance = maxDistance;

        TPrint.printf("Starting sort %s (%d) ...\n", field, tosort.size());

        List<List<Location>> sortedLocations = Parallel.map(tosort, start ->
        {
            final String sfield = start.field;

            List<Location> ssorted = new ArrayList<>(locationsMap.get(sfield));
            ssorted = ssorted.stream()
                .filter(l -> {
                    double d = start.haversineDistance(l);
                    return d <= maxDistance;
                })
                .sorted((l1, l2) -> {
                    double d1 = start.angularDistance(l1);
                    double d2 = start.angularDistance(l2);
                    return Double.compare(d1, d2);
                })
                .toList();

            moveFirst(ssorted, start);

            return ssorted;
        });

        sortedLocationsMap.put(field, sortedLocations);

        TPrint.printf("... done (%d)\n", sortedLocations.size());
        return sortedLocations;
    }

    private static void moveFirst(List<Location> list, Location first) {
        int index = 0;
        for (; index < list.size(); ++index)
            if (list.get(index).equals(first))
                break;

        if (index != 0) {
            list = new ArrayList<>(list);
            list.remove(index);
            list.add(0, first);
        }
    }

    /// Sort the sorted map
    public void clear() {
        this.sortedLocationsMap.clear();
    }

    // ----------------------------------------------------------------------

    public static LocationList load(File fileName) throws IOException, CsvValidationException {
        LocationList locations = new LocationList();

        try (Reader reader = new FileReader(fileName)) {
            CSVReader csvReader = new CSVReader(reader);
            // skip header
            csvReader.readNext();
            String[] parts;
            while ((parts = csvReader.readNext()) != null) {
                locations.addLocation(parts);
            }
        }

        return locations;
    }

    private static final String[] DFM_HEADER = new String[]{
        "",
        "lat",
        "long",
        "Area",
        "Location",
        "Connection Object",
        "Legacy Act Id",
        "Meter Serial Number",
        "MRU", "New Meter Type",
        "Polygon",
        "Premise Description",
        "SN.",
        "Serial Number",
        "MSN",
        "neighbour",
        "RSSI",
        "type",
        "is_feeder",
        "idx",
        "lon",
        "x",
        "y"
    };

    public void saveDFM(File fileName, String field) throws IOException {

        try (Writer writer = new FileWriter(fileName)) {
            CSVWriter csvWriter = new CSVWriter(writer);

            csvWriter.writeNext(DFM_HEADER, false);

            // to fill ONLY [1,2,8]
            List<List<Location>> sortedLocations = sortedLocationsMap.get(field);

            for (List<Location> sorted : sortedLocations) {
                // first element is the start location
                Location loc = sorted.get(0);

                String[] record = new String[]{
                    // (0) "",
                    String.valueOf(loc.index),
                    // (1) "lat",
                    String.valueOf(loc.latitude),
                    // (2) "long",
                    String.valueOf(loc.longitude),
                    // "Area",
                    String.valueOf((int) loc.field.charAt(0)),
                    // "Location",
                    String.format("%f, %f", loc.longitude, loc.latitude),
                    // "Connection Object",
                    "0.0",
                    // "Legacy Act Id",
                    "",
                    // "Meter Serial Number",
                    loc.key,
                    // (8) "MRU",
                    loc.key,
                    // "New Meter Type",
                    "Unknown",
                    // "Polygon",
                    "Unknown",
                    // "Premise Description",
                    "Unknown",
                    // "SN.",
                    loc.key,
                    // "Serial Number",
                    loc.key,
                    // "MSN",
                    String.valueOf(loc.index),
                    // "neighbour" (loc.key?),
                    // "['<id>_f', ...]
                    neighboursList(sorted),
                    // "RSSI" (distances?),
                    distancesList(sorted),
                    // "type",
                    "meter",
                    // "is_feeder",
                    "False",
                    // "idx",
                    "1",
                    // "lon",
                    String.valueOf(loc.longitude),
                    // "x",
                    String.valueOf(loc.latitude),
                    // "y"
                    String.valueOf(loc.longitude)
                };

                csvWriter.writeNext(record, false);
            }
        }

    }

    private static String neighboursList(List<Location> sorted) {
        // skip the first element
        sorted = sorted.subList(1, sorted.size());

        // "['<id>_f', ...]"

        StringBuilder sb = new StringBuilder("[");
        for (Location loc : sorted) {
            if (sb.length() > 2) sb.append(", ");
            sb.append(String.format("'%d_f'", loc.index));
        }
        sb.append("]");
        return sb.toString();
    }

    private static String distancesList(List<Location> sorted) {
        // reference location
        Location first = sorted.get(0);
        // skip the first element
        sorted = sorted.subList(1, sorted.size());

        StringBuilder sb = new StringBuilder("[");
        for (Location loc : sorted) {
            double dist = first.haversineDistance(loc);
            if (sb.length() > 1) sb.append(", ");
            sb.append(dist);
        }
        sb.append("]");
        return sb.toString();
    }

    private static final String[] DFF_HEADER = new String[]{
        "",
        "lon",
        "lat",
        "Area",
        "Fname",
        "Fnumber",
        "SN.",
        "Type",
        "feeder_id",
        "Polygon",
        "type", "is_feeder",
        "idx",
        "x",
        "y"
    };

    public void saveDFF(File fileName, String field) throws IOException {

        try (Writer writer = new FileWriter(fileName)) {
            CSVWriter csvWriter = new CSVWriter(writer);

            csvWriter.writeNext(DFF_HEADER, false);

            List<List<Location>> sortedLocations = sortedLocationsMap.get(field);

            for (List<Location> sorted : sortedLocations) {
                Location loc = sorted.get(0);

                String[] record = new String[]{
                    // "",
                    String.valueOf(loc.index),
                    // "lon",
                    String.valueOf(loc.longitude),
                    // "lat",
                    String.valueOf(loc.latitude),
                    // "Area",
                    String.valueOf((int) loc.field.charAt(0)),
                    // "Fname",
                    "",
                    // "Fnumber",
                    String.valueOf(loc.index),
                    // "SN.",
                    String.format("%d_f", loc.index),
                    // "Type",
                    loc.field,
                    // "feeder_id",
                    String.valueOf(loc.index),
                    // "Polygon",
                    "Unknown",
                    // "type",
                    "feeder",
                    // "is_feeder",
                    "True",
                    // "idx",
                    "1",
                    // "x",
                    String.valueOf(loc.latitude),
                    // "y"
                    String.valueOf(loc.longitude)
                };

                csvWriter.writeNext(record, false);
            }
        }
    }
}
