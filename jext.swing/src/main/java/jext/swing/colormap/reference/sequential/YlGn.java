package jext.swing.colormap.reference.sequential;

import jext.swing.colormap.SequentialColormap;
import jext.swing.colormap.reference.ColormapType;
import jext.swing.colormap.reference.ReferenceColormap;

import java.awt.*;

/**
 * Colorbrewer 2.0 YlGn.
 */
@ReferenceColormap(type = ColormapType.SEQUENTIAL, name = "YlGn", source = "ColorBrewer")
public final class YlGn extends SequentialColormap {
    /**
     * Create a colormap based on Colorbrewer 2.0 YlGn.
     */
    public YlGn() {
        super(
                new Color(255, 255, 229),
                new Color(247, 252, 185),
                new Color(217, 240, 163),
                new Color(173, 221, 142),
                new Color(120, 198, 121),
                new Color(65, 171, 93),
                new Color(35, 132, 67),
                new Color(0, 104, 55),
                new Color(0, 69, 41)
        );

    }


}
