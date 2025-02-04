package jext.jplotter.renderables;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class Curves extends hageldave.jplotter.renderables.Curves implements Renderable{

    private String name = "lines";
    private Color color = Color.BLUE;
    private double thickness = 2;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public Curves() {

    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public Curves setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public Rectangle2D.Double getBounds() {
        // Rectangle2D bounds = new Rectangle2D.Double(Double.MAX_VALUE/2, Double.MAX_VALUE/2, -Double.MAX_VALUE+2, -Double.MAX_VALUE+2);
        double xmin = +Double.MAX_VALUE;
        double ymin = +Double.MAX_VALUE;
        double xmax = -Double.MAX_VALUE;
        double ymax = -Double.MAX_VALUE;

        for(CurveDetails cd : curves) {
            if (cd.p0.getX() < xmin) xmin = cd.p0.getX();
            if (cd.p0.getY() < ymin) ymin = cd.p0.getY();
            if (cd.p0.getX() > xmax) xmax = cd.p0.getX();
            if (cd.p0.getY() > ymax) ymax = cd.p0.getY();

            if (cd.p1.getX() < xmin) xmin = cd.p1.getX();
            if (cd.p1.getY() < ymin) ymin = cd.p1.getY();
            if (cd.p1.getX() > xmax) xmax = cd.p1.getX();
            if (cd.p1.getY() > ymax) ymax = cd.p1.getY();
        }

        return new Rectangle2D.Double(xmin, ymin, xmax - xmin, ymax - ymin);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------
}
