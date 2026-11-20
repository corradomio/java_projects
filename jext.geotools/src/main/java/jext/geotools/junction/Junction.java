package jext.geotools.junction;

import jext.geotools.Op;
import jext.geotools.ShapeUtils;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;


public class Junction {

    public final LineString expanded;
    public final int index;
    public final double offset;
    // public final Coordinate origin;
    public final Coordinate coordinate;

    Junction(
        LineString expanded,
        int index,
        double offset,
        Coordinate coordinate
    ) {
        this.expanded = expanded;
        this.index = index;
        // this.origin = expanded.getCoordinateN(index);
        this.offset = offset;
        this.coordinate = coordinate;
    }

    public Point point() {
        return expanded.getFactory().createPoint(coordinate);
    }

    public LineString outward() {
        Coordinate[] coords = expanded.getCoordinates();
        Coordinate dir = ShapeUtils.outward(coords, index);
        Coordinate origin = this.coordinate;
        Coordinate target = ShapeUtils.lin(this.coordinate, 1, dir);

        return expanded.getFactory().createLineString(new Coordinate[]{
            origin, target
        });
    }

    public double distance(Junction that) {
        return this.coordinate.distance(that.coordinate);
    }

    public double angle(Junction that) {
        Coordinate u = ShapeUtils.outward(expanded.getCoordinates(), index);
        Coordinate v = ShapeUtils.direction(this.coordinate, that.coordinate);

        return ShapeUtils.angle(u, v);
    }

    @Override
    public int hashCode() {
        // coordinate identify the junction
        return coordinate.hashCode();
    }
}
