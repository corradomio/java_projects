package org.hls.examples;

import hageldave.jplotter.misc.DefaultGlyph;
import jext.jplotter.canvas.JPlotterFrame;
import jext.jplotter.renderables.Legend;
import jext.jplotter.renderables.Lines;
import jext.jplotter.renderables.Points;
import jext.optim.domain.graph.Edge;
import jext.optim.domain.graph.SimpleGraph;
import jext.util.JSONUtils;
import org.delaunay.algorithm.Triangulation;
import org.delaunay.model.Triangle;

import javax.swing.SwingUtilities;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static jext.util.MathUtils.sqrt;
import static jext.util.MathUtils.sq;

public class CheckGraph {

    static class Vertex {
        double lon;
        double lat;
        String name;
        int id;

        Vertex(int id, double lon, double lat, String name) {
            this.lon = lon;
            this.lat = lat;
            this.name = name;
            this.id = id;
        }

        @Override
        public int hashCode() {
            return id;
        }

        @Override
        public boolean equals(Object obj) {
            Vertex that = (Vertex) obj;
            return this.id == that.id || this.name.equals(that.name);
        }
    }

    public static void main(String[] args) throws IOException, Triangulation.InvalidVertexException {
        Map<String, Object> data = (Map<String, Object>) JSONUtils.load(new File("locations_uk.json")).get("locations");
        Map<Integer, Vertex> vertices = new HashMap();

        Triangulation tri = new Triangulation();

        int id=0, n = data.size();
        for(Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            Map<String, Object> value = (Map<String, Object>) entry.getValue();
            double lon = (double) value.get("lon");
            double lat = (double) value.get("lat");

            Vertex v = new Vertex(id, lon, lat, key);

            vertices.put(id, v);
            tri.addVertex(id, lon, lat);
            id++;
        }

        tri.triangulate();

        SimpleGraph<Vertex> graph = new SimpleGraph<>();

        Set<Triangle> triangles = tri.getTriangles();
        for (Triangle triangle : triangles) {

            triangle.getEdges().forEach(edge -> {
                int ia = edge.a.getVertexIndex();
                int ib = edge.b.getVertexIndex();

                Vertex va = vertices.get(ia);
                Vertex vb = vertices.get(ib);
                double dist = sqrt(sq(va.lat - vb.lat) + sq(va.lon - vb.lon));
                if (dist < 0.45)
                    graph.addEdge(va, vb, dist);
            });
        }

        JPlotterFrame frame = new JPlotterFrame("Example Viz");
        frame.setSize(1000*5/6, 1000);
        // frame.getCanvas().setRenderer(coordsys);
        // frame.getCanvas().getPoints().setColor(Color.ORANGE);

        int sineColor = 0xff66c2a5;
        Points points = new Points().setGlyps(DefaultGlyph.CIRCLE_F).setColor(sineColor).setName("points").setScaling(.5);

        for (Vertex vertex : graph.vertexSet()) {
            points.addPoint(vertex.lon, vertex.lat);
        }

        Lines lines = new Lines().setColor(Color.lightGray).setName("lines").setThickness(1);

        for (Edge<Vertex> edge : graph.edgeSet()) {
            Vertex s = graph.getEdgeSource(edge);
            Vertex t = graph.getEdgeTarget(edge);
            if (s.lon == 0 || s.lat == 0 || t.lon == 0 || t.lat == 0) {
                continue;
            }
            lines.addLineStrip(s.lon, s.lat, t.lon, t.lat);
        }


        frame.getCanvas().getContent().addAll(lines, points);
        frame.getCanvas()
            .getCoordSysRenderer()
            .setAutomaticBounds(true)
            .setAspectRatio(1.5)
            .setMarginView(.1, .1)
            .setLegend(new Legend().addAll(
                points,
                lines
            ), 80, false)
            .compose()
        ;

        SwingUtilities.invokeLater(()->{
            frame.setVisible(true);
        });


        System.out.println(graph);
    }
}
