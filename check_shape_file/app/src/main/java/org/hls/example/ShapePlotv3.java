package org.hls.example;

import hageldave.jplotter.renderables.Text;
import jext.apache.logging.log4j.Logger;
import jext.awt.color.NamedColors;
import jext.geotools.BoundaryAngle;
import jext.geotools.CorridorsGenerator;
import jext.geotools.ShapeUtils;
import jext.geotools.junction.BoundaryChamfers;
import jext.geotools.junction.BoundaryJunctions;
import jext.geotools.junction.JunctionBridge;
import jext.jplotter.canvas.JPlotterFrame;
import jext.jplotter.renderables.Lines;
import jext.jplotter.renderables.Points;
import jext.jplotter.renderables.Texts;
import jext.jplotter.renderers.CompleteRenderer;
import jext.util.concurrent.Flatten;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.List;

public class ShapePlotv3 {

    private static final File EBTIC_ROOT = new File("D:\\Projects.ebtic\\project.ftth.data");

    public static void main(String[] args) throws Exception {
        // System.setProperty("org.lwjgl.librarypath", "D:\\Projects.github\\java_projects\\lwjgl\\bin-3.3.6");

        Logger.configure("log4j2.xml");
        Logger log = Logger.getLogger();
        log.info("Logging system configured");

        JPlotterFrame frame = new JPlotterFrame("Example Viz");

        CompleteRenderer content = frame.getContent();

        File polygonsFile =
            // new File(EBTIC_ROOT, "EbticDATA/Dubai/Jumeirah Village/PARCEL.shp")
            // new File(EBTIC_ROOT, "EbticDATA/Etisalat/Greenfield/NorthernEmirates/From Hashim/yasmeenShape/yasmeen.shp")
            // new File(EBTIC_ROOT, "EbticDATA/Etisalat/Greenfield/NorthernEmirates/From Hashim/ALSEYOUH2468/revised/Al_Seyouh_2468.shp")
            // new File(EBTIC_ROOT, "EbticDATA/Etisalat/Greenfield/NorthernEmirates/From Hashim/ZUBAIR/ZUBAIR.shp")
            // new File(EBTIC_ROOT, "EbticDATA\\MasterPlan\\Latest\\RAK-ORM\\RAK-ORM\\parcel.shp")
            // new File(EBTIC_ROOT, "EbticDATA\\MasterPlan\\Latest\\EC-ODD\\EC-ODD\\parcel.shp")
            // new File(EBTIC_ROOT, "EbticDATA\\MasterPlan\\Latest\\WC-OAM\\WC-OAM\\parcel.shp")
            // new File(EBTIC_ROOT, "EbticDATA\\MasterPlan\\Latest\\AUH-OHY\\base_line.shp");
            new File(EBTIC_ROOT, "EbticDATA\\MasterPlan\\Latest\\WC-OMN\\WC-OMN1\\base_line.shp");
        ;

        CorridorsGenerator cg = new  CorridorsGenerator();

        // cg.setMinSegmentLength(0.2);
        // cg.setMaxSegmentLength(40);
        // cg.setMaxCollinearAngleDegree(1);
        // cg.setMaxCollinearOffsetAngleDegree(1);

        cg.setMinPolygonArea(2500);

        cg.setMaxIntersectionDistance(80);
        cg.setMaxOffsetCollinearAngleDegree(10);

        cg.setMaxChamferAngleDegree(120);
        cg.setChamferJunctionDistance(5);
        cg.setJunctionDistance(100);

        log.info("CorridorsGenerator");
        CorridorsGenerator.Result r = cg.generate(polygonsFile);

        log.info("Collect data for visualization");
        List<LineString> outwardsDirs = Flatten.flatten(r.outwards.stream().map(od -> od.directions).toList());
        List<LineString> intersectionDirs = Flatten.flatten(r.intersections.stream().map(od -> od.intersections).toList());
        List<Point> chamferPoints = Flatten.flatten(r.chamferLocations.stream().map(BoundaryChamfers::points).toList());
        List<Point> chamferExpandedPoints = Flatten.flatten(r.chamferLocations.stream().map(BoundaryChamfers::expandedPoint) .toList());
        List<Point> junctionPoints = Flatten.flatten(r.junctionLocations.stream().map(BoundaryJunctions::points).toList());
        List<Point> junctionNearPoints = Flatten.flatten(r.junctionLocations.stream().map(BoundaryJunctions::nearPoints).toList());
        List<Point> junctionFarPoints = Flatten.flatten(r.junctionLocations.stream().map(BoundaryJunctions::farPoints).toList());
        List<LineString> junctionOutwards = Flatten.flatten(r.junctionLocations.stream().map(BoundaryJunctions::outwards).toList());

        log.info("Collect bridgeOutwards");
        List<LineString> bridgeOutwards = r.junctionBridges.stream().map(JunctionBridge::outward).toList();
        List<LineString> bridgeDirections = r.junctionBridges.stream().map(JunctionBridge::direction).toList();

        log.info("Populate JPlotter");
        addPolygons(r.allPolygons, content, Color.GRAY, 1);
        addLines(r.boundaries, content, Color.BLUE, 1);
        // addLines(outwardsDirs, content, Color.RED, 3);
        // addLines(intersectionDirs, content, Color.GREEN, 1);
        addLines(r.expanded, content, Color.RED, 1);
        // addLines(r.junctioned, content, Color.RED, 1);
        // addLines(r.ticks, content, Color.MAGENTA, 1);

        // addPoints(chamferPoints, content, Color.BLUE, 1);
        // addPoints(chamferExpandedPoints, content, Color.RED, 1);
        // addPoints(junctionPoints, content, Color.GRAY, .75);
        addPoints(junctionNearPoints, content, Color.BLUE, 0.75);
        addPoints(junctionFarPoints, content, Color.GREEN, 0.75);

        // addLines(junctionOutwards, content, Color.PINK, 1);

        addLines(bridgeOutwards, content, NamedColors.get("brown"), 3);
        addLines(bridgeDirections, content, NamedColors.get("aquamarine"), 3);

        // addIndices(r.boundaries, content, Color.BLUE, 10);
        // addIndices(r.expanded, content, Color.RED, 10);
        // addIndices(r.junctioned, content, Color.MAGENTA, 12);
        addLines(r.bridges, content, Color.PINK, 1);

        // addLines(bridgeDirections, content, Color.MAGENTA, 1);
        // addLines(junctionDirections, content, NamedColors.get("brown"), 1);

        // Texts texts = computeAngles(r.boundaries);
        // addTexts(texts, content);

        frame.autoBounds();

        log.info("Show JPlotter");
        SwingUtilities.invokeLater(()->{
            // frame.pack(); // it is necessary to force the rectangle size computation
            frame.setVisible(true);
        });
    }

