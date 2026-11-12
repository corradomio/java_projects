package jext.geotools;
import org.locationtech.jts.algorithm.hull.ConcaveHullOfPolygons;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ClusterBoundaryGenerator {

    /**
     * Create the boundary around the list of polygons representing a polygon cluster
     *
     * @param clusters list of clusters
     * @param bridgeTolerance maximum length of the segment to use when two polygons
     *                        members of the same cluster are not at 0 distance.
     *                        In meters
     * @return list of closed linestring representing the boundaries
     */
    public static List<LineString> createBoundaries(List<List<Polygon>> clusters, double bridgeTolerance) {
        List<LineString> boundaries = new ArrayList<>();
        for (List<Polygon> cluster : clusters) {
            Optional<LineString> boundary = createBoundaryViaBuffer(cluster, bridgeTolerance);
            boundary = cleanupBoundary(boundary);
            if (!boundary.isPresent())
                continue;

            boundaries.add(boundary.get());
        }
        return boundaries;
    }

    private static Optional<LineString> cleanupBoundary(Optional<LineString> boundary) {
        if (boundary.isEmpty()) return boundary;

        Coordinate[] coords = boundary.get().getCoordinates();
        int l = coords.length-1;
        int i = 0;

        while (i < l) {
            if (ShapeUtils.length(coords[i], coords[i+1]) < 0.5) {
                l = ShapeUtils.drop(coords, i+1, l);
            }
            else {
                i++;
            }
        }

        if (l < 3)
            return Optional.empty();

        if (l != (coords.length-1))
            coords = Arrays.copyOf(coords, l+1);
        coords[l] = coords[0];

        LineString ls = boundary.get().getFactory().createLineString(coords);
        return Optional.of(ls);
    }

    /**
     * Method 1: Union & Buffer Closing method.
     * Ideal if you want a smooth outline that bridges small internal gaps between polygons.
     *
     * @param cluster list of polgons representing a cluster
     * @param bridgeTolerance maximum length of the segment to use when two polygons
     *                        members of the same cluster are not at 0 distance.
     *                        In meters
     * @return closed linestring representing the boundary
     */
    public static Optional<LineString> createBoundaryViaBuffer(List<Polygon> cluster, double bridgeTolerance) {
        GeometryFactory gf = new GeometryFactory();
        if (cluster.isEmpty()) return Optional.empty();

        // Combine all cluster polygons into a GeometryCollection / MultiPolygon
        Geometry collection = gf.buildGeometry(cluster);
        Geometry union = collection.union();

        // Morphological closing: expand slightly to close gaps, then shrink back
        Geometry closed = union.buffer(bridgeTolerance).buffer(-bridgeTolerance);

        // Extract the exterior boundary as a LineString
        if (closed instanceof Polygon) {
            return Optional.of(((Polygon) closed).getExteriorRing());
        } else if (closed instanceof MultiPolygon) {
            // If it creates multiple parts, take the largest or return the boundary
            Geometry largest = getLargestPolygon((MultiPolygon) closed);
            return Optional.of(((Polygon) largest).getExteriorRing());
        }

        return Optional.empty();
    }

    /**
     * Method 2: JTS Concave Hull of Polygons.
     * Ideal for wrapping tightly around the outer perimeter of the cluster.
     */
    public static Optional<LineString> createConcaveHullBoundary(List<Polygon> cluster, double lengthRatio) {
        GeometryFactory gf = new GeometryFactory();
        GeometryCollection collection = (GeometryCollection) gf.buildGeometry(cluster);

        // Computes a concave hull enclosing the polygon set
        Geometry hull = ConcaveHullOfPolygons.concaveHullByLengthRatio(collection, lengthRatio);

        if (hull instanceof Polygon) {
            return Optional.of(((Polygon) hull).getExteriorRing());
        }
        return Optional.empty();
    }

    private static Geometry getLargestPolygon(MultiPolygon mp) {
        Geometry largest = mp.getGeometryN(0);
        for (int i = 1; i < mp.getNumGeometries(); i++) {
            Geometry g = mp.getGeometryN(i);
            if (g.getArea() > largest.getArea()) {
                largest = g;
            }
        }
        return largest;
    }
}
