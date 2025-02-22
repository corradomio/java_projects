package com.example;

import jext.util.MapUtils;
import jext.util.concurrent.Parallel;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CheckMap {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> lres = Parallel.map(list, x -> x * x * x);
        System.out.println(lres);

        Map<Integer, Integer> dict = MapUtils.asMap(1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9);

        Map<Integer, Integer> mres = Parallel.map(dict, x -> x * x * x);

        System.out.println(mres);

        Parallel.shutdown();
    }
}
