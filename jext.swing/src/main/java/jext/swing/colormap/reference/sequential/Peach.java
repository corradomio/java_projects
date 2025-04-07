package jext.swing.colormap.reference.sequential;

import jext.swing.colormap.SequentialColormap;
import jext.swing.colormap.reference.ColormapType;
import jext.swing.colormap.reference.ReferenceColormap;

import java.awt.*;

/**
 * Peach sequential colormap.
 */
@ReferenceColormap(type = ColormapType.SEQUENTIAL, name = "Peach",source = "CARTO")
public final class Peach extends SequentialColormap {
    /**
     * Create a colormap based on Peach from Plotly
     */
    public Peach() {
        super(
                new Color(253, 224, 197),
                new Color(250, 203, 166),
                new Color(248, 181, 139),
                new Color(245, 158, 114),
                new Color(242, 133, 93),
                new Color(239, 106, 76),
                new Color(235, 74, 64)
        );


    }


}
