package org.hls.example;

import hageldave.jplotter.misc.DefaultGlyph;
import jext.jplotter.canvas.JPlotterCanvas;
import jext.jplotter.canvas.JPlotterFrame;
import jext.jplotter.renderables.Legend;
import jext.jplotter.renderables.Points;

import javax.swing.SwingUtilities;
import java.awt.Color;
import java.io.File;
import java.util.List;

public class CheckMetersData {

    public static void main(String[] args) throws Exception {

        List<MeterData> metersData = MeterData.load(new File("Meters Data.csv"));

        Points points = new Points().setGlyps(DefaultGlyph.CIRCLE_F).setColor(Color.RED).setScaling(.5);

        metersData.stream()
            .filter(
            md -> {
                return md.getLongitude() > 0 && md.getLatitude() > 0;
            })
            .forEach( md -> {
                points.addPoint(md.getLongitude(), md.getLatitude());
            });

        JPlotterFrame frame = new JPlotterFrame("Example Viz");
        JPlotterCanvas canvas = frame.getCanvas();

        canvas.getContent().addAll(
            points
        );

        canvas
            .getCoordSysRenderer()
            .setMarginView(.1, .1)
            .setAspectRatio(1)
            .setLegend(new Legend().addAll(
                points
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
