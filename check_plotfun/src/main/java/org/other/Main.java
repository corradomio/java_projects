package org.other;

import optimtools.utilityplot.RuntimeAvgMaxPlotter;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

//
// https://www.codejava.net/java-se/swing/jframe-basic-tutorial-and-examples
//

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        // JFrame frame = new JFrame();
        // frame.setLayout(new FlowLayout());
        //
        // // JTextField textFieldUserName = new JTextField(50);
        // // frame.add(textFieldUserName);
        //
        // frame.add(new RuntimeAvgMaxPlotter(0, 100, 0, 100, Color.red, 800, 700));
        //
        // // frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // // frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        // // frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(1000, 800);
        // frame.pack();
        // frame.setVisible(true);

        // JFrame frame = new JFrame();
        // frame.setLayout(new FlowLayout());
        // frame.add(new RuntimeAvgMaxPlotter(0, 50, 0, 1000, Color.RED, 400,400));

        Random rnd = new Random();

        RuntimeAvgMaxPlotter rtp = new RuntimeAvgMaxPlotter(0, 50, -1, 1, Color.RED, 400,400);

        JFrame frame = new JFrame();
        frame.add(rtp);
        frame.setSize(450, 450);
        frame.setVisible(true);
        // rtp.setVisible(true);

        for(int i=0; i<50; ++i) {
            rtp.pointToPerfGraph(rnd.nextDouble(), -1 + 2*rnd.nextDouble(), i, 1);
        }

        System.out.println("Done");
    }
}
