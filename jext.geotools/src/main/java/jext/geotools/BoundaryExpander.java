package jext.geotools;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Expand a boundary, represented by a single closed LineString, by a constant offset
 * or by a different offset for each boundary vertex.
 */
public class BoundaryExpander {

    public final LineString boundary;
    public final LineString expanded;
    public final BoundaryOffsets offsets;

    BoundaryExpander(BoundaryOffsets boundaryOffsets, LineString expandedBoundary) {
        this.boundary = boundaryOffsets.boundary;
        this.offsets = boundaryOffsets;
        this.expanded = expandedBoundary;
    }

    // ----------------------------------------------------------------------
    // boundaryExpand
    // ----------------------------------------------------------------------

    /**
     * Expand all boundaries of an offset dependent on the distance of each boundary segment
     * with the nearest segment belonging to another boundary, in front of the segment itself
     * `boundaryIntersections` contain the computed boundary intersections for some selected
     * boundary segments
     * Note: not all segments
     *
     * @param boundariesOffsets offset map to use
     * @param maxCollinearAngle in degree
     * @return
     */
    public static List<BoundaryExpander> boundaryExpand(List<BoundaryOffsets> boundariesOffsets, double maxCollinearAngle) {
        List<BoundaryExpander> expansions = new ArrayList<>();

        maxCollinearAngle = maxCollinearAngle*Math.PI/180;

        for (BoundaryOffsets boundaryOffsets : boundariesOffsets) {
            // LineString expandedBoundary = expandUsingIntersection(boundaryOffsets);
            // LineString expandedBoundary = expandBoundaryUsingSimpleRules(boundaryOffsets);
            LineString expandedBoundary = expandBoundaryPlain(boundaryOffsets, maxCollinearAngle);
            // expanded.add(eb);
            expansions.add(new BoundaryExpander(
                boundaryOffsets,
                expandedBoundary
            ));
        }

        return expansions;
    }

    // ----------------------------------------------------------------------
    // Boundary utils
    // ----------------------------------------------------------------------
    // A 'boundary' is a LineString where first and last points have the same
    // coordinates

    // a) for each boundary segment it is computed:
    //    1) the outward direction
    //    2) the coordinates of the segment when moved along the outward direction
    //       of the specified offset
    // b) for each pair of adjacent segments, the intersection point
    // c) based on the intersection points, the new boundary
    private static LineString expandUsingIntersection(BoundaryOffsets boundaryOffsets) {
        LineString boundary = boundaryOffsets.boundary;
        double[] offsets = boundaryOffsets.offsets;

        Coordinate[] coords = boundary.getCoordinates();
        Coordinate[] movedOrigins = new Coordinate[coords.length];
        Coordinate[] movedTargets = new Coordinate[coords.length];
        Coordinate[] intersections = new Coordinate[coords.length];
        int l = coords.length - 1;

        // a) move each segment along the outward direction
        for (int i = 0; i<l; ++i) {
            Coordinate origin = coords[i  ];
            Coordinate target = coords[i+1];

            Coordinate dir = ShapeUtils.outward(origin, target);

            movedOrigins[i] = ShapeUtils.lin(origin, offsets[i], dir);
            movedTargets[i] = ShapeUtils.lin(target, offsets[i], dir);
        }
        // fill the last coordinate
        movedOrigins[l] = movedOrigins[0];
        movedTargets[l] = movedTargets[0];

        // b) compute the intersections
        for (int i=0, j=l-1; i<=l; ++i) {
            Coordinate intersection = ShapeUtils.intersection(
                movedOrigins[j], movedTargets[j], movedOrigins[i], movedTargets[i]
            );
            intersections[i] = intersection;

            j = i;
        }
        // fill the last coordinate
        intersections[l] = intersections[0];

        // c) create the new boundary
        return boundary.getFactory().createLineString(intersections);
    }

    /**
     * Intersect two finite, two-coordinate segments in the XY plane.
     * For collinear segments (including overlapping segments), return the
     * midpoint of the closest pair of endpoints, one from each segment.
     * Ties are resolved by endpoint order in the input segments.
     *
     * @return a new intersection coordinate, or null if non-collinear segments
     *         do not intersect
     * @throws IllegalArgumentException if either segment is null or does not
     *         contain exactly two coordinates
     */
    public static Coordinate intersection(LineString first, LineString second) {
        // if (first == null || second == null
        //         || first.getNumPoints() != 2 || second.getNumPoints() != 2)
        //     throw new IllegalArgumentException("Segments must contain exactly two coordinates");

        Coordinate a = first.getCoordinateN(0);
        Coordinate b = first.getCoordinateN(1);
        Coordinate c = second.getCoordinateN(0);
        Coordinate d = second.getCoordinateN(1);

        return ShapeUtils.intersection(a, b, c, d);
    }


