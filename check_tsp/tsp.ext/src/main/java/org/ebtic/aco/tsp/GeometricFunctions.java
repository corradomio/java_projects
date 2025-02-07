package org.ebtic.aco.tsp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// import org.ebtic.networkmodel.model.Coordinate;
// import org.ebtic.rounding.DoubleRounder;
// import org.rew.networkbase.mdl.networkmodel.WayPointList;


public class GeometricFunctions {

    // public static double getGradient(Coordinate a, Coordinate b) {
    //     if (a.x == b.x) return Double.MAX_VALUE;
    //     else return ((b.y-a.y)/(b.x-a.x));
    // }
    //
    // public static double getAngleFromPoint(double dx, double dy) {
    //     return Math.atan2(dy, dx);
    //     //-pi<atan2(dy, dx)<=pi
    //     //Specifically, atan2 ? ( y , x ) is in the interval [0, p] when y = 0, and in (-p, 0) when y < 0.
    // }
    //
    // /**
    //  * Get angl ebetween two lines
    //  * @param m1: slope of line 1
    //  * @param m2: slope of line 2
    //  * @return
    //  */
    // public static double getAngleFromSlopes(double m1, double m2) {
    //     return Math.atan((m2-m1)/(1+m2*m1));
    //     //-pi<atan2(dy, dx)<=pi
    // }
    //
    // //03_11_2021
    // public static Coordinate getPointProjectionOnLine(Coordinate onLine1, Coordinate online2,Coordinate point)
    // {
    //     double slope=getSlope( onLine1,  online2);
    //     double slopeOrtho;
    //     if (Math.abs(slope)>=1E3)
    //     {
    //         //vertical line
    //         return new Coordinate(onLine1.x,point.y);
    //     }
    //     else
    //     if (Math.abs(slope)<=0.001)
    //     {
    //         //horizontal line
    //         return new Coordinate(point.x,onLine1.y);
    //     }
    //     else
    //     if (slope!=0)
    //     {//slope of two orthogonal lines m1 * m2 = - 1
    //         slopeOrtho=-1.0/slope;
    //     }
    //     else//slope=0;horizontal line
    //         slopeOrtho=Double.MAX_VALUE;//not sure this is right but it works well with getLineIntersection
    //     return getLineIntersection(onLine1, slope, point, slopeOrtho);
    // }
    //
    // public static double pointDistanceToLine(Coordinate onLine1, Coordinate online2, Coordinate point)
    // {
    //     Coordinate projection = getPointProjectionOnLine(onLine1, online2,point);
    //     return cartesianDistance(point, projection);
    // }
    //
    // public static double getSlope(Coordinate a, Coordinate b) {
    //     if (a.x == b.x)
    //         return Double.MAX_VALUE;
    //     else
    //         return ((b.y-a.y)/(b.x-a.x));
    // }
    //
    // public static Coordinate getLineIntersection(Coordinate point1, double m1, Coordinate point2, double m2) {
    //     double x, y, c1, c2;
    //     //boolean f;
    //     if (m1 == m2) {
    //         //same gradient therefore either lines do not intersect or
    //         //they lie on top of each other. In either case there is not a unique
    //         //intersection point.
    //         x = Double.MAX_VALUE;
    //         y = Double.MAX_VALUE;
    //         return null;
    //         //    f = false;
    //     }
    //     else {
    //         if ((m1 == Double.MAX_VALUE) || (m2 == Double.MAX_VALUE)) {
    //             if (m1 == Double.MAX_VALUE) {
    //                 c2 = point2.y-m2*point2.x;
    //                 x = point1.x;
    //                 y = m2*x + c2;
    //             }
    //             else {
    //                 c1 = point1.y-m1*point1.x;
    //                 x = point2.x;
    //                 y = m1*x + c1;
    //             }
    //         }
    //         else {
    //             c1 = point1.y-m1*point1.x;
    //             c2 = point2.y-m2*point2.x;
    //
    //             x = (c2-c1)/(m1-m2);
    //             y = m1*x + c1;
    //         }
    //     }
    //     return new Coordinate(x, y);
    // }

    public static double cartesianDistance(Coordinate a, Coordinate b)
    {
        double dx=a.getX()-b.getX();
        double dy=a.getY()-b.getY();
        return Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
    }

