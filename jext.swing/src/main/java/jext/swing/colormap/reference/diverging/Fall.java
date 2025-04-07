package jext.swing.colormap.reference.diverging;

import jext.swing.colormap.SequentialColormap;
import jext.swing.colormap.reference.ColormapType;
import jext.swing.colormap.reference.ReferenceColormap;

import java.awt.*;

/**
 * Plotly's Fall
 */
@ReferenceColormap(type = ColormapType.DIVERGING, name = "Fall", source = "CARTO")
public final class Fall extends SequentialColormap {

    /**
     * Create a colormap based on Plotly's Fall
     */
    public Fall() {
        super(
                new Color(61, 89, 65),
                new Color(119, 136, 104),
                new Color(181, 185, 145),
                new Color(246, 237, 189),
                new Color(237, 187, 138),
                new Color(222, 138, 90),
                new Color(202, 86, 44)


        );

    }


}
