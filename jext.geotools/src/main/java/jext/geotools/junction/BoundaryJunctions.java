package jext.geotools.junction;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoundaryJunctions {

    public final LineString boundary;
    public final LineString expanded;
    public final LineString expandedWithJunctions;
    public final List<Chamfer> chamfers;

    public final List<Junction> nearJunctions;  // junctions near the chamfers
    public final List<Junction> farJunctions;   // junctions between the chamfers
    public final List<Junction> junctions;      // union of all junctions

    // boundary composed using ONLY near junction vertices
    // private LineString nearJunctionBoundary;    // boundary connecting ONLY the near junctions

    // -----------------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------------

    public BoundaryJunctions(
        BoundaryChamfers boundaryChamfers,
        List<Junction> nearJunctions, List<Junction> farJunctions,
        LineString expandedWithJunctions
    ) {
        this.boundary = boundaryChamfers.boundary;
        this.expanded = boundaryChamfers.expanded;
        this.chamfers = boundaryChamfers.chamfers;

        this.nearJunctions = nearJunctions;
        this.farJunctions = farJunctions;
        this.junctions = new ArrayList<>();
        this.junctions.addAll(nearJunctions);
        this.junctions.addAll(farJunctions);
        this.expandedWithJunctions = expandedWithJunctions;
    }

    public List<Point> points() {
        return junctions.stream().map(Junction::point).toList();
    }

    public List<LineString> outwards() {
        return junctions.stream().map(Junction::outward).toList();
    }

    public List<Point> nearPoints() {
        return nearJunctions.stream().map(Junction::point).toList();
    }

    public List<Point> farPoints() {
        return farJunctions.stream().map(Junction::point).toList();
    }

    // public Optional<LineString> nearJunctionBoundary() {
    //     if (this.nearJunctions.isEmpty())
    //         return Optional.empty();
    //
    //     if (this.nearJunctionBoundary != null)
    //         return Optional.of(this.nearJunctionBoundary);
    //
    //     int i, l = this.nearJunctions.size();
    //     Coordinate[] coords = new Coordinate[l+1];
    //
    //     i = 0;
    //     for (Junction j : nearJunctions) {
    //         coords[i++] = j.coordinate;
    //     }
    //     // add last point
    //     coords[i] = coords[0];
    //
    //     this.nearJunctionBoundary = this.boundary.getFactory().createLineString(coords);
    //     return Optional.of(this.nearJunctionBoundary);
    // }
}
