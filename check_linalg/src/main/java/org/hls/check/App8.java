package org.hls.check;

import org.apache.commons.math3.linear.OpenMapRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealMatrixFormat;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SparseRealMatrix;

public class App8 {

    public static void main(String[] args) {
        RealMatrix m;
        RealVector v;
        SparseRealMatrix sm = new OpenMapRealMatrix(5, 5);
        sm.setEntry(1, 3, 3.0);
        RealMatrixFormat TABLE_FORMAT = new RealMatrixFormat("", "", "", "\n", "", ", ");
        System.out.println(TABLE_FORMAT.format(sm));
    }
}
