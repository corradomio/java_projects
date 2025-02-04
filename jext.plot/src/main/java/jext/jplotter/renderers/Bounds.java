package jext.jplotter.renderers;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Bounds {

    public double minX;
    public double maxX;
    public double minY;
    public double maxY;

    public Bounds() {
        minX = +Double.MAX_VALUE;
        maxX = -Double.MAX_VALUE;
        minY = +Double.MAX_VALUE;
        maxY = -Double.MAX_VALUE;
    }

    public Bounds update(double minX, double maxX, double minY, double maxY) {
        if (minX < this.minX) this.minX = minX;
        if (maxX > this.maxX) this.maxX = maxX;
        if (minY < this.minY) this.minY = minY;
        if (maxY > this.maxY) this.maxY = maxY;
        return this;
    }

    public Bounds update(double x, double y) {
        if (x < minX) minX = x;
        if (x > maxX) maxX = x;
        if (y < minY) minY = y;
        if (y > maxY) maxY = y;
        return this;
    }

    public Bounds update(Point2D point) {
        return update(point.getX(), point.getY());
    }

    public Bounds update(Rectangle2D rect) {
        return update(rect.getX(), rect.getX() + rect.getWidth(), rect.getY(), rect.getY() + rect.getHeight());
    }

    public Bounds margin(double marginX, double marginY) {
        return new Bounds().update(minX - marginX, maxX + marginX, minY - marginY, maxY + marginY);
    }
}
