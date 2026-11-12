package jext.geotools;

import org.junit.Test;
import org.locationtech.jts.geom.Coordinate;

import static org.junit.Assert.assertEquals;

public class ShapeUtilsAngleTest {
    @Test
    public void rightAngleAtTranslatedVertex() {
        assertEquals(Math.PI / 2, ShapeUtils.angle(new Coordinate(3, 2),
                new Coordinate(1, 2), new Coordinate(1, 5)), 1e-12);
    }

    @Test
    public void straightAndFoldedSegments() {
        Coordinate middle = new Coordinate(0, 0);
        assertEquals(Math.PI, ShapeUtils.angle(new Coordinate(-1, 0), middle,
                new Coordinate(2, 0)), 1e-12);
        assertEquals(0, ShapeUtils.angle(new Coordinate(1, 0), middle,
                new Coordinate(2, 0)), 1e-12);
    }

    @Test
    public void smallerAngleAcrossAtan2BranchIsSymmetric() {
        Coordinate first = new Coordinate(-1, 1);
        Coordinate middle = new Coordinate(0, 0);
        Coordinate end = new Coordinate(-1, -1);
        assertEquals(Math.PI / 2, ShapeUtils.angle(first, middle, end), 1e-12);
        assertEquals(Math.PI / 2, ShapeUtils.angle(end, middle, first), 1e-12);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsZeroLengthFirstSegment() {
        ShapeUtils.angle(new Coordinate(1, 1), new Coordinate(1, 1), new Coordinate(2, 3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsZeroLengthSecondSegment() {
        ShapeUtils.angle(new Coordinate(2, 3), new Coordinate(1, 1), new Coordinate(1, 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsNullPoint() {
        ShapeUtils.angle(null, new Coordinate(0, 0), new Coordinate(1, 1));
    }
}
