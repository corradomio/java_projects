package jext.swing.colormap.reference.diverging;

import jext.swing.colormap.SequentialColormap;
import jext.swing.colormap.reference.ColormapType;
import jext.swing.colormap.reference.ReferenceColormap;

import java.awt.*;

/**
 * Plotly's Portland
 */
@ReferenceColormap(type = ColormapType.DIVERGING, name = "Portland",source = "Plotly")
public final class Portland extends SequentialColormap {

    /**
     * Create a colormap based on Plotly's Portland
     */
    public Portland() {
        super(
                new Color(12, 51, 131),
                new Color(10, 136, 186),
                new Color(242, 211, 56),
                new Color(242, 143, 56),
                new Color(217, 30, 30)
        );

    }


}
