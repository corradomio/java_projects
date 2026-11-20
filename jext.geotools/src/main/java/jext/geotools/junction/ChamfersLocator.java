package jext.geotools.junction;

import jext.apache.logging.log4j.Logger;
import jext.geotools.BoundaryExpander;
import jext.geotools.Op;
import jext.geotools.ShapeUtils;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineString;

import java.util.ArrayList;
import java.util.List;

public class ChamfersLocator {

    private static Logger logger = Logger.getLogger(ChamfersLocator.class);

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public static List<BoundaryChamfers> findChamferLocations(
        List<BoundaryExpander> expandedBoundaries,
        double minChamferAngle
    ) {
        List<BoundaryChamfers> allChamferLocations = new ArrayList<>();

        for(BoundaryExpander expandedBoundary : expandedBoundaries) {
            BoundaryChamfers chamferLocations = findChamferLocations(expandedBoundary, minChamferAngle);

            allChamferLocations.add(chamferLocations);
        }
        return allChamferLocations;
    }


    public static BoundaryChamfers findChamferLocations(BoundaryExpander expandedBoundary, double minChamferAngle) {
        LineString boundary = expandedBoundary.boundary;
        Coordinate[] coords = boundary.getCoordinates();
        int l = coords.length - 1;      // coords[0] == coords[n-1]
        List<Chamfer> chamfers = new ArrayList<>();
        double maxInternalChamferAngle = Math.PI - minChamferAngle;

        for(int i=0; i<l; ++i) {
            int p = Op.dec(i, l);       // prevous
            int s = Op.inc(i, l);       // successive

            double angle = ShapeUtils.internalAngle(coords[p], coords[i], coords[s]);
            if (angle > maxInternalChamferAngle)
                continue;

            // logger.debugf("... [%d,%d,%d] %.2f [chamfer]", p,i,s, Math.toDegrees(angle));
            chamfers.add(new Chamfer(
                boundary,
                i,
                expandedBoundary.expanded,
                i+i
            ));
        }

        // IF, for some reason, the boundary has < 3 chamfers, the boundary is too smooth
        return new BoundaryChamfers(
            expandedBoundary,
            chamfers
        );
    }
}
