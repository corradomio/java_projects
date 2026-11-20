package jext.geotools.junction;

import jext.geotools.Op;
import jext.geotools.ShapeUtils;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineString;

import java.util.Objects;

public class JunctionBridge {

    private int hashCode = 0;
    public final Junction origin;
    public final Junction target;

    JunctionBridge(
        Junction origin,
        Junction target
    ) {
        this.origin = origin;
        this.target = target;
    }

    public int hashCode() {
        if (hashCode != 0)
            return hashCode;

        // ensure the hashCode independent on the
        // order of origin and target

        hashCode = Objects.hash(origin, target);

        // int originHash = origin.hashCode();
        // int targetHash = target.hashCode();
        //
        // if (originHash < targetHash) {
        //     hashCode = Objects.hash(origin, target);
        // }
        // else {
        //     hashCode = Objects.hash(target, origin);
        // }

        return hashCode;
    }

    public double distance() {
        return this.origin.coordinate.distance(this.target.coordinate);
    }

    public LineString outward() {
        return this.origin.outward();
    }

    public LineString direction() {
        Coordinate dir = ShapeUtils.direction(this.origin.coordinate, this.target.coordinate);
        Coordinate origin = this.origin.coordinate;
        Coordinate target = ShapeUtils.lin(origin, 1, dir);
        return this.origin.expanded.getFactory().createLineString(new Coordinate[]{
            origin, target
        });
    }


    public LineString bridge() {
        return origin.expanded.getFactory().createLineString(new Coordinate[]{
            origin.coordinate, target.coordinate
        });
    }

    public double angle() {
        double otAngle, toAngle;
        {
            Coordinate outward = ShapeUtils.outward(origin.expanded.getCoordinates(), origin.index);
            Coordinate dir = ShapeUtils.direction(origin.coordinate, target.coordinate);
            otAngle = ShapeUtils.absAngle(dir, outward);
        }
        {
            Coordinate outward = ShapeUtils.outward(target.expanded.getCoordinates(), target.index);
            Coordinate dir = ShapeUtils.direction(target.coordinate, origin.coordinate);
            toAngle = ShapeUtils.absAngle(dir, outward);
        }

        double angle = Math.max(otAngle, toAngle);

        return (angle <= Math.PI) ? angle : (Op.TWO_PI - angle);
    }
}
