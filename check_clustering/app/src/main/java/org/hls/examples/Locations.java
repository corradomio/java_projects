package org.hls.examples;

import jext.util.TPrint;
import jext.util.concurrent.Parallel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Locations {

    private final Map<String, List<Location>> locations = new HashMap<>();

    public Locations() {
        locations.put("", new ArrayList<>());
    }

    public void addLocation(String[] parts) {
        Location l = new Location(parts);
        if (!locations.containsKey(l.field))
            locations.put(l.field, new ArrayList<>());
        locations.get(l.field).add(l);
        locations.get("").add(l);
    }

    public Set<String> fields() {
        return locations.keySet();
    }

    public List<Location> get(String field) {
        return locations.get(field);
    }


    public List<List<Location>> sort(String field, final double maxDistance) {
        List<Location> tosort = get(field);

        TPrint.printf("Starting sort %s (%d) ...\n", field, tosort.size());

        List<List<Location>> sorted = Parallel.map(tosort, start ->
        {
                final String sfield = start.field;

                List<Location> ssorted = new ArrayList<>(locations.get(sfield));
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

            return ssorted;
        });

        TPrint.printf("... done (%d)\n", sorted.size());
        return sorted;
    }

}
