package jext.jplotter.renderers;

import hageldave.jplotter.renderables.Curves;
import hageldave.jplotter.renderables.Lines;
import hageldave.jplotter.renderables.Points;
import hageldave.jplotter.renderables.Renderable;
import hageldave.jplotter.renderables.Triangles;

import java.awt.geom.Rectangle2D;
import java.util.List;

public class CompleteRenderer extends hageldave.jplotter.renderers.CompleteRenderer {

    // ----------------------------------------------------------------------

    @Override
    public CompleteRenderer addItemToRender(Renderable item) {
        super.addItemToRender(item);
        return this;
    }

    // ----------------------------------------------------------------------

    public CompleteRenderer add(Renderable item) {
        super.addItemToRender(item);
        return this;
    }

    public CompleteRenderer addAll(Renderable... items) {
        for (Renderable item : items)
            super.addItemToRender(item);
        return this;
    }

    public CompleteRenderer addAll(List<Renderable> items) {
        for (Renderable item : items)
            super.addItemToRender(item);
        return this;
    }

    // ----------------------------------------------------------------------

    public Bounds getBounds() {
        Bounds bounds = new Bounds();

        // lines
        // points
        // text
        // triangles
        // curves

        for(Lines lines : lines.getItemsToRender()) {
            Rectangle2D r2d = lines.getBounds();
            bounds.update(r2d);
        }

        for(Points points : points.getItemsToRender()) {
            Rectangle2D r2d = points.getBounds();
            bounds.update(r2d);
        }

        for(Curves curves : curves.getItemsToRender()) {
            Rectangle2D r2d = ((jext.jplotter.renderables.Curves)curves).getBounds();
            bounds.update(r2d);
        }

        for(Triangles triangles : triangles.getItemsToRender()) {
            Rectangle2D r2d = triangles.getBounds();
            bounds.update(r2d);
        }

        return bounds;
    }

}
