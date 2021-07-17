package org.hls.check;

import com.sun.jna.Native;
import com.sun.jna.Platform;

public class App2 {
    public static void main(String[] args) {
        CMath lib = Native.load(Platform.isWindows()?"msvcrt":"c", CMath.class);
        double result = lib.cosh(.5);
        System.out.println(result);
    }
}
