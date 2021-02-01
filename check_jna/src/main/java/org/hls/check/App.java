package org.hls.check;

import jext.math.jna.gsl.gsl_complex;
import jext.math.jna.gsl.GSLibrary;

public class App {
    public static void main(String[] args) {
        GSLibrary gsl = GSLibrary.INSTANCE;

        System.out.println(gsl.gsl_fcmp(0.003, 0.002, 0.01));

        gsl_complex c = gsl.gsl_complex_rect(11,22);
        System.out.printf("(%f,%f)\n", c.real, c.imag);

        c = gsl.gsl_complex_polar(1, Math.PI/4);
        System.out.printf("(%f,%f)\n", c.real, c.imag);
        System.out.println(gsl.gsl_complex_arg(c) - Math.PI/4);

    }
}
