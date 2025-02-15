package jext.problems.dist;

import jext.problems.Coords;

import java.util.List;

import static jext.util.MathUtils.sqrt;
import static jext.util.MathUtils.sq;


public class EuclideanSpace extends MetricSpace{

    public EuclideanSpace(Coords[] points) {
        super(points);
    }

    public EuclideanSpace(List<? extends Coords> points) {
        super(points);
    }

    @Override
    protected float distance(Coords li, Coords lj) {
        return sqrt(sq(li.getX() - lj.getX()) + sq(li.getY() - lj.getY()));
    }

}
