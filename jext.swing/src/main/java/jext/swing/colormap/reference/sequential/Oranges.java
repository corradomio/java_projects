package jext.swing.colormap.reference.sequential;

import jext.swing.colormap.SequentialColormap;
import jext.swing.colormap.reference.ColormapType;
import jext.swing.colormap.reference.ReferenceColormap;

import java.awt.*;

/**
 * Sequential ColorBrewer colormap going through Oranges.
 */
@ReferenceColormap(type = ColormapType.SEQUENTIAL, name = "Oranges", source = "ColorBrewer")
public final class Oranges extends SequentialColormap {

    /**
     * Create a colormap based on ColorBrewer Oranges.
     */
    public Oranges() {
        super(
                new Color(255, 245, 235),
                new Color(254, 230, 206),
                new Color(253, 208, 162),
                new Color(253, 174, 107),
                new Color(253, 141, 60),
                new Color(241, 105, 19),
                new Color(217, 72, 1),
                new Color(166, 54, 3),
                new Color(127, 39, 4));

    }


}
