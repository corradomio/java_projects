package org.hls.example;

import jext.geotools.BoundaryExpander;
import jext.geotools.BoundaryIntersections;
import jext.geotools.BoundaryOffsets;
import jext.geotools.ClusterBoundaryGenerator;
import jext.geotools.ExpanderOffsets;
import jext.geotools.GeometrySelector;
import jext.geotools.GeometryType;
import jext.geotools.OutwardDirections;
import jext.geotools.PolygonClusterer;
import jext.geotools.PolygonSelector;
import jext.jplotter.canvas.JPlotterFrame;
import jext.jplotter.renderables.Lines;
import jext.jplotter.renderers.CompleteRenderer;
import jext.util.concurrent.Flatten;
import org.geotools.api.data.FileDataStore;
import org.geotools.api.data.FileDataStoreFinder;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Polygon;

import javax.swing.SwingUtilities;
import java.awt.Color;
import java.io.File;
import java.util.List;

public class ShapePlotv2 {

    private static final File EBTIC_ROOT = new File("D:\\Projects.ebtic\\project.ftth");

    public static void main(String[] args) throws Exception {
        // System.setProperty("org.lwjgl.librarypath", "D:\\Projects.github\\java_projects\\lwjgl\\bin-3.3.6");

        JPlotterFrame frame = new JPlotterFrame("Example Viz");

        CompleteRenderer content = frame.getContent();

        File polygonsFile =
            // new File(EBTIC_ROOT, "EbticDATA/Dubai/Jumeirah Village/PARCEL.shp")
            new File(EBTIC_ROOT, "EbticDATA/Etisalat/Greenfield/NorthernEmirates/From Hashim/yasmeenShape/yasmeen.shp")
            // new File(EBTIC_ROOT, "EbticDATA/Etisalat/Greenfield/NorthernEmirates/From Hashim/ALSEYOUH2468/revised/Al_Seyouh_2468.shp")
            // new File(EBTIC_ROOT, "EbticDATA/Etisalat/Greenfield/NorthernEmirates/From Hashim/ZUBAIR/ZUBAIR.shp")
        ;

        FileDataStore store = FileDataStoreFinder.getDataStore(polygonsFile);
        List<Polygon> allPolygons = (List<Polygon>) GeometrySelector.selectGeometries(store, GeometryType.POLYGON, true);
        System.out.printf("All polygons: %d\n", allPolygons.size());

        // Select polygons having minimumArea
        List<Polygon> polygons = PolygonSelector.selectPolygons(allPolygons, 0);
        System.out.printf("Selected polygons: %d\n", polygons.size());

        List<List<Polygon>> clusters = PolygonClusterer.clusterPolygons(polygons, 4.);
        System.out.printf("Polygon clusters: %d\n", clusters.size());

        List<LineString> boundaries = ClusterBoundaryGenerator.createBoundaries(clusters, 20.0);
        System.out.printf("Cluster boundaries: %d\n", boundaries.size());

        // List<LineString> expanded = BoundaryExpander.boundaryExpand(boundaries, 1.5);
        // System.out.printf("Expanded boundaries: %d\n", expanded.size());

        List<OutwardDirections> outwardsDirections = OutwardDirections.outwardDirections(boundaries);
        System.out.printf("Outward directions: %d\n", outwardsDirections.size());

        //List<LineString> intersections = BoundaryIntersection.boundaryIntersections(boundaries, outwardDirections, 50, 10);
        List<BoundaryIntersections> boundariesIntersections = BoundaryIntersections.boundaryIntersections(outwardsDirections, 100, 10);
        System.out.printf("Outward intersections: %d\n", boundariesIntersections.size());

        // compute the boundary offsets
        List<BoundaryOffsets> boundariesOffsets = BoundaryOffsets.assignBoundaryOffsets(boundariesIntersections, new ExpanderOffsets().initialize());
        System.out.printf("Boundary offsets maps: %d\n", boundariesOffsets.size());

        //List<LineString> expanded = BoundaryExpander.boundaryExpandSame(intersections, 1.5);
        List<BoundaryExpander> boundariesExpanded = BoundaryExpander.boundaryExpand(boundariesOffsets, 10);
        System.out.printf("Expanded boundaries: %d\n", boundariesExpanded.size());

        List<LineString> outwardsDirs = Flatten.flatten(outwardsDirections.stream().map(od -> List.of(od.allDrections)).toList());
        List<LineString> intersectionDirs = Flatten.flatten(boundariesIntersections.stream().map(od -> List.of(od.directions)).toList());
        List<LineString> expandedBounds = boundariesExpanded.stream().map(be -> be.expanded).toList();

        addPolygons(polygons, content, Color.GRAY, 1);
        addLines(boundaries, content, Color.BLUE, 1);
        addLines(outwardsDirs, content, Color.RED, 3);
        addLines(intersectionDirs, content, Color.GREEN, 1);
        addLines(expandedBounds, content, Color.RED, 1);

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
