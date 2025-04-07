package jext.swing.colormap.reference.sequential;

import jext.swing.colormap.SequentialColormap;
import jext.swing.colormap.reference.ColormapType;
import jext.swing.colormap.reference.ReferenceColormap;

import java.awt.*;

/**
 * Sequential ColorBrewer colormap going through Reds.
 */
@ReferenceColormap(type = ColormapType.SEQUENTIAL, name = "Reds", source = "ColorBrewer")
public final class Reds extends SequentialColormap {
    /**
     * Create a colormap based on ColorBrewer Reds.
     */
    public Reds() {
        super(
                new Color(255, 245, 240),
                new Color(254, 224, 210),
                new Color(252, 187, 161),
                new Color(252, 146, 114),
                new Color(251, 106, 74),
                new Color(239, 59, 44),
                new Color(203, 24, 29),
                new Color(165, 15, 21),
                new Color(103, 0, 13));

    }


}
