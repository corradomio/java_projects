package jext.jgrapht.util;

import java.util.Random;

public interface Distrib {

    void setRandom(Random random);

    double nextValue();
}
