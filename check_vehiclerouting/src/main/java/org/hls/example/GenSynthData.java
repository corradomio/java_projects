package org.hls.example;

import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.context.jts.JtsSpatialContext;
import org.locationtech.spatial4j.context.jts.JtsSpatialContextFactory;
import org.locationtech.spatial4j.io.GeoJSONReader;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.Shape;
import org.locationtech.spatial4j.shape.ShapeCollection;
import org.locationtech.spatial4j.shape.SpatialRelation;
import org.locationtech.spatial4j.shape.impl.RectangleImpl;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenSynthData {

    private static final String EMIRATES_HOME = "data/emirates";

    private static String[] EMIRATES = {
        "Abu Dhabi",
        "Ajman",
        "Dubay",
        "Fujayrah",
        "Ras Al Khaymah",
        "Sharjah",
        "Umm Al Qaywayn"
    };

    // private static double[] EXCLUDE_LON = new double[]{52.1,54.4};
    // private static double[] EXCLUDE_LAT = new double[]{24.2,24.9};

    private static final Random rnd = new Random();

    private static Shape loadUAE() throws Exception {
        System.out.println("... loadUAE");

        JtsSpatialContextFactory factory = new JtsSpatialContextFactory();
        JtsSpatialContext ctx = new JtsSpatialContext(factory);

        GeoJSONReader georeader = new GeoJSONReader(ctx, factory);

        List<Shape> emirates = new ArrayList<>();
        for (String emirate : EMIRATES) {
            String filePath = String.format("%s/%s.json", EMIRATES_HOME, emirate);
            try (Reader reader = new FileReader(new File(filePath))) {
                Shape geo = georeader.read(reader);
                emirates.add(geo);
            }
        }

        return new ShapeCollection<>(emirates, ctx);
    }

    private static Location[] generateLocations(Shape UAE, int nLocations) throws Exception {
        System.out.println("... generateLocations");

        Rectangle bbox = UAE.getBoundingBox();
        // Rectangle ebox = new RectangleImpl(EXCLUDE_LON[0], EXCLUDE_LON[1], EXCLUDE_LAT[0], EXCLUDE_LAT[1], bbox.getContext());
        SpatialContext ctx = UAE.getContext();

        Location[] locations = new Location[nLocations];

        try(Writer wrt = new FileWriter("locations.csv")) {
            wrt.write(String.format("id,longitude,latitude\n"));

            int npoints = 0;
            while (npoints < nLocations) {
                SpatialRelation srel;
                double x = rnd.nextDouble(bbox.getMinX(), bbox.getMaxX());
                double y = rnd.nextDouble(bbox.getMinY(), bbox.getMaxY());

                Point pt = ctx.getShapeFactory().pointXY(x, y);

                // srel = ebox.relate(pt);
                // if (srel == SpatialRelation.CONTAINS)
                //     continue;

                srel = UAE.relate(pt);
                if (srel != SpatialRelation.CONTAINS)
                    continue;

                // System.out.printf("%f,%f\n", pt.getX(), pt.getY());
                locations[npoints] = new Location(npoints, pt.getX(), pt.getY());
                wrt.write(String.format("%d,%f,%f\n", npoints, pt.getX(), pt.getY()));
                ++npoints;
            }

        }

        return locations;
    }

    private static void generateLocationsToVisit(int nLocationsToVisit, Location[] locations) throws Exception {
        System.out.println("... generateLocationsToVisit");

        int[] toVisit = new int[nLocationsToVisit];
        int nLocations = locations.length;

        for (int i=0; i<nLocationsToVisit; ++i)
            toVisit[i] = rnd.nextInt(nLocations);

        // Arrays.sort(toVisit);

        try(Writer wrt = new FileWriter("locations_to_visit.csv")) {
            wrt.write(String.format("id,longitude,latitude\n"));

            for(int i=0; i<nLocationsToVisit; ++i) {
                int lid = toVisit[i];
                Location l = locations[lid];
                wrt.write(String.format("%d,%f,%f\n", lid,l.getX(), l.getY()));
            }
        }
    }

    private static Location[]  generateCenters(int nCenters, Location[] locations) throws Exception {
        System.out.println("... generateCenters");

        Location[] centers = new Location[nCenters];

        int nLocations = locations.length;

        for (int i=0; i<nCenters; ++i) {
            int lid = rnd.nextInt(nLocations);
            centers[i] = locations[lid];
        }

        // Arrays.sort(toVisit);

        try(Writer wrt = new FileWriter("centers.csv")) {
            wrt.write(String.format("id,longitude,latitude\n"));

            for(int i=0; i<nCenters; ++i) {
                Location l = centers[i];
                wrt.write(String.format("%d,%f,%f\n", l.getId(),l.getX(), l.getY()));
            }
        }

        return centers;
    }

    private static void generateVehicles(int nVehicles, Location[] centers) throws Exception {
        System.out.println("... generateVehicles");

        int nCenters = centers.length;

        int[] vehicles = new int[nVehicles];
        for (int i=0; i<nVehicles; ++i)
            vehicles[i] = rnd.nextInt(nCenters);

        try(Writer wrt = new FileWriter("vehicles.csv")) {
            wrt.write(String.format("id,location_id,longitude,latitude\n"));

            for(int i=0; i<nVehicles; ++i) {
                int cid = vehicles[i];
                Location l = centers[cid];
                wrt.write(String.format("%d,%d,%f,%f\n", i, l.getId(),l.getX(),l.getY()));
            }
        }
    }

    // ----------------------------------------------------------------------

    private static int N_LOCATIONS = 5000;
    private static int N_LOCATIONS_TO_VISIT = 1000;
    private static int N_VEHICLES = 200;
    private static int N_CENTERS = 50;

    public static void main(String[] args) throws Exception {
        System.out.println("start ...");

        Shape UAE = loadUAE();
        Location[] locations = generateLocations(UAE, N_LOCATIONS);
        generateLocationsToVisit(N_LOCATIONS_TO_VISIT, locations);
        Location[] centers = generateCenters(N_CENTERS, locations);
        generateVehicles(N_VEHICLES, centers);

        System.out.println("done!");
    }
}
