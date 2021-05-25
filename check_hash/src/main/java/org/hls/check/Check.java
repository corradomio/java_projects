package org.hls.check;

import jext.util.LongHash;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Check {

    public static void main(String[] args) {
        String s = "12345678901234567890123456789012345678901234567890";
        System.out.println(s.hashCode());
        // System.out.println(Objects.hashCode(s));
        // System.out.println(Objects.hash(s));
        System.out.println("--");
        System.identityHashCode(s);
        System.out.println(Collections.emptyList().hashCode());
        System.out.println(Objects.hashCode(Collections.emptyList()));
        System.out.println(LongHash.hashCode(Collections.emptyList()));
        System.out.println("--");

        s = "123";
        System.out.println(Objects.hashCode(s));
        System.out.println(LongHash.hashCode(s));
        System.out.println(s.hashCode());

        System.out.println("--");
        int[] v = new int[]{1,2};
        System.out.println(Objects.hash(1,2));
        System.out.println(LongHash.hash(1,2));
        System.out.println(Objects.hashCode(v));
        System.out.println(Arrays.hashCode(v));
        System.out.println(LongHash.hashCode(v));

        System.out.println("--");
        Map<Integer, Integer> m= new HashMap<Integer, Integer>(){{
            put(1,11);
            put(2,22);
        }};
        System.out.println(m.hashCode());
        System.out.println(Objects.hashCode(m.hashCode()));
        System.out.println(LongHash.hashCode(m.hashCode()));

        /*Map<Integer, Integer> */m= new TreeMap<Integer, Integer>(){{
            put(1,11);
            put(2,22);
            put(3,33);
        }};
        System.out.println(m.hashCode());
        System.out.println(Objects.hashCode(m.hashCode()));
        System.out.println(LongHash.hashCode(m.hashCode()));

        System.out.println("--");
        System.out.println(Objects.hash(1, 1., "t"));
        System.out.println(LongHash.hash(1, 1., "t"));
        System.out.println(LongHash.hashCode(1, 1., "t"));
        System.out.println(LongHash.append(LongHash.hashCode(1, 1.), "t"));
    }
}
