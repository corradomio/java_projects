package org.hls.example;

import hageldave.jplotter.misc.DefaultGlyph;
import jext.jplotter.canvas.JPlotterFrame;
import jext.jplotter.renderables.Legend;
import jext.jplotter.renderables.Lines;
import jext.jplotter.renderables.Points;

import javax.swing.SwingUtilities;
import java.util.function.DoubleUnaryOperator;

public class CheckJPlotter4 {

    @SuppressWarnings("resource" /* compiler is too dumb to realize there is no leak */)
    public static void main(String[] args) {

        // lets prepare some data we want to visualize
        DoubleUnaryOperator fx = Math::sin;
        // lets sample the sine function so we can later plot a line
        int numCurveSamples = 100;
        double[] curveX = new double[numCurveSamples];
        double[] curveY = new double[numCurveSamples];
        for (int i = 0; i < numCurveSamples; i++) {
            double x = i * Math.PI * 2 / numCurveSamples;
            double y = fx.applyAsDouble(x);
            curveX[i] = x;
            curveY[i] = y;
        }

        // also lets make some random (x,y) samples that we can compare against the function
        int numPointSamples = 400;
        double[] pointsX = new double[numPointSamples];
        double[] pointsY = new double[numPointSamples];
        double[] diffToCurve = new double[numPointSamples];
        for (int i = 0; i < numPointSamples; i++) {
            double x = Math.random() * Math.PI * 2;
            double y = Math.random() * Math.PI * 2 - Math.PI;
            diffToCurve[i] = y - fx.applyAsDouble(x);
            pointsX[i] = x;
            pointsY[i] = y;
        }

        // alright this should be enough data for now
        // see how we want to visualize the data

        // the sine samples should be visualized as a line in a kind of red color
        int sineColor = 0xff66c2a5;
        Lines sineLine = new Lines()
            .setColor(sineColor)
            // .setGlobalThicknessMultiplier(2)
            .setThickness(2.)
            .setStrokePattern(0xFFFF)
            // .setStrokePattern(0xf790)
            ;
        sineLine
            .addLineStrip(curveX, curveY);

        // the random samples should be visualized as points, but lets make 3 classes
        // class 1: y(x) < sin(x)-0.5
        // class 2: sin(x)-0.5 <= y(x) <= sin(x)+0.5
        // class 3: sin(x)+0.5 < y(x)

        Points pointsC1 = new Points().setGlyps(DefaultGlyph.CROSS).setColor(0xff8da0cb).setName("< f(x)-0.5");
        Points pointsC2 = new Points().setGlyps(DefaultGlyph.CIRCLE_F).setColor(sineColor).setName("~ f(x)");
        Points pointsC3 = new Points().setGlyps(DefaultGlyph.CROSS).setColor(0xfffc8d62).setName("> f(x)+0.5");

        for (int i = 0; i < numPointSamples; i++) {
            if (diffToCurve[i] < -0.5) {
                pointsC1.addPoint(pointsX[i], pointsY[i]);
            } else if (diffToCurve[i] > 0.5) {
                pointsC3.addPoint(pointsX[i], pointsY[i]);
            } else {
                pointsC2.addPoint(pointsX[i], pointsY[i]);
            }
        }

        // ----------------------------------------------------------------------------------

        JPlotterFrame frame = new JPlotterFrame("Example Viz");
        frame.getCanvas().getContent().addAll(
            sineLine,
            pointsC1,
            pointsC2,
            pointsC3
        );

        frame.getCanvas()
            .getCoordSysRenderer()
            .setMarginView(.1, .1)
            .setLegend(new Legend().addAll(
                sineLine,
                pointsC1,
                pointsC2,
                pointsC3
            ), 80, false)
        ;

        SwingUtilities.invokeLater(() -> {
            try {
                frame.setVisible(true);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });

        System.out.println("Done");
    }
}
