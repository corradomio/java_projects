package jext.jplotter.renderables;

import hageldave.jplotter.renderables.Text;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Texts implements Renderable {

    private String name = "texts";
    private int fontsize = 12;
    private int style =  Font.PLAIN;
    private Color color = Color.BLACK;
    public final List<Text> labels = new ArrayList<>();

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public Texts() {

    }

    public Texts(int fontsize, int style, Color color) {
        this.fontsize = fontsize;
        this.style = style;
        this.color = color;
    }

    public Texts(int fontsize, int style, int color) {
        this.fontsize = fontsize;
        this.style = style;
        this.color = new Color(color);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public Texts setName(String name) {
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

    public void addText(String label, double x, double y) {
        Text text = new Text(label, fontsize, style, color);
        text.setOrigin(new Point(x, y));
        labels.add(text);
    }

    // ----------------------------------------------------------------------
    // Compatibility
    // ----------------------------------------------------------------------

    @Override
    public void initGL() {

    }

    @Override
    public boolean isDirty() {
        return false;
    }

    @Override
    public void updateGL(boolean useGLDoublePrecision) {

    }

    @Override
    public boolean isGLDoublePrecision() {
        return false;
    }

    @Override
    public void close() {

    }

    @Override
    public boolean intersects(Rectangle2D rect) {
        return false;
    }
}
