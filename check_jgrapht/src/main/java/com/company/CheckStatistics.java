package com.company;

import jext.jgrapht.util.Statistics;

public class CheckStatistics {

    public static void main(String[] args) {
        Statistics s = new Statistics();
        s.add(600).add(470).add(170).add(430).add(300).finish();
        s.print();
    }
}
