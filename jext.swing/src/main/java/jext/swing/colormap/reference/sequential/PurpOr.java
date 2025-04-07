package jext.swing.colormap.reference.sequential;

import jext.swing.colormap.SequentialColormap;
import jext.swing.colormap.reference.ColormapType;
import jext.swing.colormap.reference.ReferenceColormap;

import java.awt.*;

/**
 * Purpor sequential colormap.
 */
@ReferenceColormap(type = ColormapType.SEQUENTIAL, name = "PurpOr",source = "CARTO")
public final class PurpOr extends SequentialColormap {
    /**
     * Create a colormap based on Purpor from Plotly
     */
    public PurpOr() {
        super(
                new Color(249, 221, 218),
                new Color(242, 185, 196),
                new Color(229, 151, 185),
                new Color(206, 120, 179),
                new Color(173, 95, 173),
                new Color(131, 75, 160),
                new Color(87, 59, 136)


        );


    }


}
