package jext.geotools;

import jext.util.Assert;
import org.locationtech.jts.algorithm.RobustLineIntersector;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineString;

public class ShapeUtils {

    public static String toString(Coordinate c) {
        return String.format("(%.2f, %.2f)", c.x, c.y);
    }

    // ----------------------------------------------------------------------
    // Polyline
    // ----------------------------------------------------------------------

    public static String id(LineString ls) {
        return String.format("%d:%d:%d", mod(ls.hashCode(), 10000), ls.getNumPoints()-1, 0);
    }

    /**
     * Check if the polyline follows the right-handle rule
     * Note: the last point is the same than the first one
     * It is used the cross product chain
     * @param ls
     * @return
     */
    public static boolean isValidOrder(LineString ls) {
        Assert.verify(isClosed(ls), "It is not a closed line");
        return isValidOrder(ls.getCoordinates());
    }

    public static boolean isValidOrder(Coordinate[] coords) {
        int n = coords.length;
        double cp = 0;

        for (int i = 0, j=1; j < n; i++,j++) {
            Coordinate ci = coords[i];
            Coordinate cj = coords[j];
            cp += (ci.y*cj.x - ci.x*cj.y);
        }

        return cp > 0;
    }

    // /**
    //  * Invert the order of the coordinates, INPLACE
    //  * @param ls linestring to process
    //  */
    // public static void invertOrder(LineString ls) {
    //     invertOrder(ls.getCoordinates());
    // }

    // public static void invertOrder(Coordinate[] coords) {
    //     int i=0, j=coords.length-1;
    //
    //     while(i < j) {
    //         Coordinate t = coords[i];
    //         coords[i] = coords[j];
    //         coords[j] = t;
    //         i++;
    //         j--;
    //     }
    // }

    // /**
    //  * Ensure the linestring has the correct points order
    //  * @param ls
    //  */
    // public static LineString ensureValidOrder(LineString ls) {
    //     if (!isValidOrder(ls)) {
    //         invertOrder(ls);
    //     }
    // }

    // ----------------------------------------------------------------------
    // Segment utils
    // ----------------------------------------------------------------------

    /**
     * A 'segment' is a line string of exactly 2 points
     *
     * @param segment linestring to check
     * @return
     */
    public static boolean isSegment(LineString segment) {
        return segment.getNumPoints() == 2;
    }

    /**
     * Check if the line string is closed.
     * It is closed if the last point has the same coordinates of the first one
     *
     * @param ls linestring to check
     * @return if it is closed
     */
    public static boolean isClosed(LineString ls) {
        Coordinate[] coords = ls.getCoordinates();
        int l =  coords.length - 1;

        return l > 2 && eq(coords[0].x, coords[l].x) && eq(coords[0].y, coords[l].y);
    }

    /**
     * Check if the segment is no 0-length
     *
     * @param segment segment to analize
     * @return if is no 0-length
     */
    public static boolean isValid(LineString segment) {
        Assert.verify(isSegment(segment), "Is not a segment");

        //return !eq(0, length(segment.getCoordinateN(0), segment.getCoordinateN(1)));
        return !eq(0, segment.getLength());
    }

    /**
     * Compute the outward direction of a segment [c[0]->c[1]]
     *
     * @param segment segment to analyze
     * @return outward direction
     */
    public static LineString outward(LineString segment) {
        Assert.verify(isSegment(segment), "Is not a segment");

        Coordinate[] coords = segment.getCoordinates();
        Coordinate from = center(coords[0], coords[1]);
        Coordinate outward = outward(coords[0], coords[1]);
        Coordinate to = lin(from, 1, outward);

        // return new LineString(new Coordinate[]{from, to},
        //     segment.getPrecisionModel(),
        //     segment.getSRID()
        // );
        return segment.getFactory().createLineString(new Coordinate[]{from, to});
    }

