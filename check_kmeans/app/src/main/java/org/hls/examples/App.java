package org.hls.examples;

import hageldave.jplotter.misc.DefaultGlyph;
import jext.jplotter.canvas.JPlotterCanvas;
import jext.jplotter.canvas.JPlotterFrame;
import jext.jplotter.renderables.Legend;
import jext.jplotter.renderables.Points;
import org.apache.commons.math4.legacy.ml.clustering.BisectingKMeansPlusPlusClusterer;
import org.apache.commons.math4.legacy.ml.clustering.Cluster;
import org.apache.commons.math4.legacy.ml.clustering.Clusterable;
import org.apache.commons.math4.legacy.ml.clustering.Clusterer;
import org.apache.commons.math4.legacy.ml.distance.EuclideanDistance;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;

import javax.swing.SwingUtilities;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {

    static class Point implements Clusterable {

        private final double[] xy;

        Point(double x, double y) {
            xy = new double[]{x,y};
        }


        @Override
        public double[] getPoint() {
            return xy;
        }
    }

    public static void main(String[] args) {

        List<Point> points = new ArrayList<>();
        Random rnd = new Random();
        UniformRandomProvider rng = RandomSource.MWC_256.create();

        for (int i=0; i<5000; ++i) {
            Point p = new Point(rnd.nextDouble(), rnd.nextDouble());

            points.add(p);
        }

        Clusterer<Point> algo = new BisectingKMeansPlusPlusClusterer<>(
            100,
            1000,
            new EuclideanDistance()
        );

        List<? extends Cluster<Point>> clusters = algo.cluster(points);

        System.out.printf("n points: %d\n", points.size());
        for(Cluster<Point> c : clusters)
            System.out.printf("...%d\n", c.getPoints().size());

        System.out.printf("n clusters: %d\n", clusters.size());

        // ------------------------------------------------------------------


        // ------------------------------------------------------------------

        Points ppoints = new Points().setGlyps(DefaultGlyph.CIRCLE_F).setColor(Color.RED).setScaling(.5);

        for(Point p : points)
            ppoints.addPoint(p.xy[0], p.xy[1]);

        JPlotterFrame frame = new JPlotterFrame("Example Viz");
        JPlotterCanvas canvas = frame.getCanvas();

        canvas.getContent().addAll(
            ppoints
        );

        canvas
            .getCoordSysRenderer()
            .setMarginView(.1, .1)
            .setAspectRatio(1)
            .setLegend(new Legend().addAll(
                ppoints
            ), 80, false)
            .compose()
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
