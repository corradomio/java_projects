package jext.geotools;

import jext.util.Assert;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineString;

import java.util.ArrayList;
import java.util.List;

public class OutwardDirections {

    public final LineString boundary;
    public final LineString[] allDrections;
    public final List<LineString> directions = new ArrayList<>();

    OutwardDirections(LineString boundary) {
        this.boundary = boundary;
        this.allDrections = new  LineString[boundary.getNumPoints()];
    }

    void done() {
        for (int i=0; i<allDrections.length-1; i++) {
            directions.add(allDrections[i]);
        }
    }

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    // OutwardDirection(LineString boundary, int segmentIndex, LineString direction) {
    //     this.boundary = boundary;
    //     this.segmentIndex = segmentIndex;
    //     this.direction = direction;
    // }

    // ----------------------------------------------------------------------
    // Factory methods
    // ----------------------------------------------------------------------

    /// length (in meters) of the segment used to represent the direction
    public static double LENGTH = 2.0;

    public static List<OutwardDirections> outwardDirections(List<LineString> boundaries) {
        List<OutwardDirections> outwardDirections = new ArrayList<>();
        for (LineString boundary : boundaries) {
            outwardDirections.add(outwardDirections(boundary));
        }
        return outwardDirections;
    }

    /**
     * Compute the outward direction for each segment in the boundary.
     * The outward direction is computed on the segment's center point
     *
     * @param boundary boundary to analyze
     * @return list of outward directions, included the boundary and the segment index
     */
    public static OutwardDirections outwardDirections(LineString boundary) {
        Assert.verify(ShapeUtils.isClosed(boundary),  "LineString not closed");

        Coordinate[] coords = boundary.getCoordinates();
        OutwardDirections outwardDirs = new  OutwardDirections(boundary);

        int n =  coords.length;
        for (int i = 0, j=1; j < n; i++, j++){
            Coordinate ci = coords[i];
            Coordinate cj = coords[j];

            Coordinate from = ShapeUtils.center(ci, cj);
            Coordinate outward = ShapeUtils.outward(ci, cj);
            Coordinate to = ShapeUtils.lin(from, LENGTH, outward);

            LineString outwardDir = boundary.getFactory().createLineString(new Coordinate[]{from, to});

            outwardDirs.allDrections[i] =  outwardDir;
        }
        // fill the last element
        outwardDirs.allDrections[n-1] = outwardDirs.allDrections[0];
        outwardDirs.done();

        return outwardDirs;
    }

}
