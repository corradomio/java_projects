package jext.optim.problems.distance;

import jext.optim.problems.Coords;

import java.util.List;

import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;
import static jext.util.MathUtils.sq;

public class GeoSpace extends MetricSpace {

    public GeoSpace(Coords[] points) {
        super(points);
    }

    public GeoSpace(List<? extends Coords> points) {
        super(points);
    }

    @Override
    protected float distance(Coords li, Coords lj) {
        return distance(li.getX(), li.getY(), lj.getX(), lj.getY());
    }

    private static final float R = 6371.0088f; // Km

    private static float distance(double lon1, double lat1, double lon2, double lat2) {
        double dlat, dlon, a, c;

        lat1 = toRadians(lat1);
        lon1 = toRadians(lon1);
        lat2 = toRadians(lat2);
        lon2 = toRadians(lon2);
        dlon = lon2 - lon1;
        dlat = lat2 - lat1;

        a = sq(sin(dlat/2)) + cos(lat1) * cos(lat2) * sq(sin(dlon/2));
        c = R * 2 * atan2(sqrt(a), sqrt(1-a));

        return (float) c;
    }

}
