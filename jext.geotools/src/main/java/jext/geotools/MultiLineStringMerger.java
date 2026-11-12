package jext.geotools;

import org.geotools.api.feature.simple.SimpleFeature;
import org.geotools.api.referencing.crs.CoordinateReferenceSystem;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiLineString;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.index.strtree.STRtree;
import org.locationtech.jts.util.Assert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/*
    GeometryFactory is used to create geometries.
    Why?
 */

public class MultiLineStringMerger {

    private static final String MULTI_LINE_STRING_TYPE = "MultiLineString";

    public static boolean isMultiLineString(SimpleFeature f) {
        CoordinateReferenceSystem crs = f.getType().getCoordinateReferenceSystem();
        String CRS = crs.toWKT();
        return isMultiLine((Geometry)f.getDefaultGeometry());
    }

    public static boolean isMultiLine(Geometry g) {
        return MULTI_LINE_STRING_TYPE.equals(g.getGeometryType());
    }

    private final double maxDistance;
    private final STRtree spatialIndex = new STRtree();
    private final Map<String, MultiLineString> multiLineStrings = new HashMap<>();

    /**
     * MultiLine merger
     * WARNING: here the distance is specified in meters, because it is the most obvious
     * measure unit to use. BUT to know which is the measure unit of the points in the
     * geometric object, it is necessary to retrieve the 'CoordinateReferenceSystem'
     * from the GeoTools 'Feature'. In our case, this could be:
     * ----------------------------------------------------------------------
     * PROJCS["WGS_1984_Transverse_Mercator",
     *   GEOGCS["GCS_WGS_1984",
     *     DATUM["D_WGS_1984",
     *       SPHEROID["WGS_1984", 6378137.0, 298.257223563]],
     *     PRIMEM["Greenwich", 0.0],
     *     UNIT["degree", 0.017453292519943295],
     *     AXIS["Longitude", EAST],
     *     AXIS["Latitude", NORTH]],
     *   PROJECTION["Transverse_Mercator"],
     *   PARAMETER["central_meridian", 55.333333333333336],
     *   PARAMETER["latitude_of_origin", 0.0],
     *   PARAMETER["scale_factor", 1.0],
     *   PARAMETER["false_easting", 500000.0],
     *   PARAMETER["false_northing", 0.0],
     *   UNIT["m", 1.0],
     *   AXIS["x", EAST],
     *   AXIS["y", NORTH]]
     * ----------------------------------------------------------------------
     * where it is explicitly specified the unit of measure is meters (UNIT["m", 1.0])
     *
     * @param maxDistance in meters
     */
    public MultiLineStringMerger(double maxDistance) {
        this.maxDistance = maxDistance;
    }

    public void add(String lineId, SimpleFeature f) {
        // if (!isMultiLineString(f))
        //     throw new IllegalArgumentException(f.getID() + " is not MultiLineString");

        Geometry g = (Geometry) f.getDefaultGeometry();
        add(lineId, (MultiLineString) g);
    }

    /**
     * Insert the FIRST AND LAST points into the spatialIndex,
     * but also register mls into the dictionary
     * @param lineId id of the multiline
     * @param mls multiline to insert
     */
    public void add(String lineId, MultiLineString mls) {
        Point p;
        Envelope env = mls.getEnvelopeInternal();
        Coordinate[] coords = mls.getCoordinates();

        int l = coords.length - 1;
        // line of a single point???
        if (l == 0) return;

        multiLineStrings.put(lineId, mls);
        GeometryFactory gf = mls.getFactory();

        p = gf.createPoint(coords[0]);
        p.setUserData(lineId);
        spatialIndex.insert(env, p);

        p = gf.createPoint(coords[l]);
        p.setUserData(lineId);
        spatialIndex.insert(env, p);
    }

    public void addAll(List<String> ids, List<MultiLineString> mlss) {
        Assert.isTrue(ids.size() == mlss.size());
        Iterator<MultiLineString> mlsit = mlss.iterator();
        Iterator<String> idit = ids.iterator();

        while(mlsit.hasNext()) {
            String lineId = idit.next();
            MultiLineString mls = mlsit.next();
            add(lineId, mls);
        }
    }

    /**
     * Helper holder class for the point and its calculated distance.
     */
    private static class PointDistance {
        private final Point point;
        private final double distance;

        public PointDistance(Point point, double distance) {
            this.point = point;
            this.distance = distance;
        }

        public Point getPoint() { return point; }
        public double getDistance() { return distance; }
    }

    private static class PointId {

        public static PointId of(String lineId, boolean endPoint) {
            return new PointId(lineId, endPoint);
        }

        public final String lineId;
        public final boolean end;
        public PointId(String lineId, boolean endPoint) {
            this.lineId = lineId;
            this.end = endPoint;
        }

        @Override
        public boolean equals(Object o) {
            PointId id = (PointId) o;
            return lineId.equals(id.lineId) && end == id.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(lineId, end);
        }
    }

    public void mergeLines() {
        boolean merged = true;
        while (merged) {
            merged = false;
            for (String lineId : multiLineStrings.keySet()) {
                MultiLineString mls = multiLineStrings.get(lineId);

                // lines already merged are replaced by null
                if (mls == null) continue;

                GeometryFactory gf = mls.getFactory();
                Coordinate[] coords = mls.getCoordinates();
                int l = coords.length - 1;
                // line of a single point???
                if (l == 0) continue;

                // merge with first point
                //merge with last point
                mergeWithPoint(PointId.of(lineId, false), coords[0], gf);
                mergeWithPoint(PointId.of(lineId, true), coords[l], gf);
            }
        }

    }

    private void mergeWithPoint(PointId pointId, Coordinate c, GeometryFactory gf) {
        Envelope searchEnv = new Envelope(c);
        searchEnv.expandBy(maxDistance);

        Point targetP = gf.createPoint(c);

        @SuppressWarnings("unchecked")
        List<Point> candidates = spatialIndex.query(searchEnv);

        // 4. Perform precise Euclidean distance filtering and sort the results
        List<PointDistance> results = new ArrayList<>();
        for (Point candidate : candidates) {
            // Optional: skip if the candidate is the exact same reference point P itself
            if (candidate.equalsExact(targetP)) {
                continue;
            }

            double distance = targetP.distance(candidate);
            if (distance <= maxDistance) {
                results.add(new PointDistance(candidate, distance));
            }
        }

        if (results.isEmpty())
            return;

        // Sort ascending by distance (nearest points first)
        System.out.printf("%s->%d\n", pointId, results.size());
        results.sort(Comparator.comparingDouble(PointDistance::getDistance));
    }

}
