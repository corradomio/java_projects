package jext.swing.colormap.reference.sequential;

import jext.swing.colormap.SequentialColormap;
import jext.swing.colormap.reference.ColormapType;
import jext.swing.colormap.reference.ReferenceColormap;

import java.awt.*;

/**
 * Electric sequential colormap.
 */
@ReferenceColormap(type = ColormapType.SEQUENTIAL, name = "Electric",source = "Plotly")
public final class Electric extends SequentialColormap {
    /**
     * Create a colormap based on Electric from Plotly
     */
    public Electric() {
        super(
                Color.BLACK,
                new Color(30, 0, 100),
                new Color(120, 0, 100),
                new Color(160, 90, 0),
                new Color(230, 200, 0),
                new Color(255, 250, 220)

        );


    }


}
