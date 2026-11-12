package jext.jplotter.canvas;

import hageldave.jplotter.renderers.Renderer;
import jext.jplotter.renderables.Lines;
import jext.jplotter.renderables.Points;
import jext.jplotter.renderers.CompleteRenderer;
import jext.jplotter.renderers.CoordSysRenderer;

import java.awt.Color;
import java.awt.Dimension;

public interface JPlotterCanvas extends hageldave.jplotter.canvas.JPlotterCanvas {

    void setPreferredSize(Dimension size);
    void setBackground(Color color);

    void autoBounds();

    // ----------------------------------------------------------------------

    JPlotterCanvas setRenderer(Renderer renderer);

    CoordSysRenderer getCoordSysRenderer();

    CompleteRenderer getContent();

    // ----------------------------------------------------------------------

    Points getPoints();

    Lines getLines();
}
