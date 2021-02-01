package jext.math.jna.gsl;

import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

@FieldOrder({"real", "imag"})
public class gsl_complex extends Structure implements Structure.ByValue {
    public double real, imag;
}