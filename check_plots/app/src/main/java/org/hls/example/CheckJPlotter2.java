package org.hls.example;

// import hageldave.jplotter.canvas.JPlotterCanvas;
import hageldave.jplotter.misc.DefaultGlyph;

import jext.jplotter.canvas.JPlotterFrame;
import jext.jplotter.renderers.CoordSysRenderer;
import jext.jplotter.renderers.CompleteRenderer;
import jext.jplotter.renderables.Legend;
import jext.jplotter.renderables.Lines;
import jext.jplotter.renderables.Points;

import javax.swing.SwingUtilities;
import java.util.function.DoubleUnaryOperator;

public class CheckJPlotter2 {

    @SuppressWarnings("resource" /* compiler is too dumb to realize there is no leak */)
    public static void main(String[] args) {

        System.setProperty("org.lwjgl.librarypath", "D:\\Projects.github\\java_projects\\lwjgl\\bin-3.3.6");

        // lets prepare some data we want to visualize
        DoubleUnaryOperator fx = Math::sin;
        // lets sample the sine function so we can later plot a line
        int numCurveSamples = 100;
        double[] curveX = new double[numCurveSamples];
        double[] curveY = new double[numCurveSamples];
        for(int i=0; i<numCurveSamples; i++){
            double x = i*Math.PI*2/numCurveSamples;
            double y = fx.applyAsDouble(x);
            curveX[i]=x;  curveY[i]=y;
        }
        // also lets make some random (x,y) samples that we can compare against the function
        int numPointSamples = 400;
        double[] pointsX = new double[numPointSamples];
        double[] pointsY = new double[numPointSamples];
        double[] diffToCurve = new double[numPointSamples];
        for(int i=0; i<numPointSamples; i++){
            double x = Math.random()*Math.PI*2;
            double y = Math.random()*Math.PI*2-Math.PI;
            diffToCurve[i] = y-fx.applyAsDouble(x);
            pointsX[i]=x;  pointsY[i]=y;
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
            // .forEach(segment -> segment.setColor(sineColor));

        // the random samples should be visualized as points, but lets make 3 classes
        // class 1: y(x) < sin(x)-0.5
        // class 2: sin(x)-0.5 <= y(x) <= sin(x)+0.5
        // class 3: sin(x)+0.5 < y(x)
        int c1Color = 0xff8da0cb, c2Color = sineColor, c3Color = 0xfffc8d62;
        Points pointsC1 = new Points().setGlyps(DefaultGlyph.CROSS).setColor(c1Color).setName("< f(x)-0.5");
        Points pointsC2 = new Points().setGlyps(DefaultGlyph.CIRCLE_F).setColor(c2Color).setName("~ f(x)");
        Points pointsC3 = new Points().setGlyps(DefaultGlyph.CROSS).setColor(c3Color).setName("> f(x)+0.5");
        for(int i=0; i<numPointSamples; i++){
            if(diffToCurve[i] < -0.5){
                pointsC1.addPoint(pointsX[i], pointsY[i]);
            } else if(diffToCurve[i] > 0.5) {
                pointsC3.addPoint(pointsX[i], pointsY[i]);
            } else {
                pointsC2.addPoint(pointsX[i], pointsY[i]);
            }
        }

        // lets add a legend so the viewer can make sense of the data
        Legend legend = new Legend()
            .addLegend(sineLine)
            .addLegend(pointsC1)
            .addLegend(pointsC2)
            .addLegend(pointsC3);
        // .addGlyphLabel(DefaultGlyph.CROSS, c1Color, "< f(x)-0.5")
        // .addGlyphLabel(DefaultGlyph.CIRCLE, c2Color, "~ f(x)")
        // .addGlyphLabel(DefaultGlyph.CROSS, c3Color, "> f(x)+0.5")


        // okay we're good to go, lets display the data in a coordinate system
        CompleteRenderer content = new CompleteRenderer()
            .add(sineLine)
            .add(pointsC1)
            .add(pointsC2)
            .add(pointsC3);

        CoordSysRenderer coordsys = new CoordSysRenderer()
            .setContent(content)
            .setMarginView(.1, .1)
            // .setCoordinateView(-.5, -3.3, 6.5, 3.3)
            .setLegend(legend, 80, false)
        ;

        // lets set the coordinate view to cover the whole sampling space
        // coordsys.setCoordinateView(-.5, -3.3, 6.5, 3.3);

        // coordsys.setLegendRightWidth(80);
        // coordsys.setLegendRight(legend
        //     .addLegend(sineLine)
        //     .addLegend(pointsC1)
        //     .addLegend(pointsC2)
        //     .addLegend(pointsC3)
        // );

        // JPlotterCanvas canvas = useOpenGL ? new BlankCanvas() : new BlankCanvasFallback();
        JPlotterFrame frame = new JPlotterFrame("Example Viz");
        // frame.setJMenuBar(ExportUtil.createSaveMenu(frame, "example_export"));

        // JPlotterCanvas canvas = frame.getCanvas().setRenderer(coordsys);
        frame.getCanvas().setRenderer(coordsys);

        // lets add some controls for exploring the data
        // new CoordSysScrollZoom(canvas,coordsys).setZoomFactor(1.7).register();
        // new CoordSysViewSelector(canvas,coordsys, new KeyMaskListener(0)) {
        //     // deprecated {extModifierMask=0;/* no need for shift to be pressed */}
        //     @Override
        //     public void areaSelected(double minX, double minY, double maxX, double maxY) {
        //         coordsys.setCoordinateView(minX, minY, maxX, maxY);
        //     }
        // }.register();

        // lets put a JFrame around it all and launch
        // JFrame frame = new JFrame("Example Viz");
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.getContentPane().add(canvas);
        // canvas.setPreferredSize(new Dimension(480, 400));
        // canvas.setBackground(Color.white);
        // canvas.addCleanupOnWindowClosingListener(frame);

        SwingUtilities.invokeLater(()->{
            // frame.pack(); // it is necessary to force the rectangle size computation
            frame.setVisible(true);
        });

        // frame.setJMenuBar(ExportUtil.createSaveMenu(frame, "example_export"));
    }
}
