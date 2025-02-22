package jext.jplotter.canvas;

import hageldave.jplotter.canvas.BlankCanvasFallback;
import hageldave.jplotter.renderers.Renderer;
import jext.jplotter.renderables.Lines;
import jext.jplotter.renderables.Points;
import jext.jplotter.renderables.Renderable;
import jext.jplotter.renderers.CompleteRenderer;
import jext.jplotter.renderers.CoordSysRenderer;

import java.util.List;

public class JPlotterCanvas extends BlankCanvasFallback {

    public JPlotterCanvas() {
        // setRenderer(
        //     new CompleteRenderer()
        //         .addItemToRender(new Points())
        //         .addItemToRender(new Lines())
        // );
    }

    // ----------------------------------------------------------------------

    // public JPlotterCanvas addContent(Renderable... items) {
    //     getContent().addAll(items);
    //     return this;
    // }

    // ----------------------------------------------------------------------

    public JPlotterCanvas setRenderer(Renderer renderer) {
        super.setRenderer(renderer);
        return this;
    }

    public CoordSysRenderer getCoordSysRenderer() {
        CoordSysRenderer coordSysRenderer = (CoordSysRenderer) getRenderer();
        if (coordSysRenderer == null) {
            coordSysRenderer = new CoordSysRenderer();
            this.setRenderer(coordSysRenderer);
        }
        return coordSysRenderer;
    }

    public CompleteRenderer getContent() {
        return getCoordSysRenderer().getContent();
    }

    // ----------------------------------------------------------------------

    public Points getPoints() {
        CompleteRenderer content = getContent();

        List pointsList = content.points.getItemsToRender();
        if (pointsList.isEmpty()) {
            content.addItemToRender(new Points());
        }

        return (Points) pointsList.get(0);
    }

    public Lines getLines() {
        CompleteRenderer content = getContent();

        List linesList = content.lines.getItemsToRender();
        if (linesList.isEmpty()) {
            content.addItemToRender(new Lines());
        }

        return (Lines) linesList.get(0);
    }
}
