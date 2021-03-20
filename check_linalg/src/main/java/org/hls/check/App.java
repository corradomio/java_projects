package org.hls.check;

import jext.math.linalg.Matrices;
import jext.math.linalg.Matrix;
import jext.math.linalg.Vector;
import jext.math.linalg.Vectors;

public class App {

    public static void main(String[] args) {

        Vector v = Vectors.constant(10, 2);
        // System.out.println(v);

        Matrix id = Matrices.identity(10);
        Matrix diag = Matrices.diagonal(10);
        Matrix m = Matrices.constant(10, 3);

        // System.out.println(id);
        // System.out.println(diag);
        // System.out.println(m);

        System.out.println(id.dot(m));
        System.out.println(id.dot(id));
        System.out.println(id.dot(diag));
    }
}