    // public static boolean pointBelongsToSegment(Coordinate segEnd, Coordinate segStart, Coordinate point)
    // {
    //     if (point.x> segStart.x && point.x> segEnd.x) return false;
    //     if (point.y> segStart.y && point.y> segEnd.y) return false;
    //     double segmentLength= cartesianDistance(segEnd,segStart);
    //     double d2Start= cartesianDistance(segEnd,point);
    //     double d2End= cartesianDistance(segStart,point);
    //     double tolerance=0.0001;//481 vs 477 does not work
    //     if ((d2End+d2Start)/segmentLength>(1-tolerance) && (d2End+d2Start)/segmentLength<(1+tolerance))
    //         return true;
    //
    //     return false;
    // }
    //
    // /**
    //  * Compute the angle [ba,bc]
    //  * @param a: first point
    //  * @param b: vertex of the angle
    //  * @param c: third point
    //  * @return angle value between 0 and 2*pi (check the range)
    //  * @author: Kin Poon & Anis Ouali
    //  */
    // public static double angleBetween3PointsUsingDifference( Coordinate a, Coordinate b, Coordinate c)
    // {
    //     double a1 = Math.atan2(a.getY()-b.getY(), a.getX()-b.getX());
    //     if (a1 < 0)
    //         a1 = 2*Math.PI + a1;
    //
    //
    //     double    a2 = Math.atan2(c.getY()-b.getY(), c.getX()-b.getX());
    //     if (a2 < 0)
    //         a2 = 2*Math.PI + a2;
    //
    //     double ang;
    //     if (a1 > a2)
    //         ang = 2*Math.PI-(a1 - a2);
    //     else
    //         ang = a2 - a1;
    //
    //     return ang;
    // }
    //
    //
    // /**
    //  * returns >0 if p2 on left of (p0->p1)
    //  * 		   =0 if on the line
    //  * 		   <0 if on the right
    //  * @param linep0
    //  * @param linep1
    //  * @param p2
    //  * @return
    //  * @author anis.ouali taken from geomalgorithms
    //  */
    // public static int isLeft(Coordinate linep0, Coordinate linep1, Coordinate p2)
    // {
    //     int res= (int) ((linep1.x-linep0.x)*(p2.y-linep0.y)-(p2.x-linep0.x)*(linep1.y-linep0.y));
    //     return res;
    // }
    //
    // public static double isLeftDouble(Coordinate linep0, Coordinate linep1, Coordinate p2) {
    //     return ((linep1.x-linep0.x)*(p2.y-linep0.y)-(p2.x-linep0.x)*(linep1.y-linep0.y));
    // }
    //
    // /**
    //  *
    //  * @param cluster
    //  * @return coordinate of the median point of a list of given points
    //  * @author anis.ouali
    //  */
    //
    // public static Coordinate getMedianPoint(ArrayList<Coordinate> cluster) {
    //     double mx=0, my=0;
    //     for (Coordinate n:cluster)
    //     {
    //         mx+=n.x;
    //         my+=n.y;
    //     }
    //     return new Coordinate(mx/cluster.size(),my/cluster.size());
    // }
    //
    // public static double pointDistanceToSegment(Coordinate send1, Coordinate send2, Coordinate point)
    // {
    //     Coordinate projection = getPointProjectionOnLine(send1, send2,point);
    //     boolean belongToSegment=pointBelongsToSegment(send1, send2, projection);
    //     if (belongToSegment)
    //         return cartesianDistance(point, projection);
    //     else
    //         return
    //             (Math.min(cartesianDistance(point, send1), cartesianDistance(point, send2)));
    // }
    //
    // public static double pointDistanceToSegmentWithWayPoints(List<Coordinate> segment, Coordinate point)
    // {
    //     double shortestDistance=Double.MAX_VALUE;
    //     double currentDistance;
    //     for(int i=0;i<segment.size()-1;i++)
    //     {
    //         Coordinate send1=segment.get(i);
    //         Coordinate send2=segment.get(i+1);
    //         currentDistance=pointDistanceToSegment(send1, send2, point);
    //         if (currentDistance<shortestDistance)
    //         {
    //             shortestDistance=currentDistance;
    //         }
    //     }
    //     return shortestDistance;
    // }
    //
    // /**
    //  *
    //  * @author kaltham.alromaithi
    //  * *returns the way points a given coordinate 'point' lies in between
    //  *'point' should belong to the segment
    //  */
    // public static double findWayPoints(List<Coordinate> segment, Coordinate point, Coordinate w1, Coordinate w2)
    // {
    //
    //     double shortestDistance=Double.MAX_VALUE;
    //     double currentDistance;
    //     for(int i=0;i<segment.size()-1;i++)
    //     {
    //         Coordinate send1=segment.get(i);
    //         Coordinate send2=segment.get(i+1);
    //
    //
    //         currentDistance=pointDistanceToSegment(send1, send2, point);
    //         if (currentDistance<shortestDistance)
    //         {
    //             shortestDistance=currentDistance;
    //             w1.setX(send1.getX());
    //             w1.setY(send1.getY());
    //
    //             w2.setX(send2.getX());
    //             w2.setY(send2.getY());
    //         }
    //
    //     }
    //     return shortestDistance;
    // }
    //
    // /**
    //  *
    //  *
    //  * @author anis.ouali
    //  *
    //  * based on the one written byKaltham. Modified to add a new output parameter:
    //  * the index of w1 in the segment waypoints.
    //  *returns the way points a given coordinate 'point' lies in between
    //  *'point' should belong to the segment
    //  */
    // public static double findWayPoints(List<Coordinate> segment, Coordinate point, Coordinate w1, Coordinate w2, int[] w1Index)
    // {
    //     double shortestDistance=Double.MAX_VALUE;
    //     double currentDistance;
    //
    //     for(int i=0;i<segment.size()-1;i++)
    //     {
    //         Coordinate send1=segment.get(i);
    //         Coordinate send2=segment.get(i+1);
    //
    //
    //         currentDistance=pointDistanceToSegment(send1, send2, point);
    //         if (currentDistance<shortestDistance)
    //         {
    //             shortestDistance=currentDistance;
    //             if (w1Index!=null)
    //                 w1Index[0]=i;
    //             w1.setX(send1.getX());
    //             w1.setY(send1.getY());
    //
    //             w2.setX(send2.getX());
    //             w2.setY(send2.getY());
    //         }
    //
    //     }
    //     return shortestDistance;
    // }
    //
    //
    //
    //
    // public static boolean pointCanBeProjectedOnSegment(Coordinate send1, Coordinate send2, Coordinate point)
    // {
    //     Coordinate projection = getPointProjectionOnLine(send1, send2,point);
    //     boolean belongToSegment=pointBelongsToSegment(send1, send2, projection);
    //     if (belongToSegment)
    //         return true;
    //     else
    //         return false;
    // }
    //
    //
    // public static ArrayList<Coordinate> pointCanBeProjectedOnSegmentWithWayPoints(List<Coordinate> segment, Coordinate point)
    // {
    //     ArrayList<Coordinate> enclosingPoints;
    //     boolean canBeProjected=false;
    //     for(int i=0;i<segment.size()-1;i++)
    //     {
    //         Coordinate send1=segment.get(i);
    //         Coordinate send2=segment.get(i+1);
    //         canBeProjected=pointCanBeProjectedOnSegment(send1, send2, point);
    //         if (canBeProjected)
    //         {
    //             enclosingPoints=new ArrayList<Coordinate>();
    //             enclosingPoints.add(send1);
    //             enclosingPoints.add(send2);
    //             return enclosingPoints;
    //         }
    //     }
    //     return null;
    // }
    //
    //
    // public static WayPointList translateLine(WayPointList outputWayPointList2,
    //                                          double transX, double transY) {
    //     WayPointList out2=new WayPointList();
    //     for (Coordinate p:outputWayPointList2)
    //     {
    //         out2.add(translatePoint(p,transX,transY));
    //     }
    //     return out2;
    // }
    //
    // public static ArrayList<Coordinate> translateLine(List<Coordinate> inputList,
    //                                                   double transX, double transY) {
    //     ArrayList<Coordinate> out2=new ArrayList<Coordinate>();
    //     for (Coordinate p:inputList)
    //     {
    //         out2.add(translatePoint(p,transX,transY));
    //     }
    //     return out2;
    // }
    //
    //
    //
    // /**
    //  * translates a point along a line segment with a specific distance.
    //  * @param start:point to be translated
    //  * @param end: point used to  define the direction of translation. [start,end)
    //  * @param distance: translation value
    //  * @return the coordinate of the translation image of start
    //  * @author Anis Ouali
    //  */
    // public static Coordinate translatePoint(Coordinate start,Coordinate end, double distance)
    // {
    //     double dx,dy;
    //     double slope=getSlope(start, end);
    //     double angle=Math.atan(slope);
    //     dx=Math.abs(distance*Math.cos(angle));
    //     dy=Math.abs(distance*Math.sin(angle));
    //     if (end.x<start.x)dx=-dx;
    //     if (end.y<start.y)dy=-dy;
    //     Coordinate output=new Coordinate(start.x+dx,start.y+dy);
    //     return output;
    // }
    // public static Coordinate translatePoint(Coordinate start, double tX, double tY)
    //
    // {
    //     Coordinate output=new Coordinate(start.x+tX,start.y+tY);
    //     return output;
    // }
    //
    //
    // public static Coordinate translatePointForEB(Coordinate offset,Coordinate start,Coordinate end, double distance)
    // {
    //     double dx,dy;
    //     double slope=getSlope(start, end);
    //     double angle=Math.atan(slope);
    //     dx=Math.abs(distance*Math.cos(angle));
    //     dy=Math.abs(distance*Math.sin(angle));
    //     Coordinate output;
    //     if (slope>0)
    //         output=translatePoint(offset,dx,dy);
    //     else
    //         output=translatePoint(offset,dx,-dy);
    //     return output;
    // }
    //
    // public static Coordinate translatePointForEB1(Coordinate offset,Coordinate start,Coordinate end, double distance)
    // {
    //     double dx,dy;
    //     double slope=getSlope(start, end);
    //     double angle=Math.atan(slope);
    //     dx=Math.abs(distance*Math.cos(angle));
    //     dy=Math.abs(distance*Math.sin(angle));
    //     Coordinate output;
    //     if (slope>0)
    //         output=translatePoint(offset,-dx,-dy);
    //     else
    //         output=translatePoint(offset,-dx,dy);
    //     return output;
    // }
    //
    // /**
    //  *
    //  * @param input: point to be rotated
    //  * @param angleVertex: center of rotation
    //  * @param angle: rotation angle
    //  * @return coordinate of the rotation image of input
    //  * @author Anis Ouali
    //  */
    // public static Coordinate rotatePoint(Coordinate input,Coordinate angleVertex, double angle)
    // {
    //     double x=(input.x-angleVertex.x)*Math.cos(angle)-(input.y-angleVertex.y)*Math.sin(angle);
    //     double y=(input.x-angleVertex.x)*Math.sin(angle)+(input.y-angleVertex.y)*Math.cos(angle);
    //     Coordinate output=new Coordinate(angleVertex.x+x,angleVertex.y+y);
    //     return output;
    // }
    //
    // /**
    //  * measures the number of intersections between a segment represented
    //  * by points [a,b] and a set of segments, each of which is represented by listp
    //  * @param a
    //  * @param b
    //  * @param listp
    //  * @return
    //  * @author anis.ouali
    //  */
    // public static int numberOfIntersections(Coordinate a, Coordinate b, ArrayList<ArrayList<Coordinate>> listp)
    // {
    //     double slope1 = getSlope(a, b);
    //     int intersectionCount=0;int intersectionIndex=0;
    //     int skip=0;
    //     for (ArrayList<Coordinate> wp:listp)
    //     {
    //         for (int i=0;i<wp.size()-1;i++)
    //         {
    //             if (!segmentsIntersect(a,b,wp.get(i), wp.get(i+1)))
    //             {
    //                 skip++;
    //                 continue;
    //             }
    //             double slope2=getSlope(wp.get(i),wp.get(i+1));
    //             Coordinate inters = getLineIntersection(a, slope1, wp.get(i), slope2);
    //             if (inters.x!=Double.MAX_VALUE
    //                 && pointBelongsToSegment(b, a, inters)
    //                 && pointBelongsToSegment(wp.get(i+1), wp.get(i), inters))
    //             {
    //                 intersectionCount++;
    //                 break;
    //             }
    //         }
    //         intersectionIndex++;
    //     }
    //     //System.out.println("[DEBUG, GeometricFunctions.numberOfIntersections(...)] Number of skips: "+skip);
    //     return intersectionCount;
    // }
    //
    // /**
    //  * returns 1 if there is an intersection, (0 if not) between a segment represented
    //  * by points [a,b] and a set of segments, each of which is represented by listp
    //  * @param a
    //  * @param b
    //  * @param listp
    //  * @return
    //  * @author anis.ouali
    //  */
    // public static int numberOfIntersections2(Coordinate a, Coordinate b, ArrayList<ArrayList<Coordinate>> listp)
    // {
    //     if (GeometricFunctions.cartesianDistance(a, b)>1000)//threshold after which we assume that there is definitely an intersection
    //         return 1;
    //     double slope1 = getSlope(a, b);
    //     int intersectionCount=0;
    //     int skip=0;
    //     for (ArrayList<Coordinate> wp:listp)
    //     {
    //         for (int i=0;i<wp.size()-1;i++)
    //         {
    //             if (!segmentsIntersect(a,b,wp.get(i), wp.get(i+1)))
    //             {
    //                 skip++;
    //                 continue;
    //             }
    //             double slope2=getSlope(wp.get(i),wp.get(i+1));
    //             Coordinate inters = getLineIntersection(a, slope1, wp.get(i), slope2);
    //             if (inters.x!=Double.MAX_VALUE
    //                 && pointBelongsToSegment(b, a, inters)
    //                 && pointBelongsToSegment(wp.get(i+1), wp.get(i), inters))
    //             {
    //                 intersectionCount++;
    //                 break;
    //             }
    //         }
    //         if (intersectionCount==1)
    //             break;
    //         //intersectionIndex++;
    //     }
    //     //System.out.println("[DEBUG, GeometricFunctions.numberOfIntersections(...)] Number of skips: "+skip);
    //     return intersectionCount;
    // }
    //
    //
    // //inspired from
    // //http://www.dcs.gla.ac.uk/~pat/52233/slides/Geometry1x1.pdf, PDF file in the DOC folder
    // //Introduction to Algorithms 3rd Edition by Clifford Stein, Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest
    // //inspired from code found on http://www.geeksforgeeks.org/check-if-two-given-line-segments-intersect/
    // // To find orientation of ordered triplet (p, q, r).
    // // The function returns following values
    // // 0 --> p, q and r are colinear
    // // 1 --> Clockwise
    // // 2 --> Counterclockwise
    // public static int getOrientation(Coordinate p, Coordinate q, Coordinate r)
    // {
    //     // See http://www.geeksforgeeks.org/orientation-3-ordered-points/
    //     // for details of below formula.
    //     double val = (q.y - p.y) * (r.x - q.x) -
    //         (q.x - p.x) * (r.y - q.y);
    //
    //     //if (val == 0) return 0;  // colinear original
    //     if (Math.abs(val)>=0 && Math.abs(val)<=0.01) return 0;
    //
    //     return (val > 0)? 1: 2; // clock:1 or counterclock:2 wise
    // }
    //
    //
    //
    // //inspired from
    // //http://www.dcs.gla.ac.uk/~pat/52233/slides/Geometry1x1.pdf, PDF file in the DOC folder
    // //Introduction to Algorithms 3rd Edition by Clifford Stein, Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest
    // //inspired from code found on http://www.geeksforgeeks.org/check-if-two-given-line-segments-intersect/
    //
    // public static boolean segmentsIntersect (Coordinate p1, Coordinate q1,Coordinate p2, Coordinate q2)
    // {
    //     {
    //         // Find the four orientations needed for general and
    //         // special cases
    //         int o1 = getOrientation(p1, q1, p2);
    //         int o2 = getOrientation(p1, q1, q2);
    //         int o3 = getOrientation(p2, q2, p1);
    //         int o4 = getOrientation(p2, q2, q1);
    //
    //         // General case
    //         if (o1 != o2 && o3 != o4)
    //             return true;
    //
    //         // Special Cases
    //         // p1, q1 and p2 are colinear and p2 lies on segment p1q1
    //         if (o1 == 0 &&
    //             //onSegment(p1, p2, q1)
    //             pointBelongsToSegment(p1, q1, p2)
    //         ) return true;
    //
    //         // p1, q1 and p2 are colinear and q2 lies on segment p1q1
    //         if (o2 == 0 &&
    //             //onSegment(p1, q2, q1)
    //             pointBelongsToSegment(p1, q1, q2)
    //         ) return true;
    //
    //         // p2, q2 and p1 are colinear and p1 lies on segment p2q2
    //         if (o3 == 0 &&
    //             //onSegment(p2, p1, q2)
    //             pointBelongsToSegment(p2, q2, p1)
    //         ) return true;
    //
    //         // p2, q2 and q1 are colinear and q1 lies on segment p2q2
    //         if (o4 == 0 &&
    //             //onSegment(p2, q1, q2)
    //             pointBelongsToSegment(p2, q2, q1)
    //         ) return true;
    //
    //         return false; // Doesn't fall in any of the above cases
    //     }
    // }
    //
    // public static boolean isEqualWithTolerance(double d, double v, double t) {
    //     if (d<=v+t && d>=v-t)
    //         return true;
    //     return false;
    // }
    //
    // public static double polygonArea(WayPointList route) {
    //     if (route==null || route.isEmpty())
    //         return Double.NaN;
    //     if (route.get(0).x!=route.get(route.size()-1).x
    //         ||
    //         route.get(0).y!=route.get(route.size()-1).y) return -1; //not a polygon
    //     double area = 0.0;
    //     for (int i = 0; i < route.size()-1; i++) {
    //         area = area + (route.get(i).x * route.get(i+1).y) - (route.get(i).y * route.get(i+1).x);
    //     }
    //     return 0.5 * area;
    // }
    //
    // public static boolean rightAngle(Coordinate l, Coordinate m, Coordinate r) {
    //     double angleTolerance=0.003;
    //     double angle = GeometricFunctions.angleBetween3PointsUsingDifference(l,m,r);
    //     if (DoubleRounder.equalWithTolerance(angle, Math.PI/2,angleTolerance)
    //         || DoubleRounder.equalWithTolerance(angle, 3*Math.PI/2,angleTolerance))
    //     {
    //         return true;
    //     }
    //     return false;
    // }
    //
    // public static List<Coordinate> getCenterOfCircle(Coordinate c1, Coordinate c2, double angle) {
    //     Coordinate c0 = new Coordinate((c1.getX()+c2.getX())/2.0, (c2.getY()+c1.getY())/2.0);
    //     double dAB = cartesianDistance(c1, c2);
    //     double angle2 = (Math.PI - angle)/2.0;
    //     double radius = (dAB/Math.sin(angle))*Math.sin(angle2);
    //     double xa = (c2.getX() - c1.getX())/2.0;
    //     double ya = (c2.getY() - c1.getY())/2.0;
    //     double a = Math.sqrt(xa*xa + ya*ya);
    //     double b = Math.sqrt(radius*radius - a*a);
    //     double x3 = c0.getX() + ((b*ya)/a);
    //     double y3 = c0.getY() - ((b*xa)/a);
    //     Coordinate center1 = new Coordinate(x3, y3);
    //     double x4 = c0.getX() - ((b*ya)/a);
    //     double y4 = c0.getY() + ((b*xa)/a);
    //     Coordinate center2 = new Coordinate(x4, y4);
    //     return Arrays.asList(center1, center2);
    // }
    //
    // public static Coordinate getCenterOfCircle(Coordinate c1, Coordinate c2, double angle, Coordinate ref, Coordinate ref2) {
    //     List<Coordinate> coordinates = getCenterOfCircle(c1, c2, angle);
    //     double distance1 = pointDistanceToLine(ref, ref2, coordinates.get(0));
    //     double distance2 = pointDistanceToLine(ref, ref2, coordinates.get(1));
    //     if(distance1 < distance2)
    //         return coordinates.get(0);
    //     return coordinates.get(1);
    // }
    //
    // //a = start of segment
    // //b = end of segment
    // //c = center of circle
    // //Author: Ahmed Suliman
    // public static List<Coordinate> getPointsOnCircleSegment(Coordinate a, Coordinate b, Coordinate c, int numberOfPoints){
    //     List<Coordinate> points = new ArrayList<Coordinate>();
    //     double radius = cartesianDistance(a, c);
    //     double startAngle = Math.atan2(a.getY()-c.getY(), a.getX()-c.getX());
    //     Coordinate testCoordinate1 = new Coordinate(c.getX() + radius*Math.cos(startAngle), c.getY() + radius*Math.sin(startAngle));
    //     double endAngle =  Math.atan2(b.getY()-c.getY(), b.getX()-c.getX());
    //     Coordinate testCoordinate2 = new Coordinate(c.getX() + radius*Math.cos(endAngle), c.getY() + radius*Math.sin(endAngle));
    //     double angleDiff = endAngle - startAngle;
    //     if(angleDiff > Math.PI)
    //         angleDiff -= 2*Math.PI;
    //     else if(angleDiff < -1.0 * Math.PI)
    //         angleDiff += 2*Math.PI;
    //     double angleIncrement = angleDiff/(numberOfPoints+1);
    //     double tempAngle = startAngle;
    //     for(int i=0;i<numberOfPoints;i++) {
    //         tempAngle += angleIncrement;
    //         double x = c.getX() + radius*Math.cos(tempAngle);
    //         double y = c.getY() + radius*Math.sin(tempAngle);
    //         Coordinate tempCoordinate = new Coordinate(x, y);
    //         points.add(tempCoordinate);
    //     }
    //     //Collections.reverse(points);
    //     return points;
    // }
    //
    //
    // //a => 1st point on first line
    // //b => 2nd point on first line
    // //c => 1st point on second line
    // //returns d => 2nd point on second line
    // public static Coordinate getProjectionOfPointOnParallelLine(Coordinate a, Coordinate b, Coordinate c) {
    //     double slope = getSlope(a, b);
    //     double yIntercept = c.y - slope*c.x;
    //     Coordinate tempD = new Coordinate(0.0, yIntercept);
    //     return getPointProjectionOnLine(tempD, c, b);
    // }
    //
    // //a => 1st point on first line
    // //b => 2nd point on first line
    // //c => 1st point on second line
    // //returns d => 2nd point on second line
    // public static Coordinate getEquidisantPointOnParallelLine(Coordinate a, Coordinate b, Coordinate c) {
    //     double slope = getSlope(a, b);
    //     double slopeAC = getSlope(a,c);
    //     double yIntercept = c.y - slope*c.x;
    //     double distance = cartesianDistance(a, c);
    //     Coordinate d1,d2;
    //     if(Double.isInfinite(yIntercept)) { //vertical
    //         double vx = c.x;
    //         double va = 1.0;
    //         double vb = -2*b.y;
    //         double vc = (b.y*b.y)+((vx-b.x)*(vx-b.x))-(distance*distance);
    //         double sqrtExp = (vb*vb)-(4*va*vc);
    //         if(sqrtExp<0) sqrtExp = 0;
    //         double vy1 = (-vb+Math.sqrt(sqrtExp))/(2*va);
    //         double vy2 = (-vb-Math.sqrt(sqrtExp))/(2*va);
    //
    //         d1 = new Coordinate(vx,vy1);
    //         d2 = new Coordinate(vx,vy2);
    //     }
    //     else {
    //
    //         Coordinate tempD = new Coordinate(0.0, yIntercept);
    //         double va = (slope*slope +1);
    //         double vb = ((2*slope*yIntercept)-(2*slope*b.y)-2*b.x);
    //         double vc = (yIntercept*yIntercept)-(2*b.y*yIntercept)+(b.y*b.y)+(b.x*b.x)-(distance*distance);
    //         double sqrtExp = (vb*vb)-(4*va*vc);
    //         if(sqrtExp<0) sqrtExp = 0;
    //         double vx1 = (-vb+Math.sqrt(sqrtExp))/(2*va);
    //         double vy1 = slope*vx1+yIntercept;
    //         d1 = new Coordinate(vx1, vy1);
    //
    //         double vx2 = (-vb-Math.sqrt(sqrtExp))/(2*va);
    //         double vy2 = slope*vx2+yIntercept;
    //         d2 = new Coordinate(vx2, vy2);
    //         if(Double.isNaN(d1.x) || Double.isNaN(d1.y) || Double.isNaN(d2.x) || Double.isNaN(d2.y)) {
    //             int x=1;
    //         }
    //         if(cartesianDistance(a, d2)>100 || cartesianDistance(a, d1)>100) {
    //             int x = 1;
    //         }
    //
    //     }
    //     double slopeBD1 = getSlope(b,d1);
    //     double absoluteDiff1 = Math.abs(slopeAC-slopeBD1);
    //     double slopeBD2 = getSlope(b, d2);
    //     double absoluteDiff2 = Math.abs(slopeAC-slopeBD2);
    //     if(absoluteDiff2<absoluteDiff1) {
    //         return d2;
    //     }
    //     return d1;
    //     //return getPointProjectionOnLine(tempD, c, b);
    // }
    //
    // //a => 1st point on first line
    // //b => 2nd point on first line
    // //c => 1st point on second line
    // //returns d => 2nd point on second line
    // public static Coordinate getEquidisantPointOnParallelLineSimple(Coordinate a, Coordinate b, Coordinate c) {
    //     double x = b.x+c.x-a.x;
    //     double y = b.y+c.y-a.y;
    //     return new Coordinate(x,y);
    //     //return getPointProjectionOnLine(tempD, c, b);
    // }
    //
    //
    //
    // //same as previous function, but adds sameGradient parameter
    // //There are two possible solutions: sameGradient => true, finds d where slope of (b,d) is closest to (a,c)
    // public static Coordinate getEquidisantPointOnParallelLine(Coordinate a, Coordinate b, Coordinate c, boolean sameGradient) {
    //     double slope = getSlope(a, b);
    //     double slopeAC = getSlope(a,c);
    //     double yIntercept = c.y - slope*c.x;
    //     double distance = cartesianDistance(a, c);
    //     Coordinate d1,d2;
    //     if(Double.isInfinite(yIntercept)) { //vertical
    //         double vx = c.x;
    //         double va = 1.0;
    //         double vb = -2*b.y;
    //         double vc = (b.y*b.y)+((vx-b.x)*(vx-b.x))-(distance*distance);
    //         double sqrtExp = (vb*vb)-(4*va*vc);
    //         if(sqrtExp<0) sqrtExp = 0;
    //         double vy1 = (-vb+Math.sqrt(sqrtExp))/(2*va);
    //         double vy2 = (-vb-Math.sqrt(sqrtExp))/(2*va);
    //
    //         d1 = new Coordinate(vx,vy1);
    //         d2 = new Coordinate(vx,vy2);
    //     }
    //     else {
    //
    //         Coordinate tempD = new Coordinate(0.0, yIntercept);
    //         double va = (slope*slope +1);
    //         double vb = ((2*slope*yIntercept)-(2*slope*b.y)-2*b.x);
    //         double vc = (yIntercept*yIntercept)-(2*b.y*yIntercept)+(b.y*b.y)+(b.x*b.x)-(distance*distance);
    //         double sqrtExp = (vb*vb)-(4*va*vc);
    //         if(sqrtExp<0) sqrtExp = 0;
    //         double vx1 = (-vb+Math.sqrt(sqrtExp))/(2*va);
    //         double vy1 = slope*vx1+yIntercept;
    //         d1 = new Coordinate(vx1, vy1);
    //
    //         double vx2 = (-vb-Math.sqrt(sqrtExp))/(2*va);
    //         double vy2 = slope*vx2+yIntercept;
    //         d2 = new Coordinate(vx2, vy2);
    //         if(Double.isNaN(d1.x) || Double.isNaN(d1.y) || Double.isNaN(d2.x) || Double.isNaN(d2.y)) {
    //             int x=1;
    //         }
    //         if(cartesianDistance(a, d2)>100 || cartesianDistance(a, d1)>100) {
    //             int x = 1;
    //         }
    //
    //     }
    //     double slopeBD1 = getSlope(b,d1);
    //     double absoluteDiff1 = Math.abs(slopeAC-slopeBD1);
    //     double slopeBD2 = getSlope(b, d2);
    //     double absoluteDiff2 = Math.abs(slopeAC-slopeBD2);
    //     if((absoluteDiff2<absoluteDiff1) == sameGradient) {
    //         return d2;
    //     }
    //     return d1;
    //     //return getPointProjectionOnLine(tempD, c, b);
    // }

}
