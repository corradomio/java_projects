package jext.jplotter.canvas;

import hageldave.jplotter.renderers.Renderer;
import hageldave.jplotter.util.Utils;
import jext.jplotter.renderables.Lines;
import jext.jplotter.renderables.Points;
import jext.jplotter.renderers.Bounds;
import jext.jplotter.renderers.CompleteRenderer;
import jext.jplotter.renderers.CoordSysRenderer;

import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

/**
 * Canvas based on 'BlankCanvasFallback' (Java2D rendering) with:
 *
 *  - panning: drag with the left mouse button
 *  - zoom: mouse wheel, centered on the mouse position
 *  - reset: double click (calls 'autoBounds')
 *
 * On resize the current scale (data units per pixel) is preserved,
 * so the view is not stretched.
 */
public class OpenGLCanvas extends hageldave.jplotter.canvas.BlankCanvasFallback implements JPlotterCanvas {

    // scale factor applied for each wheel notch (> 1)
    private double zoomStep = 1.15;

    // last mouse position during a drag (null if not panning)
    private Point dragStart;

    // canvas size and coordsys area size of the last render
    private Dimension lastCanvasSize;
    private Dimension lastAreaSize;

    // the aspect ratio must be fixed after the first render, when the coordsys area is known
    private boolean fixAspectAfterRender = false;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public OpenGLCanvas() {
        installMouseInteraction();
    }

    // ----------------------------------------------------------------------

    public OpenGLCanvas setRenderer(Renderer renderer) {
        super.setRenderer(renderer);
        lastCanvasSize = null;
        lastAreaSize = null;
        return this;
    }

    @Override
    public CoordSysRenderer getCoordSysRenderer() {
        Renderer renderer = getRenderer();
        if (renderer == null) {
            CoordSysRenderer coordSysRenderer = new CoordSysRenderer();
            this.setRenderer(coordSysRenderer);
            return coordSysRenderer;
        }
        if (!(renderer instanceof CoordSysRenderer))
            throw new IllegalStateException("Renderer is not a jext CoordSysRenderer: " + renderer.getClass().getName());
        return (CoordSysRenderer) renderer;
    }

    @Override
    public CompleteRenderer getContent() {
        return getCoordSysRenderer().getContent();
    }

    public OpenGLCanvas setZoomStep(double zoomStep) {
        this.zoomStep = zoomStep;
        return this;
    }

    // ----------------------------------------------------------------------

    @Override
    public Points getPoints() {
        CompleteRenderer content = getContent();

        List pointsList = content.points.getItemsToRender();
        if (pointsList.isEmpty()) {
            content.addItemToRender(new Points());
        }

        return (Points) pointsList.get(0);
    }

    @Override
    public Lines getLines() {
        CompleteRenderer content = getContent();

        List linesList = content.lines.getItemsToRender();
        if (linesList.isEmpty()) {
            content.addItemToRender(new Lines());
        }

        return (Lines) linesList.get(0);
    }

    // ----------------------------------------------------------------------
    // aspect ratio: width:height

    @Override
    public void autoBounds() {
        Bounds bounds = getContent().getBounds();
        setBounds(bounds);
    }

    void setBounds(Bounds bounds) {
        // the coordsys area is known only after the first render:
        // until then use the canvas size and fix the ratio later
        double aspectRatio;
        if (lastAreaSize != null) {
            aspectRatio = (double) lastAreaSize.width / lastAreaSize.height;
        }
        else {
            aspectRatio = (double) Math.max(1, getWidth()) / Math.max(1, getHeight());
            fixAspectAfterRender = true;
        }
        bounds.aspectRatio(aspectRatio);

        getCoordSysRenderer().setBounds(bounds);
    }

    // ----------------------------------------------------------------------
    // Rendering

