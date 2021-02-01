package jext.math.jna;

import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

@FieldOrder({"real", "imag"})
public class Complex extends Structure implements Structure.ByValue {
    public double real, imag;
}