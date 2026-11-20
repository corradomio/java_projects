package jext.geotools;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineString;

import java.util.ArrayList;
import java.util.List;

/**
 * Increase the number of segments composing a boundary.
 *
 */
public class BoundarySegmenter {

    public static List<LineString> segmentate(List<LineString> boundaries, double maxLength) {
        List<LineString> segmentated = new ArrayList<>();
        for (LineString boundary : boundaries) {
            segmentated.add(segmentate(boundary, maxLength));
        }
        return segmentated;
    }

    public static LineString segmentate(LineString boundary, double maxLength) {

        if (maxLength == 0) return boundary;

        List<Coordinate> segments = new ArrayList<>();
        Coordinate[] coords = boundary.getCoordinates();
        int l =  coords.length - 1;
        double halfLength = maxLength / 2.0;
        double limitLength = maxLength + halfLength;

        // add the first point

        for (int i=0, j=1; i < l; i++,j++) {
            Coordinate origin = coords[i];
            Coordinate target = coords[j];

            // add the orinig
            segments.add(origin);

            double length = origin.distance(target);
            if (length <= limitLength)
                continue;

            int ns = (int) (length/maxLength) + 1;

            double step = length/ns;
            Coordinate dir = ShapeUtils.direction(origin, target);

            for (int s=1; s<ns; ++s) {
                Coordinate p = ShapeUtils.lin(origin, s*step, dir);
                segments.add(p);
            }
        }
        segments.add(segments.get(0));
        coords = segments.toArray(coords);
        return boundary.getFactory().createLineString(coords);
    }
}
