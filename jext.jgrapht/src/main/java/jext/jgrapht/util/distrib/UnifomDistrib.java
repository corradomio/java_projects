package jext.jgrapht.util.distrib;

import jext.jgrapht.util.Distrib;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class UnifomDistrib implements Distrib {

    private double min;
    private double delta;
    private Random rnd = new Random();

    public UnifomDistrib() {
        this(0., 1.);
    }

    public UnifomDistrib(double min, double max) {
        this.min = min;
        this.delta = max-min;
    }

    public UnifomDistrib random(Random rnd) {
        this.rnd = rnd;
        return this;
    }

    public UnifomDistrib range(double min, double max) {
        this.min = min;
        this.delta = max-min;
        return this;
    }

    public UnifomDistrib centered(double center, double delta) {
        this.min = center-delta;
        this.delta = 2*delta;
        return this;
    }

    @Override
    public double nextValue() {
        return min + rnd.nextDouble()*delta;
    }
}
