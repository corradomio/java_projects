package org.hls.check;

import java.util.Arrays;
import java.util.List;

public class CheckArray {

    public static void main(String[] args) {
        Object ba = new boolean[1];
        Object Ba = new Boolean[1];
        Object Bl = Arrays.asList(false);
        System.out.println();
        System.out.println(ba.getClass());
        System.out.println(Ba.getClass());
        System.out.println(Bl.getClass());
        System.out.println();
        System.out.println(ba instanceof boolean[]);
        System.out.println(ba instanceof Boolean[]);
        System.out.println(ba instanceof List);
        System.out.println();
        System.out.println(Ba instanceof boolean[]);
        System.out.println(Ba instanceof Boolean[]);
        System.out.println(Ba instanceof List);
        System.out.println();
        System.out.println(Bl instanceof boolean[]);
        System.out.println(Bl instanceof Boolean[]);
        System.out.println(Bl instanceof List);
    }
}