    // ----------------------------------------------------------------------
    // Coordinate utils
    // ----------------------------------------------------------------------

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
    public static Coordinate intersection(Coordinate a, Coordinate b, Coordinate c, Coordinate d) {
        RobustLineIntersector intersector = new RobustLineIntersector();
        intersector.computeIntersection(a, b, c, d);
        if (intersector.hasIntersection())
            return intersector.getIntersection(0);

        // if (Orientation.index(a, b, c) == 0 && Orientation.index(a, b, d) == 0
        //     && Orientation.index(c, d, a) == 0 && Orientation.index(c, d, b) == 0)
        {
            Coordinate nearestFirst = a;
            Coordinate nearestSecond = c;
            double distance = Double.POSITIVE_INFINITY;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    // Coordinate p = first.getCoordinateN(i);
                    // Coordinate q = second.getCoordinateN(j);
                    Coordinate p = (i == 0) ? a : b;
                    Coordinate q = (j == 0) ? c : d;
                    double candidate = Math.hypot(p.x - q.x, p.y - q.y);
                    if (candidate < distance) {
                        distance = candidate;
                        nearestFirst = p;
                        nearestSecond = q;
                    }
                }
            }
            return new Coordinate(
                (nearestFirst.x + nearestSecond.x) / 2,
                (nearestFirst.y + nearestSecond.y) / 2);
        }
    }

    /**
     * Compute the center between two points:  (origin+target)/2
     *
     * @param origin first point
     * @param target end point
     * @return coordinates of the center
     */
    public static Coordinate center(Coordinate origin, Coordinate target) {
        return new Coordinate((origin.x+target.x)/2,(origin.y+target.y)/2);
    }


    public static Coordinate between(Coordinate origin, double offset, Coordinate target) {
        double dist = origin.distance(target);
        Coordinate dir = direction(origin, target);

        if (offset > 0) {
            if (offset < dist)
                return lin(origin, offset, dir);
            else
                return lin(origin, dist/2, dir);
        }
        else {
            offset = -offset;
            if (offset < dist)
                return lin(origin, dist-offset, dir);
            else
                return lin(origin, dist/2, dir);
        }
    }

    // ----------------------------------------------------------------------
    // Angles
    // ----------------------------------------------------------------------

    public static double absAngle(Coordinate u, Coordinate v) {
        // Compute cross product (z-component in 3D)
        double crossProduct = u.x * v.y - u.y * v.x;

        // Compute dot product
        double dotProduct = u.x * v.x + u.y * v.y;

        // Use atan2 for robust angle calculation
        // atan2(y, x) returns angle whose tan is y/x, with correct quadrant
        double angle = Math.atan2(crossProduct, dotProduct);

        return Math.abs(angle);
    }


    public static double angle(Coordinate u, Coordinate v) {
        // Compute cross product (z-component in 3D)
        double crossProduct = u.x * v.y - u.y * v.x;

        // Compute dot product
        double dotProduct = u.x * v.x + u.y * v.y;

        // Use atan2 for robust angle calculation
        // atan2(y, x) returns angle whose tan is y/x, with correct quadrant
        double angle = Math.atan2(crossProduct, dotProduct);

        return angle >= 0 ? angle : Op.TWO_PI + angle;
    }

    /**
     * Compute the smaller angle at middle between [origin, middle] and
     * [middle, target] in the XY plane.
     * A straight [origin->middle->target] line has angle PI.
     *
     * @param a first point
     * @param b vertex of the angle
     * @param c end point
     * @return unsigned angle in radians, in [0, PI]
     * @throws IllegalArgumentException if a point is null or either segment
     *         has zero length in the XY plane
     */
    public static double internalAngle(Coordinate a, Coordinate b, Coordinate c) {
        double ux = a.x - b.x;
        double uy = a.y - b.y;
        double vx = c.x - b.x;
        double vy = c.y - b.y;

        // Compute cross product (z-component in 3D)
        double crossProduct = ux * vy - uy * vx;

        // Compute dot product
        double dotProduct = ux * vx + uy * vy;

        // Use atan2 for robust angle calculation
        // atan2(y, x) returns angle whose tan is y/x, with correct quadrant
        double angle = Math.atan2(crossProduct, dotProduct);

        return angle >= 0 ? angle : Op.TWO_PI + angle;
    }
    public static double angle(Coordinate a, Coordinate b, Coordinate c) {
        return internalAngle(a, b, c);
    }

    public static double collinearAngle(Coordinate a, Coordinate b, Coordinate c) {
        return Math.abs(internalAngle(a,b,c) - Math.PI);
    }

    public static double externalAngle(Coordinate a, Coordinate b, Coordinate c) {
        return Op.TWO_PI - internalAngle(a, b, c);
    }

    public static Coordinate rotate(Coordinate p, double angle) {
        double c = Math.cos(angle);
        double s = Math.sin(angle);

        double x = p.x*c - p.y*s;
        double y = p.x*s + p.y*c;
        return new Coordinate(x, y);
    }

    // ----------------------------------------------------------------------

    public static Coordinate outward(Coordinate[] coords, int i) {
        int j = mod(i+1, coords.length - 1);
        return outward(coords[i], coords[j]);
    }

    /**
     * Compute the outward direction of 2 points [origin->target]
     * Note: it is supposed the point are in counterclockwise order
     * following the right-hand rule
     *
     * @param origin first point
     * @param target end point
     * @return outward direction
     */
    public static Coordinate outward(Coordinate origin, Coordinate target) {
        double dx = target.x-origin.x;
        double dy = target.y-origin.y;
        Coordinate outward = new Coordinate(-dy, dx);
        return direction(outward);
    }

    /**
     * Compute the outward direction in the middle point [origin->middle->target]
     * The direction is the mean of the direction [origin->middle] and [middle->target]
     * Note: it is supposed the point are in counter-clockwise order following the
     * right-hand rule
     *
     * @param origin first point
     * @param middle point where the direction is computed
     * @param target end point
     * @return outward direction
     */
    public static Coordinate outward(Coordinate origin, Coordinate middle, Coordinate target) {
        Coordinate dom = outward(origin, middle);
        Coordinate dmt = outward(middle, target);
        Coordinate dir =  new Coordinate((dom.x+dmt.x)/2, (dom.y+dmt.y)/2);
        return direction(dir);
    }

    // /**
    //  * Compute the distance between two points
    //  * Note: Coordinate::distance compute the distance on the Hearth geoid
    //  *       Here it is enough to use the Euclidean distance
    //  *
    //  * @param origin first point
    //  * @param target end point
    //  * @return distance between two points
    //  */
    // public static double distance(Coordinate origin, Coordinate target) {
    //     return Math.sqrt(sq(origin.x - target.x) + sq(origin.y - target.y));
    // }

    public static double distance(Coordinate[] coords, int origin, int target) {
        double distance = 0;
        int l = coords.length - 1;
        int i=origin, j;

        while(i != target) {
            j = mod(i + 1, l);

            //distance += distance(coords[i], coords[j]);
            distance += coords[i].distance(coords[j]);
            i = j;
        }
        return distance;
    }

    /**
     * Compute the length of the vector represented by the point
     * (or the distance from the origin)
     *
     * @param target first point
     * @return length of the vector
     */
    public static double length(Coordinate target) {
        return Math.sqrt(sq(target.x) + sq(target.y));
    }

    /**
     * Normalize the directory in such way to have length 0 or 1
     *
     * @param dir direction
     * @return normalized direction
     */
    public static Coordinate direction(Coordinate dir) {
        double len = Math.sqrt(sq(dir.x) + sq(dir.y));
        if (eq(len, 0))
            return new  Coordinate(0,0);
        else
            return new Coordinate(dir.x/len, dir.y/len);
    }

    public static Coordinate direction(Coordinate origin, Coordinate target) {
        double dx = target.x - origin.x;
        double dy = target.y - origin.y;
        double len = Math.sqrt(sq(dx) + sq(dy));
        if (eq(len, 0))
            return new  Coordinate(0,0);
        else
            return new Coordinate(dx/len, dy/len);
    }

    /**
     * Compute:  p + step*dir
     *
     * @param p origin point
     * @param step step
     * @param dir direction (modulo 1)
     * @return linear transformation of the point
     */
    public static Coordinate lin(Coordinate p, double step, Coordinate dir) {
        return new Coordinate(p.x + step*dir.x, p.y + step*dir.y);
    }

    // ----------------------------------------------------------------------
    // Utilities
    // ----------------------------------------------------------------------

    private static double sq(double x) {
        return x*x;
    }

    private static double EPS = 0.01;

    private static boolean eq(double u, double v) {
        // u, v are in meters -> difference less than 10cm
        return Math.abs(u-v) < EPS;
    }

    private static int mod(int i, int n) {
        int m = i%n;
        if (m < 0) m += n;
        return m;
    }


    // ----------------------------------------------------------------------
    // Array drop
    // ----------------------------------------------------------------------

    public static <T> int drop(T[] array, int i, int n) {
        for (int j=i+1; j<n; ++j, ++i) {
            array[i] = array[j];
        }
        return n-1;
    }

}
