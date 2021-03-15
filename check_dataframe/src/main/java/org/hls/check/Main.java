package org.hls.check;

import jext.dataframe.Series;


public class Main {

    public static void main(String[] args) {
        // Series<String,Integer> s1 = Series.create(new int[]{1,2,3,4}, new String[]{"a","b","c", "d"});
        // Series<Integer, Integer> s2 = Series.create(new int[]{1,2,3,4});

        Series<Integer, Integer> s = Series.create(new int[]{ 4,7,-5, 3});

        s.print();
        System.out.println(s);
        System.out.println(s.values());
        System.out.println(s.index());
    }
}
