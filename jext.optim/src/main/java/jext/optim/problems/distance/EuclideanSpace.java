package jext.optim.problems.distance;

import jext.optim.problems.Coords;

import java.util.List;

import static jext.util.MathUtils.sq;
import static java.lang.Math.sqrt;


public class EuclideanSpace extends MetricSpace{

    public EuclideanSpace(Coords[] points) {
        super(points);
    }

    public EuclideanSpace(List<? extends Coords> points) {
        super(points);
    }

    @Override
    protected double distance(Coords li, Coords lj) {
        return sqrt(sq(li.getX() - lj.getX()) + sq(li.getY() - lj.getY()));
    }

}