    // ----------------------------------------------------------------------

    private static LineString expandBoundaryUsingSimpleRules(BoundaryOffsets boundaryOffsets, double maxCollinearAngle) {
        LineString boundary = boundaryOffsets.boundary;
        double[] offsets = boundaryOffsets.offsets;

        Coordinate[] coords = boundary.getCoordinates();
        Coordinate[] movedOrigins = new Coordinate[coords.length];
        Coordinate[] movedTargets = new Coordinate[coords.length];
        Coordinate[] intersections = new Coordinate[2*coords.length];
        int l = coords.length - 1;
        int n = 0;

        // a) move each segment along the outward direction
        for (int i = 0; i<l; ++i) {
            Coordinate origin = coords[i  ];
            Coordinate target = coords[i+1];

            Coordinate dir = ShapeUtils.outward(origin, target);

            movedOrigins[i] = ShapeUtils.lin(origin, offsets[i], dir);
            movedTargets[i] = ShapeUtils.lin(target, offsets[i], dir);
        }
        // fill the last coordinate
        movedOrigins[l] = movedOrigins[0];
        movedTargets[l] = movedTargets[0];

        // b) compute the intersections
        for (int i=0, j=l-1; i<l; ++i) {
            Coordinate po = movedOrigins[j];
            Coordinate pt = movedTargets[j];
            Coordinate co = movedOrigins[i];
            Coordinate ct = movedTargets[i];

            // check for collinearity
            Coordinate ci = ShapeUtils.intersection(po, pt, co, ct);
            double angle = ShapeUtils.angle(coords[j], coords[i], coords[i+1]);


            if (Math.abs(angle) < maxCollinearAngle) {
                // ci = ShapeUtils.center(pt, co);
                intersections[n++] = ci;
            }
            else if (angle > 0) {
                intersections[n++] = pt;
                intersections[n++] = co;
            }
            else {
                intersections[n++] = ci;
            }

            j = i;
        }
        // fill the last coordinate
        intersections[n++] = intersections[0];
        intersections = Arrays.copyOf(intersections, n);

        return boundary.getFactory().createLineString(intersections);
    }

    private static LineString expandBoundaryPlain(BoundaryOffsets boundaryOffsets, double maxCollinearAngle) {
        LineString boundary = boundaryOffsets.boundary;
        double[] offsets = boundaryOffsets.offsets;

        Coordinate[] coords = boundary.getCoordinates();
        Coordinate[] movedOrigins = new Coordinate[coords.length];
        Coordinate[] movedTargets = new Coordinate[coords.length];
        Coordinate[] intersections = new Coordinate[2*coords.length];
        int l = coords.length - 1;
        int n = 0;

        // a) move each segment along the outward direction
        for (int i = 0; i<l; ++i) {
            Coordinate origin = coords[i  ];
            Coordinate target = coords[i+1];

            Coordinate dir = ShapeUtils.outward(origin, target);

            movedOrigins[i] = ShapeUtils.lin(origin, offsets[i], dir);
            movedTargets[i] = ShapeUtils.lin(target, offsets[i], dir);
        }
        // fill the last coordinate
        movedOrigins[l] = movedOrigins[0];
        movedTargets[l] = movedTargets[0];

        // b) compute the intersections
        for (int i=0, j=l-1; i<l; ++i) {
            Coordinate po = movedOrigins[j];
            Coordinate pt = movedTargets[j];
            Coordinate co = movedOrigins[i];
            Coordinate ct = movedTargets[i];
            Coordinate ci;

            double angle = ShapeUtils.angle(coords[j], coords[i], coords[i+1]);

            if (Math.abs(angle) < maxCollinearAngle) {
                intersections[n++] = co;
            }
            else if (angle > 0) {
                intersections[n++] = co;
                intersections[n++] = pt;
            }
            else {
                intersections[n++] = pt;
                intersections[n++] = co;
            }

            j = i;
        }
        // fill the last coordinate
        intersections[n++] = intersections[0];
        intersections = Arrays.copyOf(intersections, n);

        if (n< 3) return null;

        return boundary.getFactory().createLineString(intersections);
    }

}
