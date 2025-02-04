package org.hls.examples;

import jext.swing.JCanvas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class AppForm extends JFrame {
    private JPanel contentPanel;
    private JCanvas canvas;
    private JTabbedPane tabbedPane1;

    public AppForm() {
        setTitle("Vehicle Routing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPanel);


        setSize(1440, 900);
        setLocationRelativeTo(null);

        canvas.repaint();
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
