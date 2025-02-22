package org.hls.example;

import jext.jplotter.canvas.JPlotterFrame;
import jext.jplotter.renderables.Point;
import jext.jplotter.renderables.Points;
import jext.jplotter.renderers.CompleteRenderer;
import jext.jplotter.renderers.CoordSysRenderer;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.util.Random;

public class CheckJPlotter3 {

    public static void main(String[] args) {
        Random rnd= new Random();

        // CompleteRenderer content = new CompleteRenderer()
        //     .add(new Points().setColor(Color.GREEN).setScaling(2))
        //     ;
        // CoordSysRenderer coordsys = new CoordSysRenderer()
        //     .addContent(content)
        //     ;

        JPlotterFrame frame = new JPlotterFrame("Example Viz");
        // frame.getCanvas().setRenderer(coordsys);
        frame.getCanvas().getPoints().setColor(Color.ORANGE);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Thread.sleep(2000);
                for(int i=0; i<10; i++) {
                    Thread.sleep(2000);
                    System.out.println("point");
                    frame.getCanvas().getPoints().addPoint(rnd.nextDouble(-1, 1), rnd.nextDouble(-1, 1));
                    frame.getCanvas().repaint();
                }
                return null;
            }
        }.execute();

        SwingUtilities.invokeLater(()->{
            frame.setVisible(true);
        });
    }
}
