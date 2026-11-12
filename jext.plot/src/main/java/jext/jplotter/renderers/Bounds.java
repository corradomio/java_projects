package jext.jplotter.renderers;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Bounds {

    public double minX;
    public double maxX;
    public double minY;
    public double maxY;
    private int count = 0;

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
        ++count;
        return this;
    }

    public Bounds update(double x, double y) {
        if (x < minX) minX = x;
        if (x > maxX) maxX = x;
        if (y < minY) minY = y;
        if (y > maxY) maxY = y;
        ++count;
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

    /**
     * Update the bounds to ensure the aspect ratio.
     * The bounds are NOT reduced, only expanded
     *
     * @param aspectRatio required aspect ratio
     * @return
     */
    public void aspectRatio(double aspectRatio) {
        double width = maxX - minX;
        double height = maxY - minY;

        double currentRatio = width/height;

        if (currentRatio < aspectRatio) {
            double xc = (minX + maxX) / 2;
            width = height*aspectRatio;
            double dx = width/2;
            minX = xc - dx;
            maxX = xc + dx;
        }
        else if (currentRatio > aspectRatio) {
            double yc = (minY + maxY) / 2;
            height = width/aspectRatio;
            double dy = height/2;
            minY = yc -dy;
            maxY = yc + dy;
        }

    }

}
