package jext.awt.color.cmap;

import java.awt.Color;

/** Immutable, 8-bit sRGB lookup table using Matplotlib's default lookup resolution. */
public abstract class ColorMap {
    private final String name;
    private final int[] rgb;

    protected ColorMap(String name, int[] rgb) {
        if (name == null || rgb == null || rgb.length == 0)
            throw new IllegalArgumentException("A name and nonempty color table are required");
        this.name = name;
        this.rgb = rgb.clone();
    }

    public final String getName() { return name; }

    public final int size() { return rgb.length; }

    /** Returns the color at a zero-based palette index. */
    public final Color getColor(int index) { return new Color(rgb[index]); }

    /**
     * Maps a normalized value to a lookup-table bin, as in Matplotlib.
     * Values outside [0, 1] are clipped; NaN returns transparent black.
     * Use a double (e.g. 1.0) for normalized values and an int for palette indices.
     */
    public final Color getColor(double value) {
        if (Double.isNaN(value)) return new Color(0, true);
        int index = value <= 0 ? 0 : value >= 1 ? rgb.length - 1 : (int) (value * rgb.length);
        return getColor(Math.min(index, rgb.length - 1));
    }

    /** Returns a fresh array; modifying it does not change this colormap. */
    public final Color[] getColors() {
        Color[] colors = new Color[rgb.length];
        for (int i = 0; i < colors.length; i++) colors[i] = getColor(i);
        return colors;
    }

    /** Samples evenly over [0, 1], as pandas does for plot series. */
    public final Color[] sample(int count) {
        if (count < 0) throw new IllegalArgumentException("Negative sample count");
        Color[] colors = new Color[count];
        for (int i = 0; i < count; i++)
            colors[i] = getColor(count == 1 ? 0.0 : (double) i / (count - 1));
        return colors;
    }

    @Override public final String toString() { return name; }
}
