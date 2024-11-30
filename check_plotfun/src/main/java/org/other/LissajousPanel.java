package org.other;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;

public class LissajousPanel extends JPanel {

    private static final int SIZE = 400;
    private GeneralPath path = new GeneralPath();

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(SIZE, SIZE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        double dt = Math.PI / 180;
        int w = getWidth() / 2;
        int h = getHeight() / 2;
        path.reset();
        path.moveTo(w, h);
        for (double t = 0; t < 2 * Math.PI; t += dt) {
            double x = w * Math.sin(5 * t) + w;
            double y = h * Math.sin(4 * t) + h;
            path.lineTo(x, y);
        }
        g2d.setColor(Color.blue);
        g2d.draw(path);
    }

    public static void main(String[] args) {
        // EventQueue.invokeLater(new Runnable() {
        //
        //     @Override
        //     public void run() {
        //         JFrame f = new JFrame();
        //         f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //         f.add(new LissajousPanel());
        //         f.pack();
        //         f.setVisible(true);
        //     }
        // });
        JFrame f = new JFrame();
        f.setLayout(new FlowLayout(FlowLayout.LEFT));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new JTextField("Hello Crueld World"));
        f.add(new LissajousPanel());
        f.setSize(new Dimension(440,440));
        // f.pack();
        f.setVisible(true);
    }
}
