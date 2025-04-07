package jext.swing.colormap.reference.sequential;

import jext.swing.colormap.SequentialColormap;
import jext.swing.colormap.reference.ColormapType;
import jext.swing.colormap.reference.ReferenceColormap;

import java.awt.*;

/**
 * Blugrn sequential colormap.
 */
@ReferenceColormap(type = ColormapType.SEQUENTIAL, name = "BluGrn",source = "CARTO")
public final class BluGrn extends SequentialColormap {
    /**
     * Create a colormap based on Blugrn from Plotly
     */
    public BluGrn() {
        super(
                new Color(196, 230, 195),
                new Color(150, 210, 164),
                new Color(109, 188, 144),
                new Color(77, 162, 132),
                new Color(54, 135, 122),
                new Color(38, 107, 110),
                new Color(29, 79, 96)

        );


    }


}
