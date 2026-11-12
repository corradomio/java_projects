package jext.geotools;

import org.junit.Test;
import org.locationtech.jts.geom.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import static org.junit.Assert.*;

public class RayBoundaryIntersectorTest {
    private final GeometryFactory factory = new GeometryFactory(new PrecisionModel(), 3857);

    private LineString line(double... xy) {
        Coordinate[] coordinates = new Coordinate[xy.length / 2];
        for (int i = 0; i < coordinates.length; i++)
            coordinates[i] = new Coordinate(xy[2 * i], xy[2 * i + 1]);
        return factory.createLineString(coordinates);
    }

    private void assertHit(double x, double y, LineString ray, LineString... boundaries) {
        Point point = RayBoundaryIntersector.nearestIntersection(List.of(boundaries), ray).orElseThrow().point();
        assertEquals(x, point.getX(), 1e-9);
        assertEquals(y, point.getY(), 1e-9);
        assertEquals(3857, point.getSRID());
        Point indexed = new RayBoundaryIntersector(List.of(boundaries))
                .nearestIntersection(ray).orElseThrow().point();
        assertEquals(point.getX(), indexed.getX(), 1e-9);
        assertEquals(point.getY(), indexed.getY(), 1e-9);
        assertEquals(point.getSRID(), indexed.getSRID());
    }

    @Test public void findsNearestBeyondDirectionEndpointAndIgnoresBehind() {
        assertHit(3, 0, line(0, 0, 1, 0),
                line(10, -1, 10, 1), line(-1, -1, -1, 1), line(3, -2, 3, 2));
    }

    @Test public void handlesVerticalNegativeAndDiagonalDirections() {
        assertHit(1, -5, line(1, 2, 1, 1), line(-2, -5, 4, -5));
        assertHit(-5, -5, line(0, 0, -1, -1), line(-5, -8, -5, 0));
        assertHit(4, 4, line(0, 0, 1, 1, -10, 0), line(4, 4, 5, 3));
    }

    @Test public void handlesOverlapsAndOriginHits() {
        assertHit(2, 0, line(0, 0, 1, 0), line(5, 0, 2, 0));
        LineString ray = line(0, 0, 1, 0);
        for (LineString boundary : List.of(line(-2, 0, 4, 0), line(0, -1, 0, 1), line(0.05, -1, 0.05, 1))) {
            assertTrue(RayBoundaryIntersector.nearestIntersection(List.of(boundary), ray).isEmpty());
            assertTrue(new RayBoundaryIntersector(List.of(boundary)).nearestIntersection(ray).isEmpty());
        }
        assertHit(0.1, 0, ray, line(0.1, -1, 0.1, 1));
    }

    @Test public void handlesPolylineAndRepeatedVertices() {
        assertHit(3, 0, line(0, 0, 1, 0), line(7, -1, 7, 1, 3, 1, 3, -1));
        assertHit(2, 0, line(0, 0, 1, 0), line(2, 0, 2, 0));
    }

    private void assertAngle(double degrees, LineString ray, LineString... boundaries) {
        var scanned = RayBoundaryIntersector.nearestIntersection(List.of(boundaries), ray).orElseThrow();
        var indexed = new RayBoundaryIntersector(List.of(boundaries)).nearestIntersection(ray).orElseThrow();
        assertEquals(Math.toRadians(degrees), scanned.angleRadians(), 1e-12);
        assertEquals(degrees, scanned.angleDegrees(), 1e-10);
        assertEquals(scanned.angleRadians(), indexed.angleRadians(), 1e-12);
    }

    @Test public void returnsAngleOfNearestIntersectedSegment() {
        LineString ray = line(0, 0, 1, 0);
        assertAngle(90, ray, line(3, -1, 3, 1));
        assertAngle(45, ray, line(2, -1, 4, 1), line(5, -1, 5, 1));
        assertAngle(45, ray, line(4, 1, 2, -1));
        assertAngle(0, ray, line(5, 0, 2, 0));
        assertAngle(45, line(0, 0, -1, -1), line(-5, -8, -5, 0));
        assertAngle(90, ray, line(7, -1, 7, 1, 3, 1, 3, -1));
        assertAngle(Double.NaN, ray, line(2, 0, 2, 0));
        assertAngle(90, ray, line(3, -1e308, 3, 1e308));
    }

