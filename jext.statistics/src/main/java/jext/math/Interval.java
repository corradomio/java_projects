package jext.math;

import java.util.Iterator;

public class Interval implements Iterable<Double> {

    public static Interval from(double a, double b, double dx) {
        return new Interval(a,b,dx);
    }

    private double a, b, dx;

    public Interval(double a, double b, double dx) {
        this.a = a;
        this.b = b;
        this.dx = dx;
    }

    @Override
    public Iterator<Double> iterator() {
        return new Iterator<Double>() {
            private double x = a;

            @Override
            public boolean hasNext() {
                return x < b;
            }

            @Override
            public Double next() {
                double v = x;
                x += dx;
                return v;
            }
        };
    }
}
