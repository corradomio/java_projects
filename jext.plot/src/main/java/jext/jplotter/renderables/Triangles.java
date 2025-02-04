package jext.jplotter.renderables;

import java.awt.Color;

public class Triangles extends hageldave.jplotter.renderables.Triangles implements Renderable {

    private String name = "lines";
    private Color[] color = new Color[]{ Color.BLUE, Color.BLUE, Color.BLUE };

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public Triangles() {

    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public Triangles setName(final String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public Triangles setColor (Color color) {
        this.color[0] = color;
        this.color[1] = color;
        this.color[2] = color;
        return this;
    }

    public Triangles setColor (int color) {
        return setColor(new Color(color));
    }

    public Triangles setColor (Color... color) {
        this.color[0] = color[0];
        this.color[1] = color[1];
        this.color[2] = color[2];
        return this;
    }

    public Triangles setColor(int... color) {
        return setColor(new Color(color[0]), new Color(color[1]), new Color(color[2]));
    }

    public Color getColor(int i) {
        return color[i];
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public TriangleDetails addTriangle(Point p0, Point p1, Point p2) {
        TriangleDetails td = super.addTriangle(p0, p1, p2);
        td.setColor0(color[0]);
        td.setColor1(color[1]);
        td.setColor2(color[2]);
        return td;
    }

    @Override
    public TriangleDetails addTriangle(
        double x0, double y0,
        double x1, double y1,
        double x2, double y2
    ){
        TriangleDetails td = super.addTriangle(x0, y0, x1, y1, x2, y2);
        td.setColor0(color[0]);
        td.setColor1(color[1]);
        td.setColor2(color[2]);
        return td;
    }
}
