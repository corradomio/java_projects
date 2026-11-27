package jext.eclipse.shapeeditor.editors.shp;

import java.util.ArrayList;
import java.util.List;

import jext.eclipse.shapeeditor.Utils;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;

public class ShapeViewerFigure extends Figure {

    private final List<Geometry> geometries = new ArrayList<>();

    private double worldMinX = 0.0;
    private double worldMaxX = 1.0;
    private double worldMinY = 0.0;
    private double worldMaxY = 1.0;

    private double scale = 1.0;
    private int offsetX = 10;
    private int offsetY = 8;
    private int viewW;
    private int viewH;
    private boolean autoFit = true;

    public ShapeViewerFigure() {
        setOpaque(true);
        setBackgroundColor(ColorConstants.white);
    }

    public void setGeometries(List<Geometry> geometries) {
        this.geometries.clear();
        if (geometries != null) {
            this.geometries.addAll(geometries);
        }
        computeBounds();
        autoFit = true;
        repaint();
    }

    public void zoomBy(double factor) {
        if (viewW <= 0 || viewH <= 0) return;
        autoFit = false;
        double newScale = Math.max(1e-9, scale * factor);

        double ratio = newScale / scale;
        offsetX = (int) Math.round(viewW / 2.0 - (viewW / 2.0 - offsetX) * ratio);
        offsetY = (int) Math.round(viewH / 2.0 - (viewH / 2.0 - offsetY) * ratio);
        scale = newScale;
        repaint();
    }

    public void panBy(int dx, int dy) {
        if (viewW <= 0 || viewH <= 0) return;
        autoFit = false;
        offsetX += dx;
        offsetY += dy;
        repaint();
    }

    private void computeBounds() {
        if (geometries.isEmpty()) {
            worldMinX = 0.0; worldMaxX = 1.0;
            worldMinY = 0.0; worldMaxY = 1.0;
            return;
        }
        double minX = Double.MAX_VALUE, maxX = -Double.MAX_VALUE;
        double minY = Double.MAX_VALUE, maxY = -Double.MAX_VALUE;

        for (Geometry geometry : geometries) {
            Coordinate[] coords = geometry.getCoordinates();
            for (Coordinate c : coords) {
                if (c.x < minX) minX = c.x;
                if (c.x > maxX) maxX = c.x;
                if (c.y < minY) minY = c.y;
                if (c.y > maxY) maxY = c.y;
            }
        }

        if (minX <= maxX && minY <= maxY) {
            worldMinX = minX; worldMaxX = maxX;
            worldMinY = minY; worldMaxY = maxY;
        }
    }

    private void fitToView() {
        double dx = worldMaxX - worldMinX;
        double dy = worldMaxY - worldMinY;
        double scaleX = dx > 0 ? viewW * 0.9 / dx : Double.POSITIVE_INFINITY;
        double scaleY = dy > 0 ? viewH * 0.8 / dy : Double.POSITIVE_INFINITY;
        scale = Math.min(scaleX, scaleY);
        if (!Double.isFinite(scale)) scale = 1.0;
        offsetX = (int) Math.round((viewW - dx * scale) / 2.0);
        offsetY = (int) Math.round((viewH - dy * scale) / 2.0);
    }

    private int toScreenX(double x) {
        return (int) Math.round((x - worldMinX) * scale) + offsetX;
    }

    private int toScreenY(double y) {
        return (int) Math.round((worldMaxY - y) * scale) + offsetY;
    }

    @Override
    protected void paintFigure(Graphics graphics) {
        Rectangle bounds = getBounds();
        if (bounds.width <= 0 || bounds.height <= 0) return;
        viewW = bounds.width;
        viewH = bounds.height;
        if (autoFit) fitToView();

        graphics.translate(bounds.x, bounds.y);
        graphics.setBackgroundColor(getBackgroundColor());
        graphics.fillRectangle(0, 0, viewW, viewH);

        for (Geometry geometry : geometries) {
            drawGeometry(graphics, geometry);
        }
    }

    private void drawGeometry(Graphics graphics, Geometry geometry) {
        if (geometry.isEmpty()) return;
        if (geometry instanceof Point) {
            drawPoint(graphics, (Point) geometry);
            return;
        }
        if (geometry instanceof LineString) {
            drawLine(graphics, (LineString) geometry);
            return;
        }
        if (geometry instanceof org.locationtech.jts.geom.Polygon) {
            drawPolygon(graphics, (org.locationtech.jts.geom.Polygon) geometry);
            return;
        }

        int count = geometry.getNumGeometries();
        for (int i = 0; i < count; i++) {
            drawGeometry(graphics, geometry.getGeometryN(i));
        }
    }

    private void drawPoint(Graphics graphics, Point point) {
        int x = toScreenX(point.getX());
        int y = toScreenY(point.getY());
        graphics.setBackgroundColor(ColorConstants.darkGray);
        graphics.fillOval(x - 2, y - 2, 5, 5);
    }

    private void drawLine(Graphics graphics, LineString line) {
        Coordinate[] coords = line.getCoordinates();
        if (coords.length < 2) return;

        int n = coords.length;
        int[] xs = new int[n];
        int[] ys = new int[n];
        for (int i = 0; i < n; i++) {
            xs[i] = toScreenX(coords[i].x);
            ys[i] = toScreenY(coords[i].y);
        }
        graphics.setForegroundColor(ColorConstants.black);
        int[] xys = Utils.compose(xs, ys, n);
        graphics.drawPolyline(xys);
    }

    private void drawPolygon(Graphics graphics, org.locationtech.jts.geom.Polygon polygon) {
        LineString ring = polygon.getExteriorRing();
        Coordinate[] coords = ring.getCoordinates();
        if (coords.length < 3) return;

        int n = coords.length;
        int[] xs = new int[n];
        int[] ys = new int[n];
        for (int i = 0; i < n; i++) {
            xs[i] = toScreenX(coords[i].x);
            ys[i] = toScreenY(coords[i].y);
        }

        graphics.setBackgroundColor(ColorConstants.lightGray);
        int[] xys = Utils.compose(xs, ys, n);
        graphics.fillPolygon(xys);

        for (int hi = 0; hi < polygon.getNumInteriorRing(); hi++) {
            LineString hole = polygon.getInteriorRingN(hi);
            Coordinate[] hc = hole.getCoordinates();
            if (hc.length < 3) continue;
            int hn = hc.length;
            int[] hxs = new int[hn];
            int[] hys = new int[hn];
            for (int i = 0; i < hn; i++) {
                hxs[i] = toScreenX(hc[i].x);
                hys[i] = toScreenY(hc[i].y);
            }
            graphics.setBackgroundColor(ColorConstants.white);
            int[] hxys = Utils.compose(hxs, hys, hn);
            graphics.fillPolygon(hxys);
        }

        graphics.setForegroundColor(ColorConstants.black);
        xys = Utils.compose(xs, ys, n);
        graphics.drawPolygon(xys);

        for (int hi = 0; hi < polygon.getNumInteriorRing(); hi++) {
            LineString hole = polygon.getInteriorRingN(hi);
            Coordinate[] hc = hole.getCoordinates();
            if (hc.length < 3) continue;
            int hn = hc.length;
            int[] hxs = new int[hn];
            int[] hys = new int[hn];
            for (int i = 0; i < hn; i++) {
                hxs[i] = toScreenX(hc[i].x);
                hys[i] = toScreenY(hc[i].y);
            }
            int[] hxys = Utils.compose(hxs, hys, hn);
            graphics.drawPolygon(hxys);
        }
    }
}
