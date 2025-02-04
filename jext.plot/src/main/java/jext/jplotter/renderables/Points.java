package jext.jplotter.renderables;

import hageldave.jplotter.misc.Glyph;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Points extends hageldave.jplotter.renderables.Points implements Renderable {

    private String name = "points";
    private double scaling = 1;
    private Color color = Color.BLUE;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public Points() {
        super();
    }

    // ----------------------------------------------------------------------

    public Points setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public Points setScaling(double scaling) {
        this.scaling = scaling;
        return this;
    }

    public Points setGlyps(Glyph glyph) {
        super.setGlyph(glyph);
        return this;
    }

    public Points setColor(Color color) {
        this.color = color;
        return this;
    }

    public Points setColor(int color) {
        this.color = new Color(color);
        return this;
    }

    public Color getColor() {
        return color;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public PointDetails addPoint(Point p) {
        PointDetails pd = super.addPoint(p);
        pd.setColor(color);
        pd.setScaling(scaling);
        return pd;
    }

    // public List<PointDetails> addPoints(Point[] points) {
    //     List<PointDetails> pdlist = new ArrayList<>();
    //     for (Point p : points) {
    //         pdlist.add(addPoint(p));
    //     }
    //     return pdlist;
    // }

    public List<PointDetails> addPoints(Point... points) {
        List<PointDetails> pdlist = new ArrayList<>();
        for (Point p : points) {
            pdlist.add(addPoint(p));
        }
        return pdlist;
    }

    public List<PointDetails> addPoints(List<Point> points) {
        List<PointDetails> pdlist = new ArrayList<>();
        for (Point p : points) {
            pdlist.add(addPoint(p));
        }
        return pdlist;
    }

    // ----------------------------------------------------------------------

    @Override
    public PointDetails addPoint(double x, double y) {
        PointDetails pd = super.addPoint(x, y);
        pd.setColor(color);
        if (scaling != 1)
            pd.setScaling(scaling);
        return pd;
    }

    public List<PointDetails> addPoints(double[] x, double[] y) {
        List<PointDetails> pdlist = new ArrayList<>();
        int n = x.length;
        for(int i=0; i<n; i++)
            pdlist.add(addPoint(x[i], y[i]));

        return pdlist;
    }

    public List<PointDetails> addPoints(double[][] coords) {
        List<PointDetails> pdlist = new ArrayList<>();
        int n = coords.length;
        for(int i=0; i<n; i++)
            pdlist.add(addPoint(coords[i][0], coords[i][1]));

        return pdlist;
    }

    public List<PointDetails> addPoints(double... coords) {
        List<PointDetails> pdlist = new ArrayList<>();
        int n = coords.length;
        for(int i=0; i<n; i+=2)
            pdlist.add(addPoint(coords[i], coords[i+1]));

        return pdlist;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}
