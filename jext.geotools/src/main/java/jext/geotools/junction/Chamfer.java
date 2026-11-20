package jext.geotools.junction;

import jext.geotools.Op;
import jext.util.Assert;
import jext.geotools.ShapeUtils;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;

/*
    Where the chamfer is located:
    in boundary (90 degrees angle)
    in expanded (vertex STARTING the chamfer)
 */
public class Chamfer {
    // initial boundary
    final LineString boundary;
    // origin of the chamfer
    final int index;
    // expanded boundary
    final LineString expanded;
    // the chamfer on the expanded boundary is represented by vertices
    // [expandedIndex-1, expandedIndex]
    final int expandedIndex;


    Chamfer(LineString boundary, int index, LineString expanded, int expandedIndex) {
        this.boundary = boundary;
        this.index = index;

        this.expanded = expanded;
        this.expandedIndex = expandedIndex;
    }

    double distance(Chamfer that) {
        Assert.verify(this.expanded == that.expanded, "Chamfers not referring to the same boundary");

        Coordinate[] coords = this.expanded.getCoordinates();
        int l = coords.length - 1;
        int origin = Op.inc(this.expandedIndex, l);
        int target = that.expandedIndex;

        return ShapeUtils.distance(coords, origin, target);
    }

    public Point point() {
        return boundary.getFactory().createPoint(boundary.getCoordinates()[index]);
    }

    public Point expandedPoint() {
        return expanded.getFactory().createPoint(expanded.getCoordinates()[expandedIndex]);
    }

}
