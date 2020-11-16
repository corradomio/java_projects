package org.hls.check;

import jext.dataframe.Series;


public class Main {

    public static void main(String[] args) {
        Series<String,Integer> s1 = Series.create(new int[]{1,2,3,4}, new String[]{"a","b","c", "d"});
        Series<Integer, Integer> s2 = Series.create(new int[]{1,2,3,4});
    }
}
