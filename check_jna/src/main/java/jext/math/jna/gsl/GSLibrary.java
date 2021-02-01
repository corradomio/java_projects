package jext.math.jna.gsl;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface GSLibrary extends Library {
    GSLibrary INSTANCE = (GSLibrary) Native.load("gsl", GSLibrary.class);

    double gsl_log1p (final double x);
    double gsl_expm1 (final double x);
    double gsl_hypot (final double x, final double y);
    double gsl_hypot3 (final double x, final double y, final double z);
    double gsl_acosh (final double x);
    double gsl_asinh (final double x);
    double gsl_atanh (final double x);

    int gsl_isnan (final double x);
    int gsl_isinf (final double x);
    int gsl_finite (final double x);

    double gsl_nan (/*void*/);
    double gsl_posinf (/*void*/);
    double gsl_neginf (/*void*/);
    double gsl_fdiv (final double x, final double y);

    double gsl_coerce_double (final double x);
    float gsl_coerce_float (final float x);
    // long double gsl_coerce_long_double (final long double x);

    double gsl_ldexp(final double x, final int e);
    double gsl_frexp(final double x, int[] e);

    int gsl_fcmp (final double x1, final double x2, final double epsilon);

    // ----------------------------------------------------------------------

    gsl_complex gsl_complex_rect(double x, double y);
    gsl_complex gsl_complex_polar(double ro, double alpha);

    double gsl_complex_arg(gsl_complex z);
    double gsl_complex_abs(gsl_complex z);
    double gsl_complex_abs2(gsl_complex z);
    double gsl_complex_logabs(gsl_complex z);

}
