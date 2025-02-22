package org.hls.examples;

import jext.jgrapht.Graphs;
import jext.jgrapht.edges.WeighedDirectedEdge;
import jext.jgrapht.nio.adjacent.EdgesGraphImporter;
import jext.jgrapht.nio.json.JSONGraphExporter;
import org.jgrapht.Graph;
import org.jgrapht.nio.GraphExporter;
import org.jgrapht.nio.GraphImporter;

import java.io.File;
import java.util.List;

public class SaveGraph {

    public static void main(String[] args) throws Exception {
        List<Location> locations = Location.load(new File("locations.csv"));
        List<Location> toVisit = Location.load(new File("locations_to_visit.csv"));
        List<Vehicle> vehicles = Vehicle.load(new File("vehicles.csv"));

        PlanarGraph pg = PlanarGraph.of(locations, 6.0);
        pg.save(new File("uae-edges.csv"));

        // Distance d = Distance.distanceMatrix(locations);

        GraphImporter egi = new EdgesGraphImporter()
            .withSkipLines(1)
            .withSeparator(",")
            .withToVertex(s -> Integer.parseInt((String)s))
        ;

        Graph<Integer, WeighedDirectedEdge> g = Graphs.newGraph(Integer.class, WeighedDirectedEdge.class);

        egi.importGraph(
            g,
            new File("uae-edges.csv")
        );

        for(WeighedDirectedEdge e : g.edgeSet())
            System.out.printf("%s: %f\n", e, e.getWeight());

        GraphExporter<Integer, WeighedDirectedEdge> ge = new JSONGraphExporter<>();
        ge.exportGraph(g, new File("uae-edges.json"));


        System.out.println("Done");
    }
}
