package jext.jplotter.canvas;

import hageldave.jplotter.canvas.FBOCanvas;
import hageldave.jplotter.renderers.Renderer;
import jext.jplotter.renderables.Lines;
import jext.jplotter.renderables.Points;
import jext.jplotter.renderers.CompleteRenderer;
import jext.jplotter.renderers.CoordSysRenderer;

public class BlankCanvas extends hageldave.jplotter.canvas.BlankCanvas implements JPlotterCanvas {

    public BlankCanvas(FBOCanvas parent) {
        super(parent);
    }

    public BlankCanvas() {
        super();
    }

    @Override
    public BlankCanvas setRenderer(Renderer renderer) {
        super.setRenderer(renderer);
        return this;
    }

    @Override
    public void autoBounds() {

    }

    @Override
    public CoordSysRenderer getCoordSysRenderer() {
        return (CoordSysRenderer) super.renderer;
    }

    @Override
    public CompleteRenderer getContent() {
        return getCoordSysRenderer().getContent();
    }

    @Override
    public Points getPoints() {
        return null;
    }

    @Override
    public Lines getLines() {
        return null;
    }
}
