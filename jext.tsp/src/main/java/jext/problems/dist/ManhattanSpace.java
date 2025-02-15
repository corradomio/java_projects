package jext.problems.dist;

import jext.problems.Coords;

import java.util.List;

import static java.lang.Math.abs;

public class ManhattanSpace extends MetricSpace {

    public ManhattanSpace(Coords[] points) {
        super(points);
    }

    public ManhattanSpace(List<Coords> points) {
        super(points);
    }

    @Override
    protected float distance(Coords li, Coords lj) {
        return abs(li.getX() - lj.getX()) + abs(li.getY() - lj.getY());
    }

}
