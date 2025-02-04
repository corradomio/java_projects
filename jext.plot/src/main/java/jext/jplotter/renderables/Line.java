package jext.jplotter.renderables;

import java.util.ArrayList;
import java.util.List;

public class Line {

    public final List<Point> points = new ArrayList<>();

    public Line() { }

    // ----------------------------------------------------------------------

    public Line addPoint(Point p) {
        points.add(p);
        return this;
    }

    public Line addPoints(Point... points) {
        for (Point p : points)
            addPoint(p);
        return this;
    }

    public Line addPoints(List<Point> points) {
        this.points.addAll(points);
        return this;
    }

    // ----------------------------------------------------------------------

    public Line addPoint(double x, double y) {
        points.add(new Point(x, y));
        return this;
    }

    public Line addPoints(double[] xs, double[] ys) {
        int n = xs.length;
        for (int i = 0; i < n; i++) {
            points.add(new Point(xs[i], ys[i]));
        }
        return this;
    }

    public Line addPoints(double[][] xys) {
        int n = xys.length;
        for (int i = 0; i < n; i++) {
            points.add(new Point(xys[i][0], xys[i][1]));
        }
        return this;
    }

    public Line addPoints(double... c) {
        int n = c.length/2;
        for (int i = 0; i < n; i+=2) {
            points.add(new Point(c[i], c[i+1]));
        }
        return this;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}
