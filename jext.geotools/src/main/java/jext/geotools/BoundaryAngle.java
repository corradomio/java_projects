package jext.geotools;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineString;

import java.util.ArrayList;
import java.util.List;

public class BoundaryAngle {

    public final LineString boundary;
    public final Coordinate coord;
    public final int index;
    public final double angle;

    BoundaryAngle(LineString boundary, Coordinate coord, int index, double angle) {
        this.boundary = boundary;
        this.coord = coord;
        this.index = index;
        this.angle = angle;
    }

    public static List<List<BoundaryAngle>> boundaryAngles(List<LineString> boundaries) {
        List<List<BoundaryAngle>> angles = new ArrayList<>();

        for (LineString boundary : boundaries) {
            angles.add(
                boundaryAngles(boundary)
            );
        }
        return angles;
    }

    public static List<BoundaryAngle> boundaryAngles(LineString boundary) {
        Coordinate[] coords = boundary.getCoordinates();
        int l = coords.length - 1;
        List<BoundaryAngle> angles = new ArrayList<>();

        for (int i=0; i<l; ++i) {
            int p = Op.dec(i, l);
            int s = Op.inc(i, l);

            double angle = ShapeUtils.internalAngle(coords[p], coords[i], coords[s]);

            angles.add(new BoundaryAngle(
                boundary,
                coords[i],
                i,
                angle
            ));
        }

        return angles;
    }
}
