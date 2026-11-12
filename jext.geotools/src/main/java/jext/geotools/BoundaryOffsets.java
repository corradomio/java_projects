package jext.geotools;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoundaryOffsets {

    final LineString boundary;
    final double[] offsets;
    // final Coordinate[] origins;
    // final Coordinate[] targets;

    final boolean useMax = false;
    final double deltaAngle = 1.0;  // 1 radian

    BoundaryOffsets(LineString boundary) {
        this.boundary = boundary;

        int n = boundary.getNumPoints();
        this.offsets = new double[n];
        // this.origins = new Coordinate[n];
        // this.targets = new Coordinate[n];
    }

    void fillOffsets(double defaultOffset) {
        propagateCollinearOffsets();
        propagateMinMaxOffsets();
        removeOutliers();
        useDefaultOffset(defaultOffset);
    }

    private void useDefaultOffset(double offset) {
        if (offsets[0] == 0)
            Arrays.fill(offsets, offset);
    }

    // Propagate the offset for collinearity
    // If the points are collinear, it is reasonable to suppose the offset is the same
    private void propagateCollinearOffsets() {
        Coordinate[] coords = boundary.getCoordinates();
        int p,i,s, l = offsets.length - 1;
        double minAngle = Math.PI - deltaAngle;

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
            s = ShapeUtils.mod(i+1, l);
            if (!(offsets[i] != 0 && offsets[s] == 0))
                continue;

            p = ShapeUtils.mod(i-1, l);
            // check for collinearity
            if (ShapeUtils.angle(coords[p], coords[i], coords[s]) > minAngle)
                offsets[s] = offsets[i];
        }

        // scan backward
        i = l;
        while(i > 0) {
            i--;
            p = ShapeUtils.mod(i-1, l);
            if (!(offsets[p] == 0 && offsets[i] != 0))
                continue;

            s = ShapeUtils.mod(i+1, l);
            // check for collinearity
            if (ShapeUtils.angle(coords[p], coords[i], coords[s]) > minAngle)
                offsets[p] = offsets[i];
        }

        // align the last point
        offsets[l] = offsets[0];
    }

    // Propagate the max offset
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

        int b = 0;
        int e = 1;

        // if offsets[0] == 0, go backward
        if (offsets[b] == 0) {
            e = 1;
            while (offsets[b] == 0 && b != e) {
                b = ShapeUtils.dec(b,  n);
            }
        }
        // else scan forward
        else {
            e = n-1;
            while(b != e && offsets[b+1] != 0) {
                b = ShapeUtils.inc(b,  n);
            }
        }

        // ALL values are == 0 OR ALL values are != 0
        if (b == e) return;

        // HERE we have: offsets[b] != 0, offsets[b+1] == 0
        while (true) {
            e = ShapeUtils.inc(b,  n);
            while (offsets[e] == 0) {
                e = ShapeUtils.inc(e,  n);
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
            b = ShapeUtils.inc(b, n);
            while (b != e) {
                // offsets[b] = offsets[p] + dd;
                offsets[b] = useOffset;
                // p = b;
                b = ShapeUtils.inc(b, n);
            }

            // advance until the next 0
            e = ShapeUtils.dec(b, n);
            while (offsets[b] != 0 && b != e) {
                b = ShapeUtils.inc(b,  n);
            }

            // there are no zeros
            if (b == e) break;

            // HERE we have offsets[b] == 0 && offsets[b-1] != 0
            b = ShapeUtils.dec(b,  n);
        }

        // align the last point
        offsets[l] = offsets[0];
    }


    //  if offset[i-1] == offset[i+1] && offset[i] != offset[i+1]
    //  then offset[i] = offset[i+1]
    private void removeOutliers() {
        int l = offsets.length-1;
        for (int i=0,j=l-1; i < l; i++) {
            if (offsets[j] == offsets[i+1] && offsets[i] != offsets[i+1]) {
                offsets[i] = offsets[i+1];
            }

            j = i;
        }
        // fill the last element
        offsets[l] =  offsets[0];
    }

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
    public static List<BoundaryOffsets> assignBoundaryOffsets(List<BoundaryIntersections> boundaryIntersections, double offset) {
        return assignBoundaryOffsets(boundaryIntersections, new ExpanderOffsets(offset));
    }

    public static List<BoundaryOffsets> assignBoundaryOffsets(List<BoundaryIntersections> boundariesIntersections, ExpanderOffsets offsets) {
        //List<LineString> expanded = new ArrayList<>();
        List<BoundaryOffsets> boundariesOffsets = new ArrayList<>();

        // 1) for each boundary intersection, fill the offset table
        for (BoundaryIntersections boundaryIntersections : boundariesIntersections) {
            BoundaryOffsets boundaryOffsets = new BoundaryOffsets(boundaryIntersections.boundary);

            int n = boundaryIntersections.lengths.length;
            for (int i=0; i<n; ++i) {
                double length = boundaryIntersections.lengths[i];
                // no intersection
                if (length == 0) continue;

                double offset = offsets.get(length);
                boundaryOffsets.offsets[i] = offset;
            }

            boundariesOffsets.add(boundaryOffsets);
        }

        // 2) for each boundary, complete the offset table
        double defaultOffset = offsets.get();
        for (BoundaryOffsets boundaryOffsets : boundariesOffsets) {
            boundaryOffsets.fillOffsets(defaultOffset);
        }

        return boundariesOffsets;
    }
}
