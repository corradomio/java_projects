package jext.jplotter.renderables;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleSupplier;

public class Lines extends hageldave.jplotter.renderables.Lines implements Renderable {

    private String name = "lines";
    private Color color = Color.BLUE;
    private double thickness = 2;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public Lines() {

    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public Lines setName(final String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public Lines setColor (Color color) {
        this.color = color;
        return this;
    }

    public Lines setColor (int color) {
        this.color = new Color(color);
        return this;
    }

    public Color getColor() {
        return color;
    }

    public Lines setThickness(double thickness) {
        this.thickness = thickness;
        return this;
    }

    public double getThickness() {
        return thickness;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------
    // double[] xCoords, double[] yCoords
    // Point2D... points
    // double[][] coords
    // double... coords

    public SegmentDetails addSegment(Point p1, Point p2) {
        SegmentDetails sd = super.addSegment(p1, p2);
        sd.setColor(color);
        sd.setThickness(thickness);
        return sd;
    }

    public ArrayList<SegmentDetails> addLineStrip(Point... points) {
        ArrayList<SegmentDetails> sdlist = super.addLineStrip(points);
        sdlist.forEach(sd -> {
            sd.setColor(color);
            sd.setThickness(thickness);
        });
        return sdlist;
    }

    public ArrayList<SegmentDetails> addLineStrip(List<Point> points) {
        Point[] array = points.toArray(new Point[0]);
        return addLineStrip(array);
    }

    public ArrayList<SegmentDetails> addLineStrip(Line line) {
        return addLineStrip(line.points);
    }

    // ----------------------------------------------------------------------

    public Lines add(Point p1, Point p2) {
        addSegment(p1, p2);
        return this;
    }

    public Lines add(Line line) {
        addLineStrip(line);
        return this;
    }

    // ----------------------------------------------------------------------

    public SegmentDetails addSegment(double x1, double y1, double x2, double y2) {
        SegmentDetails sd = super.addSegment(x1, y1, x2, y2);
        sd.setColor(color);
        sd.setThickness(thickness);
        return sd;
    }

    public ArrayList<SegmentDetails> addLineStrip(double[] xCoords, double[] yCoords) {
        ArrayList<SegmentDetails> sdlist = super.addLineStrip(xCoords, yCoords);
        sdlist.forEach(sd -> {
            sd.setColor(color);
            sd.setThickness(thickness);
        });
        return sdlist;
    }

    public ArrayList<SegmentDetails> addLineStrip(double[][] coords){
        ArrayList<SegmentDetails> lineStrip = super.addLineStrip(coords);
        lineStrip.forEach(sd -> {
            sd.setColor(color);
            sd.setThickness(thickness);
        });
        return lineStrip;
    }

    public ArrayList<SegmentDetails> addLineStrip(double... coords) {
        ArrayList<SegmentDetails> lineStrip = super.addLineStrip(coords);
        lineStrip.forEach(sd -> {
            sd.setColor(color);
            sd.setThickness(thickness);
        });
        return lineStrip;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public Lines hide(boolean hide) {
        super.hide(hide);
        return this;
    }

    @Override
    public Lines setGlobalAlphaMultiplier(DoubleSupplier globalAlphaMultiplier) {
        super.setGlobalAlphaMultiplier(globalAlphaMultiplier);
        return this;
    }

    @Override
    public Lines setGlobalAlphaMultiplier(double globalAlphaMultiplier) {
        super.setGlobalAlphaMultiplier(globalAlphaMultiplier);
        return this;
    }

    @Override
    public Lines setGlobalThicknessMultiplier(DoubleSupplier thickness) {
        super.setGlobalThicknessMultiplier(thickness);
        return this;
    }

    @Override
    public Lines setGlobalThicknessMultiplier(double thickness) {
        super.setGlobalThicknessMultiplier(thickness);
        return this;
    }

    @Override
    public Lines setGlobalSaturationMultiplier(DoubleSupplier saturation) {
        super.setGlobalSaturationMultiplier(saturation);
        return this;
    }

    @Override
    public Lines setGlobalSaturationMultiplier(double saturation) {
        super.setGlobalSaturationMultiplier(saturation);
        return this;
    }

    @Override
    public Lines setVertexRoundingEnabled(boolean useVertexRounding) {
        super.setVertexRoundingEnabled(useVertexRounding);
        return this;
    }

    @Override
    public Lines setStrokePattern(int strokePattern) {
        super.setStrokePattern(strokePattern);
        return this;
    }

    @Override
    public Lines setStrokeLength(double strokeLength) {
        super.setStrokeLength(strokeLength);
        return this;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}
