package jext.jplotter.renderables;

/*
    setColorScheme
    addBarLabel
    addGlyphLabel
    addLineLabel
    addColormapLabel

 */

import hageldave.jplotter.misc.Glyph;

import java.awt.Color;
import java.util.List;

public class Legend extends hageldave.jplotter.renderables.Legend {

    public Legend() {

    }

    // ----------------------------------------------------------------------

    public Legend add(Renderable r) {
        if (r instanceof Points) {
            addLegend((Points) r);
        }
        else if (r instanceof Lines) {
            addLegend((Lines) r);
        }
        else {
            throw new IllegalArgumentException("Unsupported renderable: " + r.getClass());
        }
        return this;
    }

    public Legend addAll(Renderable... rs) {
        for (Renderable r : rs)
            add(r);
        return this;
    }

    public Legend addAll(List<Renderable> rs) {
        for (Renderable r : rs)
            add(r);
        return this;
    }

    // ----------------------------------------------------------------------
    // addBarLabel
    // addColormapLabel

    public Legend addGlyphLabel(Glyph glyph, Color color, String labeltxt) {
        super.addGlyphLabel(glyph, color.getRGB(), labeltxt);
        return this;
    }

    public Legend addLineLabel(double thickness, Color color, String labeltxt) {
        super.addLineLabel(thickness, color.getRGB(), labeltxt);
        return this;
    }

    public Legend addLegend(Points points) {
        return addGlyphLabel(points.getGlyph(), points.getColor(), points.getName());
    }

    public Legend addLegend(Lines lines) {
        return addLineLabel(lines.getThickness(), lines.getColor(), lines.getName());
    }

    // ----------------------------------------------------------------------

}
