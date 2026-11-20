package jext.geotools;

import jext.util.Assert;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Compute the boundary offsets based on the intersection distance
 * with other boundaries. If for some segments there is no intersections
 * some heuristic rules are applied:
 * .
 *      1) all collinear segment must have the same offset
 *      2) the not collinear segments will are initialized with
 *         the smaller offset
 * .
 * It is also applied a cleanup:
 * .
 *      - if offset[i-1] == offset[i+1]
 *        and offset[i] != offset[i+1]
 *        then offset[i] <- offset[i+1]
 *
 */
public class BoundaryOffsets {

    final LineString boundary;
    final double[] offsets;

    final boolean useMax = false;

    BoundaryOffsets(LineString boundary) {
        this.boundary = boundary;

        int n = boundary.getNumPoints();
        this.offsets = new double[n];
    }

    void initializeOffsets(BoundaryIntersections boundaryIntersections, ExpanderOffsets offsets) {

        int n = boundaryIntersections.lengths.length;
        for (int i=0; i<n; ++i) {
            double length = boundaryIntersections.lengths[i];
            // no intersection
            if (length == 0) continue;

            double offset = offsets.get(length);
            this.offsets[i] = offset;
        }

    }

    void fillOffsets(double defaultOffset, double maxOffsetCollinearAngle) {
        // propagateCollinearOffsetsMiddle(maxOffsetCollinearAngle);
        propagateCollinearOffsetsHeadTail(maxOffsetCollinearAngle);
        propagateMinMaxOffsets();
        useDefaultOffset(defaultOffset);
        // removeOutliers41();
        // removeOutliers4();
        removeOutliers3();
    }

