package jext.geotools;

import org.locationtech.jts.algorithm.Intersection;
import org.locationtech.jts.algorithm.Orientation;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.index.strtree.STRtree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Finds boundary intersections with a forward, infinite ray in the XY plane.
 * Construct once to reuse a segment-level STRtree for many ray queries, or use
 * the static method for a single query without building an index.
 */
public final class RayBoundaryIntersector {
    /**
     * Intersection point and smaller unsigned XY angle to the hit segment.
     * The angle is in radians, between 0 and PI/2, independent of endpoint order.
     * Zero-length segments have angle NaN. Equal-distance hits use the first
     * encountered segment; index traversal order may differ from list order.
     */
    public static class IntersectionResult {

        private final LineString segment;
        private final double angleRadians;
        private Point point;

        public IntersectionResult(LineString segment, double angleRadians) {
            this.segment = segment;
            this.angleRadians = angleRadians;
        }

        public LineString segment() {
            return segment;
        }

        public Point point() {
            if (point == null) {
                point = new Point(segment.getCoordinateN(1), segment.getPrecisionModel(), segment.getSRID());
            }
            return point;
        }

        public double angleRadians() {
            return angleRadians;
        }

        public double angleDegrees() {
            return Math.toDegrees(angleRadians);
        }

        public double length() {
            Coordinate[] coords = segment.getCoordinates();
            double dx = coords[0].x - coords[1].x;
            double dy = coords[0].y - coords[1].y;
            return Math.sqrt(dx*dx + dy*dy);
        }
    }

    private final STRtree index = new STRtree();
    private final Envelope bounds = new Envelope();

    /**
     * Builds an index over independent copies of all boundary segments.
     * Later changes to the input geometries do not affect this index; construct
     * a new intersector when boundaries change. Empty boundaries are ignored.
     */
    public RayBoundaryIntersector(List<? extends LineString> boundaries) {
        // Objects.requireNonNull(boundaries, "boundaries");
        for (LineString boundary : boundaries) {
            // Objects.requireNonNull(boundary, "boundary");
            Coordinate[] coordinates = boundary.getCoordinates();
            // for (Coordinate coordinate : coordinates) requireFinite(coordinate);
            for (int i = 1; i < coordinates.length; i++) {
                // Coordinate a = new Coordinate(coordinates[i - 1].x, coordinates[i - 1].y);
                // Coordinate b = new Coordinate(coordinates[i].x, coordinates[i].y);
                Coordinate a = coordinates[i - 1];
                Coordinate b = coordinates[i];
                Envelope envelope = new Envelope(a, b);
                LineString segment = boundary.getFactory().createLineString(new Coordinate[] {a, b});
                index.insert(envelope, segment);
                bounds.expandToInclude(envelope);
            }
        }
        index.build();
    }

    /**
     * Queries the reusable index, then computes exact ray/segment intersections.
     * Has the same result and origin/overlap semantics as the static method.
     *
     * <p>The query envelope conservatively contains the forward ray within the
     * dataset bounds: an axis-aligned ray gives a zero-width strip, an oblique
     * ray gives a quadrant clipped to the bounds. A second envelope-side test
     * rejects boxes not crossed by the ray's supporting line. No artificial
     * ray length or numerically computed far endpoint is required.</p>
     *
     * <p>Indexing helps most for repeated queries with spatially selective
     * envelopes. Diagonal rays or heavily overlapping boxes can still visit
     * many candidates; worst-case query work remains linear.</p>
     */
    public Optional<IntersectionResult> nearestIntersection(LineString direction) {
        // validateDirection(direction);
        if (bounds.isNull()) return Optional.empty();
        Coordinate origin = direction.getCoordinateN(0);
        Coordinate toward = direction.getCoordinateN(1);
        double dx = toward.x - origin.x;
        double dy = toward.y - origin.y;
        double minX = dx >= 0 ? Math.max(bounds.getMinX(), origin.x) : bounds.getMinX();
        double maxX = dx <= 0 ? Math.min(bounds.getMaxX(), origin.x) : bounds.getMaxX();
        double minY = dy >= 0 ? Math.max(bounds.getMinY(), origin.y) : bounds.getMinY();
        double maxY = dy <= 0 ? Math.min(bounds.getMaxY(), origin.y) : bounds.getMaxY();
        if (minX > maxX || minY > maxY) return Optional.empty();

        List<LineString> candidates = new ArrayList<>();
        index.query(new Envelope(minX, maxX, minY, maxY), item -> {
            LineString segment = (LineString) item;
            if (crossesEnvelope(origin, toward, segment.getEnvelopeInternal()))
                candidates.add(segment);
        });
        return nearestIntersection(candidates, direction);
    }

    private static boolean crossesEnvelope(Coordinate origin, Coordinate toward, Envelope envelope) {
        Coordinate[] corners = {
                new Coordinate(envelope.getMinX(), envelope.getMinY()),
                new Coordinate(envelope.getMinX(), envelope.getMaxY()),
                new Coordinate(envelope.getMaxX(), envelope.getMinY()),
                new Coordinate(envelope.getMaxX(), envelope.getMaxY())
        };
        int side = Orientation.index(origin, toward, corners[0]);
        if (side == 0) return true;
        for (int i = 1; i < corners.length; i++)
            if (Orientation.index(origin, toward, corners[i]) != side) return true;
        return false;
    }

