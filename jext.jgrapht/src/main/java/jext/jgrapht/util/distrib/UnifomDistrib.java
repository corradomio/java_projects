package jext.jgrapht.util.distrib;

import jext.jgrapht.util.Distrib;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class UnifomDistrib implements Distrib {

    private final double min;
    private final double delta;
    private Random rnd = new Random();

    public UnifomDistrib() {
        this(0., 1.);
    }

    public UnifomDistrib(double min, double max) {
        this.min = min;
        this.delta = max-min;
    }

    public void setRandom(Random rnd) {
        this.rnd = rnd;
    }

    @Override
    public double nextValue() {
        return min + rnd.nextDouble()*delta;
    }
}
