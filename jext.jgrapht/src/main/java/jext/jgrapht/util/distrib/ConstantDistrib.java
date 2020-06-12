package jext.jgrapht.util.distrib;

import jext.jgrapht.util.Distrib;

import java.util.Random;

public class ConstantDistrib implements Distrib {

    private double p;

    public ConstantDistrib() {
        this.p = 1.;
    }

    public ConstantDistrib(double p) {
        this.p = p;
    }

    public void setRandom(Random rnd) {

    }

    @Override
    public double nextValue() {
        return p;
    }
}