    /**
     * Returns the nearest boundary point and its segment angle on the ray starting at direction[0]
     * and pointing toward direction[1]. Later direction coordinates are ignored.
     * The ray extends beyond direction[1]; no arbitrary maximum length is used.
     *
     * <p>Candidates less than 0.1 coordinate units from the origin are excluded.
     * For collinear overlaps containing the origin, only the origin is considered
     * and thus excluded; otherwise forward endpoints are considered.
     * Angles follow the conventions in {@link IntersectionResult}.
     * Empty boundaries are ignored; no intersection yields Optional.empty().
     * Inputs are not modified. The result uses the direction's geometry factory
     * and SRID; Z and M are not used or interpolated.</p>
     *
     * <p>All inputs must use the same planar coordinate reference system.
     * Reproject geographic longitude/latitude data with GeoTools before using
     * this method when distances/directions must have metric meaning.</p>
     *
     * @throws IllegalArgumentException if the first two coordinates are missing,
     *         equal in XY, nonfinite, or have an unrepresentable difference;
     *         or a boundary contains nonfinite XY coordinates
     * @throws NullPointerException if an input or a boundary is null
     */
    public static Optional<IntersectionResult> nearestIntersection(List<? extends LineString> boundaries,
                                                       LineString direction) {
        // Objects.requireNonNull(boundaries, "boundaries");
        // validateDirection(direction);
        Coordinate origin = direction.getCoordinateN(0);
        Coordinate toward = direction.getCoordinateN(1);
        double dx = toward.x - origin.x;
        double dy = toward.y - origin.y;

        // The dominant axis determines forward/backward without a dot product
        // that could overflow or underflow for very large/small vectors.
        boolean useX = Math.abs(dx) >= Math.abs(dy);
        boolean increasing = (useX ? dx : dy) > 0;
        double start = useX ? origin.x : origin.y;
        Coordinate nearest = null;
        Coordinate nearestOrigin = null;
        double nearestAngle = Double.NaN;
        double nearestDistance = Double.POSITIVE_INFINITY;

        for (LineString boundary : boundaries) {
            // Objects.requireNonNull(boundary, "boundary");
            Coordinate[] coordinates = boundary.getCoordinates();
            // for (Coordinate coordinate : coordinates) requireFinite(coordinate);
            for (int i = 1; i < coordinates.length; i++) {
                Coordinate a = coordinates[i - 1];
                Coordinate b = coordinates[i];
                int sideA = Orientation.index(origin, toward, a);
                int sideB = Orientation.index(origin, toward, b);
                Coordinate[] candidates;
                if (sideA == 0 && sideB == 0) {
                    // Collinear: both overlap endpoints matter, regardless of
                    // their order. An overlap containing the origin starts there.
                    candidates = new Envelope(a, b).contains(origin)
                            ? new Coordinate[] {origin} : new Coordinate[] {a, b};
                } else if (sideA == sideB) {
                    continue;
                } else {
                    Coordinate hit = Intersection.lineSegment(origin, toward, a, b);
                    if (hit == null) continue;
                    candidates = new Coordinate[] {hit};
                }
                for (Coordinate candidate : candidates) {
                    double position = useX ? candidate.x : candidate.y;
                    if (increasing ? position < start : position > start) continue;
                    double distance = Math.hypot(candidate.x - origin.x, candidate.y - origin.y);

                    // Minimum distance in coordinate units (10 cm for metres).
                    if (distance < 0.1) continue;
                    if (nearest == null || distance < nearestDistance) {
                        nearestOrigin = origin;
                        nearest = new Coordinate(candidate.x, candidate.y);
                        nearestDistance = distance;
                        nearestAngle = segmentAngle(dx, dy, a, b);
                    }
                }
            }
        }
        if (nearest == null) return Optional.empty();

        LineString intersection = direction.getFactory().createLineString(new Coordinate[]{nearestOrigin, nearest});
        return Optional.of(new IntersectionResult(intersection, nearestAngle));
    }

    private static double segmentAngle(double dx, double dy, Coordinate a, Coordinate b) {
        if (a.equals2D(b)) return Double.NaN;
        double sx = b.x - a.x;
        double sy = b.y - a.y;
        // Avoid overflow when subtracting large finite endpoint coordinates.
        if (!Double.isFinite(sx) || !Double.isFinite(sy)) {
            sx = b.x / 2 - a.x / 2;
            sy = b.y / 2 - a.y / 2;
        }
        double rayScale = Math.max(Math.abs(dx), Math.abs(dy));
        double segmentScale = Math.max(Math.abs(sx), Math.abs(sy));
        dx /= rayScale;
        dy /= rayScale;
        sx /= segmentScale;
        sy /= segmentScale;
        return Math.atan2(Math.abs(dx * sy - dy * sx), Math.abs(dx * sx + dy * sy));
    }

}
