package jext.plot;

import java.awt.*;
import java.util.List;


public class DrawUtils {

    public static void clear(Graphics2D g, Rectangle r) {
        g.clearRect(r.x, r.y, r.width, r.height);
    }

    private static String fmt(double x) {
        return String.format("%3.1f", x);
    }

    public static void drawAxis(Graphics2D g, Bounds b, Rectangle r) {
        g.drawString(fmt(b.min.x), r.x - 7,              r.y + r.height + 15);
        g.drawString(fmt(b.max.x), r.x + r.width - 10, r.y + r.height + 15);

        g.drawString(fmt(b.min.y), 5, r.y + r.height + 5);
        g.drawString(fmt(b.max.y), 5, r.y+5);
    }

    public static void drawLine(Graphics2D g, List<Coords> coords, Color c, Bounds b, Rectangle r) {
        int n;
        g.setColor(c);
        n = coords.size();
        for(int i=0, j=1; j<n; i++,j++) {
            Coords c1 = coords.get(i);
            Coords c2 = coords.get(j);

            if (!b.contains(c1) || !b.contains(c2))
                continue;

            Point p1 = b.convert(c1, r);
            Point p2 = b.convert(c2, r);

            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    public static void drawRectangle(Graphics2D g, Rectangle r) {
        g.drawRect(r.x, r.y, r.width, r.height);
    }

    /* public static void drawRectangle(Graphics2D g, int x1, int y1, int x2, int y2) {
        drawPolygon(g, x1, y1, x2, y1, x2, y2, x1, y2, x1, y1);
    } */

    /* public static void drawPolygon(Graphics2D g, int... coords) {
        int n = coords.length-3;

        for(int i=0, j=1; i<n; i+=2, j+=2) {
            g.drawLine(coords[i], coords[i+1], coords[i+2], coords[i+3]);
        }
    } */
}
