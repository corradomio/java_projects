package jext.swing.colormap.reference.sequential;

import jext.swing.colormap.reference.ColormapType;
import jext.swing.colormap.SequentialColormap;
import jext.swing.colormap.reference.ReferenceColormap;

import java.awt.*;

/**
 * Sequential ColorBrewer colormap going through Blues.
 */
@ReferenceColormap(type = ColormapType.SEQUENTIAL, name = "Blues", source = "ColorBrewer")
public final class Blues extends SequentialColormap {
    /**
     * Create a colormap based on ColorBrewer Blues.
     */
    public Blues() {
        super(
                new Color(247, 251, 255),
                new Color(222, 235, 247),
                new Color(198, 219, 239),
                new Color(158, 202, 225),
                new Color(107, 174, 214),
                new Color(66, 146, 198),
                new Color(33, 113, 181),
                new Color(8, 81, 156),
                new Color(8, 48, 107));

    }


}
