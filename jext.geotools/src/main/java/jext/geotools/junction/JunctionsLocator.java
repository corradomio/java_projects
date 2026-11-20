package jext.geotools.junction;

import jext.apache.logging.log4j.Logger;
import jext.geotools.Op;
import jext.geotools.ShapeUtils;
import jext.util.Assert;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineString;

import java.util.ArrayList;
import java.util.List;

/**
 * On the expanded boundary it is necessaryto identify the locations where to create
 * the junction boxes.
 * There are 2 rules:
 * .
 *      1) at 5m of a chamfer, at the both sides
 *      2) each 300m between two chamfers
 * .
 * There are several cases to consider
 * .
 *      1) if the distance between adjacent chamfers is < 2*5m, nothing is added
 *      2) if the distance between adjacent chamfers is < 3*5m, only 1 junction at the center is added
 *      3)
 *
 */
public class JunctionsLocator {

    private static Logger logger = Logger.getLogger(JunctionsLocator.class);

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public static List<BoundaryJunctions> findJunctionLocations(
        List<BoundaryChamfers> boundariesChamfers,
        double chamferJunctionDistance,
        double junctionDistance
    ) {
        List<BoundaryJunctions> junctionsLocations = new ArrayList<>();

        for(BoundaryChamfers boundaryChamfers : boundariesChamfers) {
            BoundaryJunctions boundaryJunctions = findJunctionLocations(
                boundaryChamfers, chamferJunctionDistance, junctionDistance
            );

            junctionsLocations.add(boundaryJunctions);
        }
        return junctionsLocations;
    }

    /**
     * Find the locations where to place the junctions. Steps:
     * .
     *  1) find where the chamfer are located
     *  2) identify the junction locations near the chamfers
     *  3) identify the junction locations between the chamfers
     *
     * @return
     */
    public static BoundaryJunctions findJunctionLocations(
        BoundaryChamfers boundaryChamfers,
        double chamferJunctionDistance, double junctionDistance
    ) {
        JunctionsLocator jl = new JunctionsLocator(chamferJunctionDistance, junctionDistance);

        List<Junction> nearJunctions = jl.locateNearJunctions(boundaryChamfers.chamfers);

        List<Junction> farJunctions = jl.locateFarJunctions(boundaryChamfers.chamfers);

        List<Junction> allJunctions = new ArrayList<>(nearJunctions);
        allJunctions.addAll(farJunctions);

        LineString expandedWithJunctions = splitOnJunctions(boundaryChamfers.expanded, allJunctions);

        boolean ec = ShapeUtils.isValidOrder(boundaryChamfers.expanded);
        boolean jc = ShapeUtils.isValidOrder(expandedWithJunctions);

        return new BoundaryJunctions(
            boundaryChamfers,
            nearJunctions,
            farJunctions,
            expandedWithJunctions
        );
    }

