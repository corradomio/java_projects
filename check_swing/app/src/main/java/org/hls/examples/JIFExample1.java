package org.hls.examples;

import javax.swing.*;

public class JIFExample1 extends JFrame {

    // frame
    static JFrame f;

    // label to display text
    static JLabel l;

    // main class
    public static void main(String[] args)
    {
        // create a new frame to
        f = new JFrame("frame");

        // create a internal frame
        JInternalFrame in = new JInternalFrame();

        // set the title of the frame
        in.setTitle("InternalFrame");

        // create a Button
        JButton b = new JButton("button");

        // create a label to display text
        l = new JLabel("This is a JInternal Frame  ");

        // create a panel
        JPanel p = new JPanel();

        // add label and button to panel
        p.add(l);
        p.add(b);

        // set visibility internal frame
        in.setVisible(true);

        // add panel to internal frame
        in.add(p);

        // add internal frame to frame
        f.add(in);

        // set the size of frame
        f.setSize(300, 300);

        f.show();
    }
}
