package jext.swing.colormap.reference.diverging;

import jext.swing.colormap.SequentialColormap;
import jext.swing.colormap.reference.ColormapType;
import jext.swing.colormap.reference.ReferenceColormap;

import java.awt.*;

/**
 * Plotly's Picnic
 */
@ReferenceColormap(type = ColormapType.DIVERGING, name = "Picnic",source = "Plotly")
public final class Picnic extends SequentialColormap {

    /**
     * Create a colormap based on Plotly's Picnic
     */
    public Picnic() {
        super(
                Color.BLUE,
                new Color(51, 153, 255),
                new Color(102, 204, 255),
                new Color(153, 204, 255),
                new Color(204, 204, 255),
                Color.WHITE,
                new Color(255, 204, 255),
                new Color(255, 153, 255),
                new Color(255, 102, 204),
                new Color(255, 102, 102),
                Color.RED

        );

    }


}