    private static LineString splitOnJunctions(LineString expanded, List<Junction> junctions) {
        // WARNING: the SAME segment of expanded could be subdivided in MULTIPLE segments
        Coordinate[] coords = expanded.getCoordinates();
        int l = coords.length - 1;

        // create the list junctions for each segment
        List<Junction>[] jsegments = new List[l];
        for (int i=0; i<l; ++i) {
            jsegments[i] = new ArrayList<>();
            jsegments[i].add(new Junction(expanded, i, 0, coords[i]));
        }

        // fill the lists
        for (Junction j : junctions) {
            jsegments[j.index].add(j);
        }

        // sort the lists by offset
        for (int i=0; i<l; ++i) {
            jsegments[i] = jsegments[i].stream().sorted((o1, o2) -> {
                double diff = o1.offset - o2.offset;
                // 0 NEVER HAPPENS
                if (diff == 0) return 0;
                return diff < 0 ? -1 : 1;
            }).toList();
        }

        // create the new list of coordinates
        List<Coordinate> jcoords = new ArrayList<>();
        for (int i=0; i<l; ++i) {
            for (Junction j : jsegments[i])
                jcoords.add(j.coordinate);
        }
        // add last point
        if (jcoords.size() > 0)
            jcoords.add(jcoords.get(0));

        // convert in coords
        coords = jcoords.toArray(new Coordinate[0]);

        return expanded.getFactory().createLineString(coords);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private final double chamferJunctionDistance;
    private final double junctionDistance;

    public JunctionsLocator(double chamferJunctionDistance, double junctionDistance
    ) {
        this.chamferJunctionDistance = chamferJunctionDistance;
        this.junctionDistance = junctionDistance;
    }

    // A chamfer is a boundary vertex  where
    private List<Junction> locateNearJunctions(List<Chamfer> chamfers) {
        List<Junction> nearJunctions = new ArrayList<>();

        // things to check:
        //  1) IF the distance between the CURRENT chamfer and the NEXT one
        //      is LESS than 2*near_distance -> NO JUNCTION
        //  2) IF the distance between the CURRENT chamfer and the NEXT one
        //      is LESS than 3*near_distance -> A SINGLE JUNCTION at the center
        double minJunctionDistance = 2*chamferJunctionDistance;
        double oneJunctionDistance = 3*chamferJunctionDistance;
        int n = chamfers.size();
        Junction junction;

        // WARNING:
        //  in boundary, the vertex representing the chamfer is 'index'
        //  BUT
        //  in expanded, the edge is [expandedIndex == (2*index), expandedIndex+1]

        // scan forward
        for (int i=0; i<n; ++i) {
            int s = Op.inc(i, n);
            Chamfer ci = chamfers.get(i);
            Chamfer cs = chamfers.get(s);

            double chamfersDistance = ci.distance(cs);

            // if the distance is less than 2*near_distance
            if (chamfersDistance  < minJunctionDistance)
                continue;
            // if the distance is less than 3*near_distance
            if (chamfersDistance < oneJunctionDistance) {
                // add a single junction at the center

                junction = junctionAt(ci, oneJunctionDistance/2);
            }
            // otherwise
            else {
                junction = junctionAt(chamfers.get(i), chamferJunctionDistance);
            }

            nearJunctions.add(junction);
        }

        // scan backward
        for (int i=0; i<n; ++i) {
            int p = Op.dec(i, n);
            Chamfer cp = chamfers.get(p);
            Chamfer ci = chamfers.get(i);

            double chamfersDistance = cp.distance(ci);

            // if the distance is less than 2*near_distance
            if (chamfersDistance  < minJunctionDistance)
                continue;
            // if the distance is less than 3*near_distance
            if (chamfersDistance < oneJunctionDistance) {
                junction = junctionAt(cp, oneJunctionDistance/2);
            }
            // otherwise
            else {
                junction = junctionAt(cp, chamfersDistance - chamferJunctionDistance);
            }

            nearJunctions.add(junction);
        }

        return nearJunctions;
    }

    private List<Junction> locateFarJunctions(List<Chamfer> chamfers) {
        List<Junction> farJunctions = new ArrayList<>();

        // things to check:
        //  1) IF the distance between the CURRENT chamfer and the NEXT one
        //      is LESS than 2*near_distance -> NO JUNCTION
        //  2) IF the distance between the CURRENT chamfer and the NEXT one
        //      is LESS than 3*near_distance -> A SINGLE JUNCTION at the center
        double minJunctionDistance = 2*junctionDistance;
        double oneJunctionDistance = 3*junctionDistance;
        int n = chamfers.size();
        Junction junction;

        // WARNING:
        //  in boundary, the vertex representing the chamfer is 'index'
        //  INSTEAD
        //  in expanded, the edge is [expandedIndex == (2*index), expandedIndex+1]

        // scan forward
        for (int i=0; i<n; ++i) {
            int s = Op.inc(i, n);
            Chamfer ci = chamfers.get(i);
            Chamfer cs = chamfers.get(s);

            double chamfersDistance = ci.distance(cs);

            // if the distance is less than 2*near_distance
            if (chamfersDistance  < minJunctionDistance)
                continue;
            // if the distance is less than 3*near_distance
            if (chamfersDistance < oneJunctionDistance) {
                // add a single junction at the center
                junction = junctionAt(chamfers.get(i), chamfersDistance/2);
                farJunctions.add(junction);
                continue;
            }

            // compute how many junctions to create
            int nf = (int)(chamfersDistance/junctionDistance);
            // compute the offset from the first chamfer
            double startOffset = (chamfersDistance - nf*junctionDistance)/2;
            double junctionOffset = startOffset;
            // check the far junctions not too near the chamfers
            if (startOffset < junctionDistance/2) {
                nf -= 1;
                startOffset = (chamfersDistance - nf*junctionDistance)/2;
                junctionOffset = startOffset;
            }

            for (int f=0; f<nf;  ++f) {
                junction = junctionAt(ci, junctionOffset);
                junctionOffset += junctionDistance;

                farJunctions.add(junction);
            }
            // add the last point
            {
                junction = junctionAt(ci, junctionOffset);
                farJunctions.add(junction);
            }
            junctionOffset += startOffset;
            Assert.verify(Math.abs(chamfersDistance - junctionOffset) < chamferJunctionDistance, "Far junctions not correctly distributed");
        }

        return farJunctions;
    }

    private static Junction junctionAt(Chamfer chamfer, double offset) {
        // let the expanded boundary, the index on the expanded boundary where the chamfer is located
        // (the chamfer is [i-1, i]), to advance of offset meters and to identify which segment
        // contains the location of the junction
        LineString expanded = chamfer.expanded;
        Coordinate[] coords = expanded.getCoordinates();
        int l = coords.length - 1;
        int origin = Op.inc(chamfer.expandedIndex, l);
        int target = 0;
        double rest = offset;

        while(true) {
            target = Op.inc(origin, l);
            double segmentDistance = ShapeUtils.distance(coords, origin, target);
            if (segmentDistance >= rest)
                break;

            origin = target;
            rest -= segmentDistance;
        }

        // compute the coordinates of the point
        Coordinate location = ShapeUtils.between(coords[origin], rest, coords[target]);

        return new Junction(
            expanded,
            origin,
            rest,
            location
        );
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public static List<LineString> expandedWithJunctions(List<BoundaryJunctions> boundariesJunctions) {
        return boundariesJunctions.stream().map(bj -> bj.expandedWithJunctions).toList();
    }
}