    void fixChamferOffsets(double minChamferAngle) {
        Coordinate[] coords = boundary.getCoordinates();
        int l = coords.length - 1;
        double maxInternalChamferAngle = Math.PI - minChamferAngle;
        // double maxInternalChamferAngle = Math.PI - minChamferAngle;
        if (minChamferAngle == 0)
            return;

        for(int i=0; i<l; ++i) {
            int p = Op.dec(i, l);       // prevous
            int s = Op.inc(i, l);       // successive

            double angle = ShapeUtils.internalAngle(coords[p], coords[i], coords[s]);
            if (angle > maxInternalChamferAngle)
                continue;

            int o = Op.dec(p, l);

            if (offsets[o] != offsets[p])
                offsets[p] = offsets[o];

            if (offsets[i] != offsets[s])
                offsets[i] = offsets[s];
        }
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private void useDefaultOffset(double offset) {
        if (offsets[0] == 0)
            Arrays.fill(offsets, offset);
    }

    // Propagate the offset for collinearity
    // If the points are collinear, it is reasonable to suppose the offset is the same
    private void propagateCollinearOffsetsMiddle(double maxOffsetCollinearAngle) {
        Coordinate[] coords = boundary.getCoordinates();
        int p,i,s, l = offsets.length - 1;

        if (ShapeUtils.id(boundary). equals("7804:17:0"))
            Assert.nop();

        // implementation
        // scan forward
        //  if offsets[i] != 0 and offsets[i+1] == 0
        //      if points [i-1, i, i+1] are collinear
        //          offsets[i+1] = offsets[i]
        //
        // scan backward
        //  if offsets[i] != 0 and offsets[i-1] == 0
        //       if points [i-1, i, i+1] are collinear
        //          offsets[i-1] = offsets[i]

        // scan forward
        i = -1;
        while (i < l) {
            i++;
            s = Op.inc(i, l);   // succesive/next
            p = Op.dec(i, l);   // previous

            if (offsets[i] == 0) continue;
            if (offsets[s] != 0) continue;

            // HERE offsets[i] != 0 & offsets[i+1] == 0
            double collinearAngle = ShapeUtils.collinearAngle(coords[p], coords[i], coords[s]);

            // check for collinearity
            if (collinearAngle <= maxOffsetCollinearAngle)
                offsets[s] = offsets[i];
        }

        // scan backward
        i = l;
        while(i > 0) {
            i--;
            p = Op.dec(i, l);   // previous
            s = Op.inc(i, l);   // succesive/next

            if (offsets[i] == 0) continue;
            if (offsets[p] != 0) continue;

            // HERE offsets[i] != 0 & offsets[i-1] == 0
            double collinearAngle = ShapeUtils.collinearAngle(coords[p], coords[i], coords[s]);

            // check for collinearity
            if (collinearAngle <= maxOffsetCollinearAngle)
                offsets[p] = offsets[i];
        }

        // align the last point
        offsets[l] = offsets[0];
    }

    // propagate the max offset
    // If there are missing offsets, it is reasonable to suppose this happen because
    // the intersection point is too far, not because the intersection angle is
    // different of 90 degrees.
    // In this case, it is propagated the min/max offset
    private void propagateMinMaxOffsets() {
        int n = offsets.length;
        int l = offsets.length - 1;

        // implementation:
        // 0.1) the array MUST have at minimum ONE value NOT ZERO
        // 0.2) if the array doesn't contain zeros, DONE
        //
        // 1) start from index 0
        // 2) if offsets[0] == 0, go backward until it found the
        //    first value not 0
        //    if offset[0] != 0, go forward until the next value is zero
        // 3) from the first 0, advance until the next value is NOT zero
        // 4) now we have [start] is NOT zero, [end] is NOT zero
        //    values in range [start+1 : end-1] are zero
        // 5) compute 'delta = offsets[end] - offsets[start]'
        //    compute 'deltaOffset = delta/n_zeros
        // 6) fill the zero elements with 'prev_offset + deltaOffset'
        //
        // repeat until all elements of 'offsets' are filled

        if (ShapeUtils.id(boundary). equals("7804:17:0"))
            Assert.nop();

        int b = 0;
        int e = 1;

        // if offsets[0] == 0, go backward
        if (offsets[b] == 0) {
            e = 1;
            while (offsets[b] == 0 && b != e) {
                b = Op.dec(b,  n);
            }
        }
        // else scan forward
        else {
            e = n-1;
            while(b != e && offsets[b+1] != 0) {
                b = Op.inc(b,  n);
            }
        }

        // ALL values are == 0 OR ALL values are != 0
        if (b == e) return;

        // HERE we have: offsets[b] != 0, offsets[b+1] == 0
        while (true) {
            e = Op.inc(b,  n);
            while (offsets[e] == 0) {
                e = Op.inc(e,  n);
            }

            // HERE we have:
            //      offsets[b] != 0 && offsets[e] != 0
            //      offsets[b+1 : e-1] == 0

            //double delta = offsets[e] - offsets[b];
            double useOffset = offsets[e];
            if (useMax && offsets[b] > offsets[e]) {
                useOffset = offsets[b];
            }
            else if (!useMax && offsets[b] < offsets[e]) {
                useOffset = offsets[b];
            }
            else {
                useOffset = offsets[e];
            }

            // int nz = ShapeUtils.steps(b, e, n);
            // double dd = delta/ nz;

            // int p = b;
            b = Op.inc(b, n);
            while (b != e) {
                // offsets[b] = offsets[p] + dd;
                offsets[b] = useOffset;
                // p = b;
                b = Op.inc(b, n);
            }

            // advance until the next 0
            e = Op.dec(b, n);
            while (offsets[b] != 0 && b != e) {
                b = Op.inc(b,  n);
            }

            // there are no zeros
            if (b == e) break;

            // HERE we have offsets[b] == 0 && offsets[b-1] != 0
            b = Op.dec(b,  n);
        }

        // align the last point
        offsets[l] = offsets[0];
    }

    private void propagateCollinearOffsetsHeadTail(double maxOffsetCollinearAngle) {
        Coordinate[] coords = boundary.getCoordinates();
        int l = offsets.length - 1;

        if (ShapeUtils.id(boundary). equals("2536:30:0"))
            Assert.nop();

        // scan forward
        for (int i=0; i<l; ++i) {
            int j = Op.inc(i, l);
            int k = Op.inc(j, l);

            double collinearAngle = ShapeUtils.collinearAngle(coords[i], coords[j], coords[k]);
            if (collinearAngle > maxOffsetCollinearAngle)
                continue;
            else if (offsets[i] == offsets[j] && offsets[j] == offsets[k])
                continue;
            else if (offsets[i] == 0 && offsets[j] != 0)
                offsets[i] = offsets[j];
            else if (offsets[i] != 0 && offsets[j] == 0)
                offsets[j] = offsets[i];
        }

        // scan backward
        for(int i=l-1; i>=0; --i) {
            int j = Op.dec(i, l);
            int k = Op.dec(j, l);

            double collinearAngle = ShapeUtils.collinearAngle(coords[k], coords[j], coords[i]);
            if (collinearAngle > maxOffsetCollinearAngle)
                continue;
            else if (offsets[i] == offsets[j] && offsets[j] == offsets[k])
                continue;
            else if (offsets[i] == 0 && offsets[j] != 0)
                offsets[i] = offsets[j];
            else if (offsets[i] != 0 && offsets[j] == 0)
                offsets[j] = offsets[i];
        }
    }

    // if offset[i-1] == offset[i+1] && offset[i] != offset[i+1]
    // then
    //    offset[i] = offset[i+1]
    private void removeOutliers3() {
        boolean updated = true;
        while (updated) {
            updated = false;

            int l = offsets.length - 1;
            for (int i = 0; i < l; i++) {
                int p = Op.dec(i, l);
                int s = Op.inc(i, l);
                if (offsets[p] == offsets[s] && offsets[i] != offsets[s]) {
                    offsets[i] = offsets[s];
                    updated = true;
                }
            }
            // fill the last element
            offsets[l] = offsets[0];
        }
    }

    // if offset[i-1] == offsets[i+2] && offset[i] != offset[i+1] && offset[i+1] != offset[i+2]
    // then
    //   offset[i]   = offset[i+2]
    //   offset[i+1] = offset[i+2]
    private void removeOutliers4() {
        boolean updated = true;
        while (updated) {
            updated = false;

            int l = offsets.length - 1;
            for (int i = 0; i < l; i++) {
                int p = Op.dec(i, l);
                int s = Op.inc(i, l);
                int t = Op.inc(s, l);

                if (offsets[p] == offsets[t] && offsets[p] != offsets[i] && offsets[s] != offsets[t] &&
                    offsets[i] != offsets[s]) {
                    offsets[i] = offsets[t];
                    offsets[s] = offsets[t];
                    updated = true;
                }
            }
        }
    }

    // if outlier[i-1] == outlier[i+1] && outlier[i+1] != outlier[i+2] &&
    //    outlier[i] != outlier[i+2]
    // then
    //    outlier[i] = outlier[i-1]
    private void removeOutliers41() {
        boolean updated = true;
        while (updated) {
            updated = false;

            int l = offsets.length - 1;
            for (int i = 0; i < l; i++) {
                int p = Op.dec(i, l);
                int s = Op.inc(i, l);
                int t = Op.inc(s, l);

                if (offsets[p] == offsets[s] && offsets[s] != offsets[t] && offsets[i] != offsets[t]) {
                    offsets[s] = offsets[i];
                    updated = true;
                }
            }
        }
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    // used to plot the ticks
    public List<LineString> ticks() {
        Coordinate[] coords = boundary.getCoordinates();
        int l =  offsets.length - 1;
        List<LineString> ticks = new ArrayList<>();

        for (int i=0; i<l; ++i) {
            int j = (i+1)%l;
            double offset = offsets[i];
            Coordinate ci = coords[i];
            Coordinate cj = coords[j];
            Coordinate dir = ShapeUtils.outward(ci, cj);

            Coordinate ti = ShapeUtils.lin(ci, offset, dir);
            Coordinate tj = ShapeUtils.lin(cj, offset, dir);
            ticks.add(boundary.getFactory().createLineString(new Coordinate[]{ci, ti}));
            ticks.add(boundary.getFactory().createLineString(new Coordinate[]{cj, tj}));
        }

        return ticks;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------
    /**
     * Expand all boundaries of the same offset (in meters)
     *
     * @param boundaryIntersections list of boundaries to expand
     * @param offset offset, in meters
     * @return
     */
    public static List<BoundaryOffsets> assignBoundaryOffsets(
        List<BoundaryIntersections> boundaryIntersections,
        double offset,
        double maxOffsetCollinearAngle, double minChamferAngle
    ) {
        return assignBoundaryOffsets(boundaryIntersections, new ExpanderOffsets(offset),
            maxOffsetCollinearAngle, minChamferAngle);
    }

    public static List<BoundaryOffsets> assignBoundaryOffsets(
        List<BoundaryIntersections> boundariesIntersections,
        ExpanderOffsets offsets,
        double maxOffsetCollinearAngle, double minChamferAngle
    ) {
        //List<LineString> expanded = new ArrayList<>();
        List<BoundaryOffsets> boundariesOffsets = new ArrayList<>();

        // 1) for each boundary intersection, fill the offset table
        for (BoundaryIntersections boundaryIntersections : boundariesIntersections) {
            BoundaryOffsets boundaryOffsets = new BoundaryOffsets(boundaryIntersections.boundary);

            boundaryOffsets.initializeOffsets(boundaryIntersections, offsets);

            boundariesOffsets.add(boundaryOffsets);
        }

        // 2) for each boundary, complete the offset table
        double defaultOffset = offsets.get();
        for (BoundaryOffsets boundaryOffsets : boundariesOffsets) {
            boundaryOffsets.fillOffsets(defaultOffset, maxOffsetCollinearAngle);
        }

        // 3) for each chamfer, correct the offset table
        for (BoundaryOffsets boundaryOffsets : boundariesOffsets) {
            boundaryOffsets.fixChamferOffsets(minChamferAngle);
        }

        return boundariesOffsets;
    }
}
