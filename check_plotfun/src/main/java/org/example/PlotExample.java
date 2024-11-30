package org.example;

import jext.plot.JPlotPanel;

import javax.swing.*;
import java.util.Random;

public class PlotExample {

    public static void main(String[] args) {
        System.out.println("Hello world!");

        Random r = new Random();

        JPlotPanel jpp = new JPlotPanel();
        jpp.xLimit(0, 50);
        jpp.yLimit(0, 1);
        jpp.rescale(false, true);

        JFrame frame = new JFrame();
        frame.add(jpp);

        frame.setSize(450, 450);
        frame.setVisible(true);

        for(int i=1; i<=50; ++i) {
            double x = i;
            double y1 = r.nextDouble();
            double y2 = y1 + r.nextDouble();

            jpp.add(x, y1, y2);
        }

        System.out.println("Done");
    }
}