    @Override
    protected void render(Graphics2D g, Graphics2D p, int w, int h) {
        CoordSysRenderer coordsys = currentCoordSys();
        if (coordsys != null)
            keepScaleOnResize(coordsys, w, h);

        super.render(g, p, w, h);

        if (coordsys == null)
            return;

        Rectangle2D area = coordsys.getCoordSysArea();
        lastCanvasSize = new Dimension(w, h);
        lastAreaSize = area.getWidth() > 0 && area.getHeight() > 0
            ? new Dimension((int) area.getWidth(), (int) area.getHeight())
            : null;

        if (fixAspectAfterRender && lastAreaSize != null) {
            fixAspectAfterRender = false;
            Rectangle2D v = coordsys.getCoordinateView();
            Bounds bounds = new Bounds().update(v.getMinX(), v.getMaxX(), v.getMinY(), v.getMaxY());
            bounds.aspectRatio((double) lastAreaSize.width / lastAreaSize.height);
            coordsys.setBounds(bounds);
            scheduleRepaint();
        }
    }

    /**
     * When the canvas size changes, resize the coordinate view proportionally to
     * the (estimated) new coordsys area, keeping its center. Axes and legend have
     * a fixed size in pixels, so the area changes by the same amount as the canvas.
     */
    private void keepScaleOnResize(CoordSysRenderer coordsys, int w, int h) {
        if (lastCanvasSize == null || lastAreaSize == null)
            return;
        if (lastCanvasSize.width == w && lastCanvasSize.height == h)
            return;

        double newAreaW = lastAreaSize.width + (w - lastCanvasSize.width);
        double newAreaH = lastAreaSize.height + (h - lastCanvasSize.height);
        if (newAreaW <= 0 || newAreaH <= 0)
            return;

        Rectangle2D v = coordsys.getCoordinateView();
        double halfW = v.getWidth() * newAreaW / lastAreaSize.width / 2;
        double halfH = v.getHeight() * newAreaH / lastAreaSize.height / 2;
        double cx = v.getCenterX();
        double cy = v.getCenterY();
        coordsys.setCoordinateView(cx - halfW, cy - halfH, cx + halfW, cy + halfH);
    }

    // ----------------------------------------------------------------------
    // Mouse interaction

    private void installMouseInteraction() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                requestFocusInWindow();
                if (SwingUtilities.isLeftMouseButton(e) && insidePlot(e.getPoint()))
                    dragStart = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                dragStart = null;
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                CoordSysRenderer coordsys = currentCoordSys();
                if (dragStart == null || coordsys == null)
                    return;

                Rectangle2D area = coordsys.getCoordSysArea();      // pixels
                Rectangle2D view = coordsys.getCoordinateView();    // data units
                double dx = (e.getX() - dragStart.x) / area.getWidth() * view.getWidth();
                double dy = (e.getY() - dragStart.y) / area.getHeight() * view.getHeight();
                dragStart = e.getPoint();

                // AWT y axis points down, coordsys y axis points up: +dy
                coordsys.setCoordinateView(
                    view.getMinX() - dx, view.getMinY() + dy,
                    view.getMaxX() - dx, view.getMaxY() + dy);
                repaint();
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                CoordSysRenderer coordsys = currentCoordSys();
                if (coordsys == null || !insidePlot(e.getPoint()))
                    return;

                // the point under the cursor stays fixed; wheel down = zoom out
                Point2D p = coordsys.transformAWT2CoordSys(e.getPoint(), getHeight());
                Rectangle2D v = coordsys.getCoordinateView();
                double s = Math.pow(zoomStep, e.getPreciseWheelRotation());
                coordsys.setCoordinateView(
                    p.getX() + (v.getMinX() - p.getX()) * s,
                    p.getY() + (v.getMinY() - p.getY()) * s,
                    p.getX() + (v.getMaxX() - p.getX()) * s,
                    p.getY() + (v.getMaxY() - p.getY()) * s);
                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2 && currentCoordSys() != null) {
                    autoBounds();
                    repaint();
                }
            }
        };
        addMouseListener(ma);
        addMouseMotionListener(ma);
        addMouseWheelListener(ma);
    }

    private boolean insidePlot(Point p) {
        CoordSysRenderer coordsys = currentCoordSys();
        return coordsys != null
            && coordsys.getCoordSysArea().contains(Utils.swapYAxis(p, getHeight()));
    }

    /** The current renderer if it is a CoordSysRenderer, otherwise null (no interaction) */
    private CoordSysRenderer currentCoordSys() {
        Renderer renderer = getRenderer();
        return renderer instanceof CoordSysRenderer ? (CoordSysRenderer) renderer : null;
    }

}
