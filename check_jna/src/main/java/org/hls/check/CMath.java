package org.hls.check;

import com.sun.jna.Library;

public interface CMath extends Library {
    double cosh(double value);
}