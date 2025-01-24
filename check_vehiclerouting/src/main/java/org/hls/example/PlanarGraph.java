package org.hls.example;

import org.delaunay.algorithm.Triangulation;
import org.delaunay.model.Triangle;
import org.delaunay.model.Vertex;
import org.hls.example.utils.LatLonUtils;

import java.util.ArrayList;
import java.util.List;

public class PlanarGraph {

    public static PlanarGraph of(List<Location> locations) throws Triangulation.InvalidVertexException {
        PlanarGraph graph = new PlanarGraph(locations);

        Triangulation t = new Triangulation();
        for (Location l : locations) {
            t.addVertex(l.getId(), l.getX(), l.getY());
        }

        t.triangulate();

        for (Triangle tri : t.getTriangles()) {
            List<Vertex> vertices = tri.getVertices().stream().toList();

            Vertex v1 = vertices.get(0);
            Vertex v2 = vertices.get(1);
            Vertex v3 = vertices.get(2);

            graph.add(v1, v2).add(v2, v3).add(v3,v1);
        }

        return graph;
    }

    private final List<Edge> edges = new ArrayList<>();
    private final List<Location> locations;

    private PlanarGraph(List<Location> locations) {
        this.locations = locations;
    }

    private PlanarGraph add(Vertex v1, Vertex v2) {
        Location l1 = locations.get(v1.getVertexIndex());
        Location l2 = locations.get(v2.getVertexIndex());

        double dist = LatLonUtils.distance(l1.getX(), l1.getY(), l2.getX(), l2.getY());
        if (dist < 4)
            edges.add(new Edge(l1, l2));

        return this;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
