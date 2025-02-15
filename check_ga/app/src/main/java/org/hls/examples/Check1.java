package org.hls.examples;

import jext.optim.domain.permutation.PermUtils;

public class Check1 {

    public static void main(String[] args) {
        // BitSet bs = new BitSet(7);
        // System.out.println(bs.size());
        // System.out.println(bs.length());
        // System.out.println(bs.cardinality());
        // System.out.println(bs.nbits());

        int[] perm = PermUtils.defaultPerm(9, 1);
        // int[] pr = Utils.random(9, 1, new Random());
        // int[] p1 = Utils.copy(perm);
        // int[] p2 = Utils.swap(perm, 1, 4);
        // int[] p3 = Utils.insert(perm, 1, 4);
        // int[] p4 = Utils.insert(perm, 4, 1);
        // int[] p5 = Utils.shuffle(perm, 1, 4, new Random());
        // int[] p6 = Utils.invert(perm, 1, 4);
        // int[] p7 = Utils.pmx(
        //     new int[]{1,2,3,4,5,6,7,8,9},
        //     new int[]{9,3,7,8,2,6,5,1,4},
        //     3, 6);

        // int[] p8 = Utils.ex(
        //     new int[]{1,2,3,4,5,6,7,8,9},
        //     new int[]{9,3,7,8,2,6,5,1,4}
        // );

        // int[] p9 = Utils.ordx(
        //     new int[]{1,2,3,4,5,6,7,8,9},
        //     new int[]{9,3,7,8,2,6,5,1,4},
        //     3, 6
        // );

        // int[] pa = Utils.cycx(
        //     new int[]{1,2,3,4,5,6,7,8,9},
        //     new int[]{9,3,7,8,2,6,5,1,4}
        // );
        // System.out.println(Arrays.toString(pa));
        // pa = Utils.cycx(
        //     new int[]{0,1,2,3,4,5,6,7,8,9},
        //     new int[]{2,3,0,5,6,7,8,9,4,1}
        // );
        // System.out.println(Arrays.toString(pa));
        // pa = Utils.cycx(
        //     new int[]{2,3,0,5,6,7,8,9,4,1},
        //     new int[]{0,1,2,3,4,5,6,7,8,9}
        // );
        // System.out.println(Arrays.toString(pa));
        // pa = Utils.cycx(
        //     Utils.random(10),
        //     Utils.random(10)
        // );
        // System.out.println(Arrays.toString(pa));
    }
}
