package jext.geotools.junction;

import jext.geotools.BoundaryExpander;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;

import java.util.List;

public class BoundaryChamfers {

    public final LineString boundary;
    public final LineString expanded;
    public final List<Chamfer> chamfers;

    public BoundaryChamfers(BoundaryExpander expandedBoundary, List<Chamfer> chamfers) {
        this.boundary = expandedBoundary.boundary;
        this.expanded = expandedBoundary.expanded;
        this.chamfers = chamfers;
    }

    public List<Point> points() {
        return chamfers.stream().map(Chamfer::point).toList();
    }

    public List<Point> expandedPoint() {
        return chamfers.stream().map(Chamfer::expandedPoint).toList();
    }
}
