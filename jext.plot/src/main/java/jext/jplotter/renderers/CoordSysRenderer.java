package jext.jplotter.renderers;

import hageldave.jplotter.renderers.Renderer;

public class CoordSysRenderer extends hageldave.jplotter.renderers.CoordSysRenderer  {

    private boolean automaticBounds = true;
    private double marginX = 0;
    private double marginY = 0;

    public CoordSysRenderer() { }

    public CoordSysRenderer setAutomaticBounds(boolean automaticBounds) {
        this.automaticBounds = automaticBounds;
        return this;
    }

    public CompleteRenderer getContent() {
        CompleteRenderer content = (CompleteRenderer) super.getContent();
        if (content == null) {
            content = new CompleteRenderer();
            super.setContent(content);
        }
        return content;
    }

    public CoordSysRenderer addContent(CompleteRenderer content){
        super.setContent(content);
        if (automaticBounds)
            setCoordinateView(content.getBounds().margin(marginX, marginY));
        return this;
    }

    /**
     * Extend the content bounds of the specified margin
     *
     * Note: call 'setMarginView' after 'addContent'
     * @param marginX horizontal margin
     * @param marginY vertical margin
     * @return this
     */
    public CoordSysRenderer setMarginView(double marginX, double marginY) {
        this.marginX = marginX;
        this.marginY = marginY;

        if (automaticBounds) {
            CompleteRenderer cr = getContent();
            setCoordinateView(cr.getBounds().margin(marginX, marginY));
        }
        return this;
    }

    // ----------------------------------------------------------------------

    public CoordSysRenderer setCoordinateView(Bounds bounds){
        return setCoordinateView(bounds.minX, bounds.minY, bounds.maxX, bounds.maxY);
    }

    @Override
    public CoordSysRenderer setCoordinateView(double minX, double minY, double maxX, double maxY) {
        super.setCoordinateView(minX, minY, maxX, maxY);
        return this;
    }

    // ----------------------------------------------------------------------

    @Override
    public CoordSysRenderer setLegendRight(Renderer legend) {
        super.setLegendRight(legend);
        return this;
    }

    @Override
    public CoordSysRenderer setLegendBottom(Renderer legend) {
        super.setLegendBottom(legend);
        return this;
    }

    public CoordSysRenderer setLegend(Renderer legend, int legendSize, boolean bottom) {
        if (legendSize > 0) {
            if (bottom)
                super.setLegendBottomHeight(legendSize);
            else
                super.setLegendRightWidth(legendRightWidth);
        }
        if (bottom)
            super.setLegendBottom(legend);
        else
            super.setLegendRight(legend);
        return this;
    }

}
