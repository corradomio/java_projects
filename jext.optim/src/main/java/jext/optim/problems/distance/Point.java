package jext.optim.problems.distance;

import jext.optim.problems.Coords;

import java.text.DecimalFormat;

public class Point implements Coords {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.######");

    public final int id;
    public final double x;
    public final double y;
    public final double z;
    public final int ndims;

    public Point() {
        this.id = 0;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.ndims = 0;
    }

    public Point(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = 0;
        this.ndims = 2;
    }

    public Point(int id, double x, double y, double z) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.ndims = 3;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        String fx = DECIMAL_FORMAT.format(x);
        String fy = DECIMAL_FORMAT.format(y);
        String fz = DECIMAL_FORMAT.format(z);

        if (ndims == 2)
            return String.format("%d %s %s", id, fx, fy);
        else
            return String.format("%d %s %s %s", id, fx, fy, fz);
    }

}
