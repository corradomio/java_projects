package org.hls.example;

import jext.geotools.CorridorsGenerator;
import jext.jplotter.canvas.JPlotterFrame;
import jext.jplotter.renderables.Lines;
import jext.jplotter.renderers.CompleteRenderer;
import jext.util.concurrent.Flatten;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Polygon;

import javax.swing.SwingUtilities;
import java.awt.Color;
import java.io.File;
import java.util.List;

public class ShapePlotv3 {

    private static final File EBTIC_ROOT = new File("D:\\Projects.ebtic\\project.ftth");

    public static void main(String[] args) throws Exception {
        // System.setProperty("org.lwjgl.librarypath", "D:\\Projects.github\\java_projects\\lwjgl\\bin-3.3.6");

        JPlotterFrame frame = new JPlotterFrame("Example Viz");

        CompleteRenderer content = frame.getContent();

        File polygonsFile =
            // new File(EBTIC_ROOT, "EbticDATA/Dubai/Jumeirah Village/PARCEL.shp")
            // new File(EBTIC_ROOT, "EbticDATA/Etisalat/Greenfield/NorthernEmirates/From Hashim/yasmeenShape/yasmeen.shp")
            // new File(EBTIC_ROOT, "EbticDATA/Etisalat/Greenfield/NorthernEmirates/From Hashim/ALSEYOUH2468/revised/Al_Seyouh_2468.shp")
            // new File(EBTIC_ROOT, "EbticDATA/Etisalat/Greenfield/NorthernEmirates/From Hashim/ZUBAIR/ZUBAIR.shp")
            // new File(EBTIC_ROOT, "EbticDATA\\MasterPlan\\Latest\\RAK-ORM\\RAK-ORM\\parcel.shp")
            // new File(EBTIC_ROOT, "EbticDATA\\MasterPlan\\Latest\\EC-ODD\\EC-ODD\\parcel.shp")
            new File(EBTIC_ROOT, "EbticDATA\\MasterPlan\\Latest\\WC-OAM\\WC-OAM\\parcel.shp")
        ;

        CorridorsGenerator cg = new  CorridorsGenerator();
        CorridorsGenerator.Result r = cg.generate(polygonsFile);

        List<LineString> outwardsDirs = Flatten.flatten(r.outwards.stream().map(od -> od.directions).toList());
        List<LineString> intersectionDirs = Flatten.flatten(r.intersections.stream().map(od -> od.intersections).toList());

        addPolygons(r.polygons, content, Color.GRAY, 1);
        addLines(r.boundaries, content, Color.BLUE, 1);
        addLines(outwardsDirs, content, Color.RED, 3);
        addLines(intersectionDirs, content, Color.GREEN, 1);
        addLines(r.expanded, content, Color.RED, 1);
        addLines(r.ticks, content, Color.MAGENTA, 1);

        frame.autoBounds();

        SwingUtilities.invokeLater(()->{
            // frame.pack(); // it is necessary to force the rectangle size computation
            frame.setVisible(true);
        });
    }

    private static void addPolygons(List<Polygon> ps, CompleteRenderer content, Color color, int thickness) {

        for(Polygon p : ps) {
            Coordinate[] coords = p.getCoordinates();
            int n =  coords.length;

            Lines lines = new  Lines(color, thickness);

            for(int i=0, j=n-1; i<coords.length; i++){
                Coordinate a = coords[j];
                Coordinate b = coords[i];
                lines.addSegment(a.x, a.y, b.x, b.y);
                j = i;
            }

            content.add(lines);
        }
    }

    private static void addLines(List<LineString> lss, CompleteRenderer content, Color color, int thickness) {

        for (LineString ls : lss) {
            // addLine(ls, content, color);
            Coordinate[] coords = ls.getCoordinates();

            Lines lines = new  Lines(color, thickness);

            for(int i=1, j=0; i<coords.length; i++){
                Coordinate a = coords[j];
                Coordinate b = coords[i];
                lines.addSegment(a.x, a.y, b.x, b.y);
                j = i;
            }

            content.add(lines);
        }
    }

}
