package jext.geotools;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Simplify a boundary, represented by a LineString. It applies the following rules:
 * .
 *      1) remove points too near
 *      2) remove collinear points
 */
public class BoundarySimplifier {

    public static List<LineString> simplifyExpanded(List<BoundaryExpander> expandedBoundaries, double minSegmentLength, double maxCollinearAngle) {
        List<LineString> simplifiedBoundaries = new ArrayList<>();

        for (BoundaryExpander expandedBoundary : expandedBoundaries) {
            LineString simplified = simplify(expandedBoundary.expanded, minSegmentLength, maxCollinearAngle);
            expandedBoundary.expanded = simplified;

            simplifiedBoundaries.add(simplified);
        }

        return simplifiedBoundaries;
    }

    // public static List<BoundaryExpander> simplifyExpanded(List<BoundaryExpander> expandedBoundaries, double minSegmentLength, double maxCollinearAngle) {
    //     List<BoundaryExpander> simplifiedBoundaries = new ArrayList<>();
    //
    //     for (BoundaryExpander expandedBoundary : expandedBoundaries) {
    //         LineString simplified = simplify(expandedBoundary.expanded, minSegmentLength, maxCollinearAngle);
    //         expandedBoundary.expanded = simplified;
    //         simplifiedBoundaries.add(expandedBoundary);
    //     }
    //
    //     return simplifiedBoundaries;
    // }

    public static List<LineString> simplify(List<LineString> boundaries, double minSegmentLength, double maxCollinearAngle) {
        List<LineString> simplifiedBoundaries = new ArrayList<>();

        for (LineString boundary : boundaries) {
            LineString simplified = simplify(boundary, minSegmentLength, maxCollinearAngle);
            simplifiedBoundaries.add(simplified);
        }
        return simplifiedBoundaries;
    }


    public static LineString simplify(LineString boundary, double minSegmentLength, double maxCollinearAngle) {
        LineString simplified = removeSegmentsTooSmall(boundary, minSegmentLength);
        simplified = removeCollinearSegments(simplified, maxCollinearAngle);
        return simplified;
    }

    private static LineString removeSegmentsTooSmall(LineString boundary, double minSegmentLength) {
        if (minSegmentLength == 0)
            return boundary;

        Coordinate[] coords = boundary.getCoordinates();
        int l = coords.length-1;

        int i = 0;
        while (i < l) {
            double distance = coords[i].distance(coords[i+1]);
            if (distance < minSegmentLength) {
                l = ShapeUtils.drop(coords, i+1, l);
            }
            else {
                i++;
            }
        }
        coords[l] = coords[0];

        // VERY STRANGE
        if (l < 3)
            return boundary;
        else if (l != (coords.length-1))
            coords = Arrays.copyOf(coords, l+1);

        return boundary.getFactory().createLineString(coords);
    }

    private static LineString removeCollinearSegments(LineString boundary, double maxCollinearAngle) {
        if (maxCollinearAngle == 0)
            return boundary;

        Coordinate[] coords = boundary.getCoordinates();
        int l = coords.length-1;

        int i=0, j=l-1;
        while (i < l) {
            double angle = ShapeUtils.collinearAngle(coords[j], coords[i], coords[i+1]);
            if (angle <= maxCollinearAngle) {
                l = ShapeUtils.drop(coords, i, l);
            }
            else {
                j = i;
                i++;
            }
        }
        coords[l] = coords[0];

        // VERY STRANGE
        if (l < 3)
            return boundary;
        else if (l != coords.length-1)
            coords = Arrays.copyOf(coords, l+1);

        return boundary.getFactory().createLineString(coords);
    }
}
