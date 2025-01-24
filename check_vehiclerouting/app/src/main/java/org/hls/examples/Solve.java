package org.hls.examples;

import jext.jgrapht.Graphs;
import jext.jgrapht.edges.DirectedEdge;
import jext.jgrapht.edges.Edge;
import jext.jgrapht.edges.WeighedDirectedEdge;
import jext.jgrapht.edges.WeightedEdge;
import jext.jgrapht.nio.adjacent.EdgesGraphImporter;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.locationtech.jts.edgegraph.EdgeGraph;

import java.io.File;
import java.util.List;

public class Solve {

    public static void main(String[] args) throws Exception {
        List<Location> locations = Location.load(new File("locations.csv"));
        List<Location> toVisit = Location.load(new File("locations_to_visit.csv"));
        List<Vehicle> vehicles = Vehicle.load(new File("vehicles.csv"));

        PlanarGraph pg = PlanarGraph.of(locations);
        pg.save(new File("uae-edges.csv"));

        // Distance d = Distance.distanceMatrix(locations);

        EdgesGraphImporter egi = new EdgesGraphImporter()
            .withSkipLines(1)
            .withSeparator(",")
            .withToVertex(s -> Integer.parseInt((String)s))
        ;

        Graph<Integer, WeighedDirectedEdge> g = Graphs.newGraph(false, true, Integer.class, WeighedDirectedEdge.class);

        egi.importGraph(
            g,
            new File("uae-edges.csv")
        );

        for(WeighedDirectedEdge e : g.edgeSet())
            System.out.printf("%s: %f\n", e, e.getWeight());

        System.out.println("Done");
    }
}
