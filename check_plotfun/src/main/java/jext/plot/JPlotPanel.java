package jext.plot;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class JPlotPanel extends JPanel {

    private Bounds bounds;
    private Rectangle area;
    private Dimension preferredDim = new Dimension(400, 300);
    private JPanel panel;

    // ----------------------------------------------------------------------

    private boolean xRescale = false;
    private boolean yRescale = true;

    private List<Coords> y1 = new ArrayList<>();
    private List<Coords> y2 = new ArrayList<>();

    private boolean updated = true;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public JPlotPanel(double minGen, double maxGen, double minFit, double maxFit, Color col, int width, int height, JPanel panel) {
        super();
        initialize(minGen, maxGen, minFit, maxFit, col, width, height, panel);
    }

    public JPlotPanel() {
        super();
        this.bounds = new Bounds();
        initialize();
    }

    public JPlotPanel(Bounds bounds) {
        super();
        this.bounds = bounds;
        initialize();
    }

    private void initialize() {
        setVisible(true);

        Dimension d = getPreferredSize();
        this.area = new Rectangle(25, 10, d.width - 10, d.height - 10 - 10);

        if (this.panel != null)
            this.panel.updateUI();
    }

    // ----------------------------------------------------------------------

    public JPlotPanel initialize(double minGen, double maxGen, double minFit, double maxFit, Color col, int width, int height, JPanel panel) {
        this.updated = true;

        if (this.panel != null) {
            this.panel.remove(this);
        }

        this.panel = panel;
        // (xmin, ymin), (xmax, ymax)
        this.bounds = new Bounds(minGen, maxGen, minFit, maxFit+1);
        this.preferredDim = new Dimension(width, height);

        if (this.panel != null) {
            this.panel.add(this);
        }

        initialize();

        return this;
    }

    // ----------------------------------------------------------------------

    public void xLimit(double xmin, double xmax) {
        Bounds b = this.bounds;
        this.bounds = new Bounds(xmin, xmax, b.min.y, b.max.y);
    }

    public void yLimit(double ymin, double ymax) {
        Bounds b = this.bounds;
        this.bounds = new Bounds(b.min.x, b.max.x, ymin, ymax);
    }

    public void rescale(boolean xRescale, boolean yRescale) {
        this.xRescale = xRescale;
        this.yRescale = yRescale;
    }

    // ----------------------------------------------------------------------

    public Dimension getPreferredSize( ) {
        return preferredDim;
    }

    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        Rectangle r = g.getClipBounds();
        int n;

        if (this.updated)
            DrawUtils.clear(g, r);

        DrawUtils.drawRectangle(g, area);
        DrawUtils.drawAxis(g, bounds, area);

        DrawUtils.drawLine(g, y1, Color.black, bounds, area);
        DrawUtils.drawLine(g, y2, Color.red,   bounds, area);
    }

    // ----------------------------------------------------------------------

    public void add(double x, double y) {
        Coords c = new Coords(x, y);

        if (this.yRescale)
            this.updated = this.bounds.update(c);

        this.y1.add(c);
    }

    public void add(double x, double y1, double y2) {
        Coords c1 = new Coords(x, y1);
        Coords c2 = new Coords(x, y2);

        if (this.yRescale)
            this.updated = this.bounds.update(c1) || this.bounds.update(c2);

        this.y1.add(c1);
        this.y2.add(c2);
        this.updateUI();
    }

    public void pointToPerfGraph(double curMaxFit, double curAvgFit, int curGen, int lineType) {
        add(curGen, curAvgFit, curMaxFit);
    }
}
