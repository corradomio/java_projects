package jext.jplotter.canvas;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

/**
 * Simple frame to visualize  a plot
 */
public class JPlotterFrame extends JFrame {

    private JPlotterCanvas canvas;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public JPlotterFrame(String title) {
        super(title);
        canvas = new JPlotterCanvas();
        init();
    }

    public JPlotterFrame(String title, JPlotterCanvas canvas) {
        super(title);
        this.canvas = canvas;

        init();
    }

    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(canvas, BorderLayout.CENTER);

        int width = 1440;

        canvas.setPreferredSize(new Dimension(width, (width*900)/1440));
        canvas.setBackground(Color.white);
        canvas.addCleanupOnWindowClosingListener(this);

        pack();

        setLocationRelativeTo(null);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public JPlotterCanvas getCanvas() {
        return canvas;
    }

    public void setVisible(boolean visible) {
        canvas.repaint();
        super.setVisible(visible);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}
