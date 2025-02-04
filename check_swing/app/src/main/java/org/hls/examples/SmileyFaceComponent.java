package org.hls.examples;
import jext.swing.JComponent;

import java.awt.*;

public class SmileyFaceComponent extends JComponent {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the face
        g.setColor(Color.YELLOW);
        g.fillOval(10, 10, 200, 200);

        // Draw the eyes
        g.setColor(Color.BLACK);
        g.fillOval(60, 60, 40, 40);
        g.fillOval(160, 60, 40, 40);

        // Draw the mouth
        g.drawArc(60, 90, 100, 100, 0, -180);
    }
}
