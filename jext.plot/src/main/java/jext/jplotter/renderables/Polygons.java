package jext.jplotter.renderables;

import java.awt.Color;

public class Polygons extends hageldave.jplotter.renderables.Lines implements Renderable {

    private String name = "lines";
    private Color color = Color.BLUE;
    private Color fillColor = new Color(0, 0, 0, 0);
    private double thickness = 2;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public Polygons() {

    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public Polygons setName(final String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public Polygons setColor (Color color) {
        this.color = color;
        return this;
    }

    public Polygons setColor (int color) {
        this.color = new Color(color);
        return this;
    }

    public Color getColor() {
        return color;
    }

    public Polygons setFillColor (Color color) {
        this.fillColor = color;
        return this;
    }

    public Polygons setFillColor (int color) {
        this.fillColor = new Color(color);
        return this;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public Polygons setThickness(double thickness) {
        this.thickness = thickness;
        return this;
    }

    public double getThickness() {
        return thickness;
    }

}