    private static Texts computeAngles(List<LineString> boundaries) {
        List<BoundaryAngle> angles = Flatten.flatten(BoundaryAngle.boundaryAngles(boundaries));
        Texts texts = new Texts();
        for (BoundaryAngle angle : angles) {
            String label = String.format("%.2f", Math.toDegrees(angle.angle));
            Coordinate c = angle.coord;

            texts.addText(label, c.x, c.y);
        }

        return texts;
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

    private static void addPoints(List<Point> points, CompleteRenderer content, Color color, double scaling) {
        Points rpoints = new Points();
        rpoints.setColor(color);
        rpoints.setScaling(scaling);

        for(Point p : points) {
            rpoints.addPoint(p.getX(), p.getY());
        }

        content.add(rpoints);
    }

    private static void addTexts(Texts texts, CompleteRenderer content) {
        for(Text label : texts.labels) {
            content.add(label);
        }
    }

    private static void addIndices(List<LineString> boundaries, CompleteRenderer content, Color color, int fontsize) {

        for(LineString boundary: boundaries) {
            Coordinate[] coords = boundary.getCoordinates();
            int l = coords.length - 1;
            Texts texts = new Texts(fontsize, Font.PLAIN, color);
            {
                int i = 0;
                String text = "  " + ShapeUtils.id(boundary);
                texts.addText(text, coords[i].x, coords[i].y);
            }
            for (int i=1; i<l; ++i) {
                texts.addText(String.valueOf(i), coords[i].x, coords[i].y);
            }
            addTexts(texts, content);
        }
    }

}
