package jext.geotools;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineString;
import jext.geotools.RayBoundaryIntersector.IntersectionResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoundaryIntersections {

    public final LineString boundary;
    public final LineString[] directions;
    public final LineString[] allIntersections;
    public final double[] lengths;
    public final List<LineString> intersections = new ArrayList<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    BoundaryIntersections(OutwardDirections outwardDirections) {
        this.boundary = outwardDirections.boundary;
        this.directions = outwardDirections.allDrections;
        this.allIntersections = new LineString[directions.length];
        this.lengths = new double[directions.length];
    }

    void done() {
        for (int i=0; i<allIntersections.length-1; i++) {
            if (allIntersections[i] != null) {
                intersections.add(allIntersections[i]);
            }
        }
    }

    // ----------------------------------------------------------------------
    // boundaryIntersections
    // ----------------------------------------------------------------------

    /**
     * Compute the boundary intersections between the list of all boundary directions
     *
     * Note: set maxDistance or deltaAngle to disable te filters on intersection distances
     *  or intersection angles
     *
     * @param outwardDirections list of boundary directions
     * @param maxDistance maximum distance of the intersection to consider valid.
     *                    Use 0 to disable the filter
     * @param deltaAngle maximum angle, respect the perfect perpendicular direction
     *                   to consider the direction valid.
     *                   Use 0 to disable the filter
     * @return
     */
    public static List<BoundaryIntersections> boundaryIntersections(
        List<OutwardDirections> outwardDirections,
        double maxDistance, double deltaAngle
    ) {
        List<BoundaryIntersections> boundariesIntersections = new ArrayList<>();
        double minAngleDegree = 90 - deltaAngle;

        // extract the list of boundaries
        List<LineString> boundaries = outwardDirections.stream().map(od -> od.boundary).toList();

        RayBoundaryIntersector rbi = new RayBoundaryIntersector(boundaries);

        for (OutwardDirections od : outwardDirections) {
            BoundaryIntersections boundaryIntersections = new  BoundaryIntersections(od);

            int n = od.allDrections.length;
            for (int i=0; i<n; i++) {
                LineString outwardDir = od.allDrections[i];

                if (!ShapeUtils.isValid(outwardDir))
                    continue;

                // The angle is in range [0, 90] degree, then positive
                Optional<IntersectionResult> intersection = rbi.nearestIntersection(outwardDir);
                if (intersection.isEmpty())
                    continue;

                if (deltaAngle > 0 && intersection.get().angleDegrees() < minAngleDegree)
                    continue;

                if (maxDistance > 0 && intersection.get().length() > maxDistance)
                    continue;

                boundaryIntersections.allIntersections[i] = intersection.get().segment();
                boundaryIntersections.lengths[i] = intersection.get().length();
            }
            // fill the last element
            boundaryIntersections.allIntersections[n-1] = boundaryIntersections.allIntersections[0];
            boundaryIntersections.lengths[n-1] = boundaryIntersections.lengths[0];
            boundaryIntersections.done();

            boundariesIntersections.add(boundaryIntersections);
        }


        return boundariesIntersections;
    }

    public static List<BoundaryIntersections> boundaryIntersections(List<OutwardDirections> outwardDirections) {
        return boundaryIntersections(outwardDirections, 0, 0);
    }

    // ----------------------------------------------------------------------

    public static List<LineString> boundaryIntersections(List<LineString> boundaries, List<LineString> outwardDirections) {
        return boundaryIntersections(boundaries, outwardDirections, 0, 0);
    }

    public static List<LineString> boundaryIntersections(
        List<LineString> boundaries, List<LineString> outwardDirections,
        double maxDistance, double deltaAngle
    ) {
        List<LineString> intersections = new ArrayList<>();
        double minAngleDegree = 90 - deltaAngle;

        RayBoundaryIntersector rbi = new RayBoundaryIntersector(boundaries);

        for (LineString outwardDir : outwardDirections) {
            if (!ShapeUtils.isValid(outwardDir)) {
                // ShapeUtils.isValid(outwardDir);
                continue;
            }

            Optional<IntersectionResult> intersection = rbi.nearestIntersection(outwardDir);
            if (intersection.isEmpty())
                continue;

            if (deltaAngle > 0 && intersection.get().angleDegrees() < minAngleDegree)
                continue;

            if (maxDistance > 0 && intersection.get().length() > maxDistance)
                continue;

            // Coordinate v = intersection.get().point().getCoordinate();
            // Coordinate u = outwardDir.getCoordinateN(0);
            Coordinate u = intersection.get().segment().getCoordinateN(0);
            Coordinate v = intersection.get().segment().getCoordinateN(1);

            // LineString li = new LineString(new Coordinate[]{u,v}, outwardDir.getPrecisionModel(), outwardDir.getSRID());
            LineString isegment = outwardDir.getFactory().createLineString(new Coordinate[]{u,v});
            intersections.add(isegment);
        }

        return intersections;
    }

}
