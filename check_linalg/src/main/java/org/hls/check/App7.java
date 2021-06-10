package org.hls.check;

import jext.math.linear.Linalg;
import jext.math.linear.Matrix;
import jext.math.linear.Vector;
import jext.math.linear.sparse.Linear;
import jext.math.linear.sparse.SparseMatrix;
import jext.math.linear.sparse.SparseVector;

public class App7 {

    public static void main(String[] args) {

        Matrix mm;
        SparseVector u = Linalg.sparse(5);
        SparseVector v = Linalg.sparse(5);

        u.set(1, 1);
        u.set(2, 1);
        u.set(4, 1);

        v.set(0, 1);
        v.set(2, 1);
        v.set(4, 1);

        System.out.println(u.dot(v));

        // SparseMatrix m = Linalg.sparse(5,5);
        //
        // m.set(0, 1, 1);
        // m.set(1, 3, 1);
        // m.set(2, 4, 1);
        // m.set(3, 0, 1);
        // m.set(4, 2, 1);
        //
        // Linalg.print(m);
        // Linalg.print(m.dot(m));

        v = Linalg.sparse(new float[]{1,0,3,0});

        mm = v.outer(v);
        Linalg.print(mm);
    }
}
