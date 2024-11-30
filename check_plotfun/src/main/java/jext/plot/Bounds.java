package jext.plot;

import java.awt.*;

public class Bounds {

    public Coords min, max;

    public Bounds() {
        this.min = new Coords(0, 0);
        this.max = new Coords(1, 1);
    }

    public Bounds(double xmin, double xmax) {
        this.min = new Coords(xmin, 0);
        this.max = new Coords(xmax, 1);
    }

    public Bounds(double xmin, double xmax, double ymin, double ymax) {
        this.min = new Coords(xmin, ymin);
        this.max = new Coords(xmax, ymax);
    }

    public boolean update(Coords c) {
        return update(c.x, c.y);
    }

    public boolean update(double x, double y) {
        boolean updated = false;
        if (x < min.x) { min.x = x; updated = true; }
        if (x > max.x) { max.x = x; updated = true; }
        if (y < min.y) { min.y = y; updated = true; }
        if (y > max.y) { max.y = y; updated = true; }
        if (updated) {
            min.x = (int)(min.x + 0.9);
            min.y = (int)(min.y + 0.9);
            max.x = (int)(max.x + 0.9);
            max.y = (int)(max.y + 0.9);
        }
        return updated;
    }

    public boolean contains(Coords c) {
        return contains(c.x, c.y);
    }

    public boolean contains(double x, double y) {
        return min.x <= x && x <= max.x
            && min.y <= y && y <= max.y;
    }

    public Point convert(Coords c, Rectangle r) {
        int xi, yi;
        xi = r.x +            (int)(r.width  * (c.x - min.x)/(max.x - min.x));
        yi = r.y + r.height - (int)(r.height * (c.y - min.y)/(max.y - min.y));
        return new Point(xi, yi);
    }

}
