package jext.jgrapht.util;

import java.util.Random;

public interface Distrib {

    Distrib random(Random random);

    double mean();

    double sdev();

    double nextValue();
}