    @Test public void returnsEmptyWhenNoForwardHit() {
        LineString ray = line(0, 0, 1, 0);
        assertTrue(RayBoundaryIntersector.nearestIntersection(List.of(), ray).isEmpty());
        assertTrue(RayBoundaryIntersector.nearestIntersection(
                List.of(line(), line(-3, 0, -2, 0), line(2, 1, 4, 1)), ray).isEmpty());
    }

    @Test public void rejectsInvalidInputs() {
        assertThrows(IllegalArgumentException.class,
                () -> RayBoundaryIntersector.nearestIntersection(List.of(), line()));
        assertThrows(IllegalArgumentException.class,
                () -> RayBoundaryIntersector.nearestIntersection(List.of(), line(1, 1, 1, 1)));
        assertThrows(IllegalArgumentException.class,
                () -> RayBoundaryIntersector.nearestIntersection(List.of(), line(0, 0, Double.NaN, 1)));
        assertThrows(IllegalArgumentException.class,
                () -> RayBoundaryIntersector.nearestIntersection(
                        List.of(line(1, 0, Double.POSITIVE_INFINITY, 2)), line(0, 0, 1, 0)));
    }

    @Test public void indexMatchesScanAcrossManyDirections() {
        Random random = new Random(42);
        List<LineString> boundaries = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            double x = random.nextDouble() * 200 - 100;
            double y = random.nextDouble() * 200 - 100;
            boundaries.add(line(x, y, x + random.nextDouble() * 10, y + random.nextDouble() * 10));
        }
        RayBoundaryIntersector indexed = new RayBoundaryIntersector(boundaries);
        for (int i = 0; i < 300; i++) {
            double x = random.nextDouble() * 400 - 200;
            double y = random.nextDouble() * 400 - 200;
            double angle = random.nextDouble() * 2 * Math.PI;
            LineString ray = line(x, y, x + Math.cos(angle), y + Math.sin(angle));
            var expected = RayBoundaryIntersector.nearestIntersection(boundaries, ray);
            var actual = indexed.nearestIntersection(ray);
            assertEquals(expected.isPresent(), actual.isPresent());
            if (expected.isPresent()) {
                assertEquals(expected.get().angleRadians(), actual.orElseThrow().angleRadians(), 1e-12);
                assertEquals(expected.get().point().getX(), actual.orElseThrow().point().getX(), 1e-9);
                assertEquals(expected.get().point().getY(), actual.orElseThrow().point().getY(), 1e-9);
            }
        }
    }

    @Test public void indexHandlesOutsideOriginsEmptyDataAndInputMutation() {
        assertTrue(new RayBoundaryIntersector(List.of()).nearestIntersection(line(0, 0, 1, 0)).isEmpty());
        LineString boundary = line(5, -1, 5, 1);
        RayBoundaryIntersector indexed = new RayBoundaryIntersector(List.of(boundary));
        assertTrue(indexed.nearestIntersection(line(10, 0, 11, 0)).isEmpty());
        assertTrue(indexed.nearestIntersection(line(0, 2, 1, 2)).isEmpty());
        assertTrue(indexed.nearestIntersection(line(0, 0, 0, 1)).isEmpty());
        assertEquals(5, indexed.nearestIntersection(line(10, 0, 9, 0)).orElseThrow().point().getX(), 0);
        boundary.getCoordinateSequence().setOrdinate(0, 0, 50);
        boundary.getCoordinateSequence().setOrdinate(1, 0, 50);
        boundary.geometryChanged();
        assertEquals(5, indexed.nearestIntersection(line(0, 0, 1, 0)).orElseThrow().point().getX(), 0);
        assertThrows(IllegalArgumentException.class, () -> indexed.nearestIntersection(line(0, 0, 0, 0)));
    }
}
