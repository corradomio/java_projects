package jext.geotools;

import org.junit.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;

import static org.junit.Assert.*;

public class BoundaryExpanderIntersectionTest {
    private final GeometryFactory factory = new GeometryFactory();

    private LineString segment(double x1, double y1, double x2, double y2) {
        return factory.createLineString(new Coordinate[] {
                new Coordinate(x1, y1), new Coordinate(x2, y2)
        });
    }

    private void assertPoint(double x, double y, LineString first, LineString second) {
        Coordinate result = BoundaryExpander.intersection(first, second);
        assertNotNull(result);
        assertEquals(x, result.x, 1e-12);
        assertEquals(y, result.y, 1e-12);
    }

    @Test
    public void crossingAndTouchingSegments() {
        assertPoint(1, 1, segment(0, 0, 2, 2), segment(0, 2, 2, 0));
        assertPoint(2, 0, segment(0, 0, 2, 0), segment(2, 0, 2, 3));
    }

    @Test
    public void collinearSegmentsUseNearestEndpoints() {
        assertPoint(3, 0, segment(0, 0, 2, 0), segment(4, 0, 7, 0));
        assertPoint(0, 3, segment(0, 2, 0, 0), segment(0, 7, 0, 4));
        assertPoint(3.5, 0, segment(0, 0, 4, 0), segment(3, 0, 8, 0));
        assertPoint(2, 0, segment(0, 0, 2, 0), segment(2, 0, 4, 0));
    }

    @Test
    public void nonIntersectingSegmentsReturnNull() {
        assertNull(BoundaryExpander.intersection(segment(0, 0, 1, 0), segment(0, 1, 1, 1)));
        assertNull(BoundaryExpander.intersection(segment(0, 0, 1, 0), segment(2, -1, 2, 1)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsNull() {
        BoundaryExpander.intersection(null, segment(0, 0, 1, 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsEmptySegment() {
        BoundaryExpander.intersection(factory.createLineString(), segment(0, 0, 1, 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsMoreThanTwoCoordinates() {
        BoundaryExpander.intersection(segment(0, 0, 1, 1), factory.createLineString(new Coordinate[] {
                new Coordinate(0, 0), new Coordinate(1, 1), new Coordinate(2, 2)
        }));
    }
}
