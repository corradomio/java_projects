package jext.jgrapht.util.distrib;

import jext.jgrapht.util.Distrib;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class NormalDistrib implements Distrib {

    private final double mean;
    private final double standardDeviation;
    private double minValue = -Double.MAX_VALUE;
    private double maxValue = +Double.MAX_VALUE;
    private Random rnd = new Random();

    public NormalDistrib() {
        this(0., 1.);
    }

    public NormalDistrib(double m, double sdev) {
        this.mean = m;
        this.standardDeviation = sdev;
    }

    public NormalDistrib random(Random rnd) {
        this.rnd = rnd;
        return this;
    }

    public NormalDistrib minValue(double value) {
        minValue = value;
        return this;
    }

    public NormalDistrib maxValue(double value) {
        maxValue = value;
        return this;
    }

    public NormalDistrib range(double min, double max) {
        minValue = min;
        maxValue = max;
        return this;
    }

    public NormalDistrib centered(double center, double delta) {
        minValue = center-delta;
        maxValue = center+delta;
        return this;
    }

    @Override
    public double nextValue() {
        double value = mean + rnd.nextGaussian()*standardDeviation;
        if (value < minValue)
            value = minValue;
        if (value > maxValue)
            value = maxValue;
        return value;
    }

}
