package org.other;

import javax.swing.*;
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Custom Artwork Component");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 300);
            frame.add(new SmileyFaceComponent());
            frame.setVisible(true);
        });
    }
}
