package org.hls.examples;

import jext.jgrapht.Graphs;
import jext.jgrapht.edges.WeightedUndirectedEdge;
import org.delaunay.algorithm.Triangulation;
import org.delaunay.model.Triangle;
import org.delaunay.model.Vertex;
// import il.ac.idc.jdt.algorithm.Triangulation;
// import il.ac.idc.jdt.model.Triangle;
// import il.ac.idc.jdt.model.Vertex;


import org.hls.examples.utils.LatLonUtils;
import org.jgrapht.Graph;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PlanarGraph {

    public static PlanarGraph of(List<Location> locations, double maxDistance) throws Exception {
        Map<Integer, Location> lmap = new HashMap<>();

        locations.forEach(l -> {
            lmap.put(l.id, l);
        });

        return of(lmap, maxDistance);
    }

    private static PlanarGraph of(Map<Integer, Location> locations, double maxDistance) throws Exception {
        PlanarGraph graph = new PlanarGraph(locations);

        Triangulation t = new Triangulation();
        for (Location l : locations.values()) {
            t.addVertex(l.id(), l.x(), l.y());
        }

        t.triangulate();

        for (Triangle tri : t.getTriangles()) {
            List<Vertex> vertices = tri.getVertices().stream().toList();

            Vertex v1 = vertices.get(0);
            Vertex v2 = vertices.get(1);
            Vertex v3 = vertices.get(2);

            Location l1 = locations.get(v1.getVertexIndex());
            Location l2 = locations.get(v2.getVertexIndex());
            Location l3 = locations.get(v3.getVertexIndex());

            graph.add(l1, l2, maxDistance);
            graph.add(l2, l3, maxDistance);
            graph.add(l3, l1, maxDistance);
        }

        return graph;
    }

    private final Set<Edge> edges = new HashSet<>();
    private final Map<Integer, Location> locations;

    private PlanarGraph(Map<Integer, Location> locations) {
        this.locations = locations;
    }

    private void add(Location l1, Location l2, double maxDistance) {

        Edge e = new Edge(l1, l2);
        if (e.length == 0) return;
        if (maxDistance > 0 && e.length >= maxDistance) return;
        // if (edges.contains(e)) return;

        double d = LatLonUtils.distance(e.l1.latitude, e.l1.longitude, e.l2.latitude, e.l2.longitude);

        edges.add(e);
    }

    public List<Edge> getEdges() {
        return new ArrayList<>(edges);
    }

    public Graph<Location, WeightedUndirectedEdge> getGraph() {
        Graph<Location, WeightedUndirectedEdge> g = Graphs.newGraph(Location.class, WeightedUndirectedEdge.class);

        for(Location l : locations.values())
            g.addVertex(l);

        for (Edge e : edges)
            Graphs.addEdge(g, e.l1, e.l2, e.length);

        return g;
    }

    public int size() {
        return this.edges.size();
    }

    public void save(File file) {
        try (Writer fos = new FileWriter(file)) {
            fos.write("source,target,length\n");

            for (Edge e : edges) {
                int v1 = e.l1.id();
                int v2 = e.l2.id();
                double w = LatLonUtils.distance(e.l1.latitude, e.l1.longitude, e.l2.latitude, e.l2.longitude);

                fos.write(String.format("%d,%d,%f\n", v1, v2, w));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
