package org.hls.check;


import jext.math.linear.Matrices;
import jext.math.linear.Matrix;
import jext.math.linear.Vector;
import jext.math.linear.Vectors;

public class App {

    public static void main(String[] args) {

        Vector u = Vectors.ones(10);
        Vector v = Vectors.ones(10);

        Vector r = u.linear(2, 3, v);
        System.out.println(r);

        Matrix m = Matrices.zeros(2,3);
        System.out.println(m);

        Matrix i = Matrices.identity(3);
        System.out.println(i);


        // Random rnd = new Random();
        // rnd.setSeed(System.currentTimeMillis());
        //
        // System.out.println(Linear.toString(Linear.ivect(3), "%.3f"));
        // System.out.println(Linear.toString(Linear.imatrix(3, 4), 4, "%.3f"));
        // System.out.println(Linear.toString(Linear.diagonal(3, 4), 3, "%.3f"));

        // float[] v = Linear.vect(3, 2);
        // float[] o = Linear.vect(3, 1);
        // float[] r = Linear.vect(3);
        // float[] I = Linear.identity(3);
        // System.out.println(Linear.dot(v, v));
        // Linear.dot(r, o, I, o);
        // System.out.println(Linear.dot(r, r));

        // float[] v = Linear.vect(4, 1);
        // float[] A = Linear.matrix(3,4, 2);
        // float[] D = Linear.diagonal(3, 3);
        //
        // System.out.println("  " + Linear.toString(v, "%.3f"));
        // System.out.println(Linear.toString(A, 3, "%.3f"));
        // System.out.println(Linear.toString(D, 3, "%.3f"));
    }
}
