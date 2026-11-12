To efficiently find the nearest points within a maximum distance using GeoTools and JTS, the best practice is to use **`STRtree`** (a spatial index provided by JTS).

Querying points directly from a spatial index avoids an $O(N)$ full-scan complexity, making it extremely fast even for large datasets. Because an index query uses a square bounding box (Envelope), you follow it up with an exact distance calculation and sort the results by proximity.

### Java Code Implementation

```java
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.index.strtree.STRtree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NearestPointsFinder {

    /**
     * Finds all points within a maximum distance from a target point P, 
     * ordered from nearest to furthest.
     *
     * @param pointsList  The list of candidate JTS Points
     * @param targetP     The reference point P
     * @param maxDistance The maximum distance threshold
     * @return A sorted list of custom wrapper objects containing the point and its distance
     */
    public static List<PointDistance> findNearbyPoints(List<Point> pointsList, Point targetP, double maxDistance) {
        
        // 1. Build and populate the STRtree spatial index
        STRtree spatialIndex = new STRtree();
        for (Point pt : pointsList) {
            spatialIndex.insert(pt.getEnvelopeInternal(), pt);
        }
        
        // 2. Create a square search envelope around P expanded by the max distance
        Envelope searchEnv = new Envelope(targetP.getCoordinate());
        searchEnv.expandBy(maxDistance);
        
        // 3. Query the index (returns candidates whose bounding boxes intersect the envelope)
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
        
        // Sort ascending by distance (nearest points first)
        results.sort(Comparator.comparingDouble(PointDistance::getDistance));
        
        return results;
    }

    /**
     * Helper holder class for the point and its calculated distance.
     */
    public static class PointDistance {
        private final Point point;
        private final double distance;

        public PointDistance(Point point, double distance) {
            this.point = point;
            this.distance = distance;
        }

        public Point getPoint() { return point; }
        public double getDistance() { return distance; }
    }

    // --- Example Usage ---
    public static void main(String[] args) {
        GeometryFactory geometryFactory = new GeometryFactory();
        
        // Define target point P
        Point targetP = geometryFactory.createPoint(new Coordinate(0.0, 0.0));
        
        // Create a list of sample points
        List<Point> points = new ArrayList<>();
        points.add(geometryFactory.createPoint(new Coordinate(1.0, 1.0))); // dist ≈ 1.41
        points.add(geometryFactory.createPoint(new Coordinate(5.0, 5.0))); // dist ≈ 7.07 (too far)
        points.add(geometryFactory.createPoint(new Coordinate(0.5, 0.5))); // dist ≈ 0.70
        points.add(geometryFactory.createPoint(new Coordinate(2.0, 0.0))); // dist = 2.0
        
        double maxDistance = 3.0;
        
        List<PointDistance> nearby = findNearbyPoints(points, targetP, maxDistance);
        
        System.out.println("Points within " + maxDistance + " units of P(0,0):");
        for (PointDistance pd : nearby) {
            System.out.printf("Coordinate: (%f, %f) -> Distance: %.2f\n", 
                    pd.getPoint().getX(), pd.getPoint().getY(), pd.getDistance());
        }
    }
}

```

---

### How It Works

1. **Spatial Indexing (`STRtree`)**: Inserting all points into an `STRtree` constructs an optimized R-tree. This eliminates the need to loop through every single point manually during the search phase.
2. **Bounding Box Pre-filtering (`searchEnv.expandBy(...)`)**: The search envelope acts as a rough filter. It extracts only the points inside a square radius around point $P$, discarding distant nodes instantly.
3. **Exact Distance Check & Sorting**: Because the bounding box query returns a square region, points sitting in the corners might technically fall outside your radial `maxDistance`. A quick `.distance()` check ensures geometric accuracy, followed by sorting to prioritize the closest points.
