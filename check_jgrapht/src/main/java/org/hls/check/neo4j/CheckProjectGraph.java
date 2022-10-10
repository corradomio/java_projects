package org.hls.check.neo4j;

import jext.jgrapht.Graphs;
import jext.jgrapht.edges.DirectedEdge;
import jext.jgrapht.nio.neo4j.Neo4JGraphImporter;
import jext.jgrapht.nio.neo4j.Neo4JGraphImporterFactory;
import jext.jgrapht.nio.neo4j.Neo4JInfo;
import jext.jgrapht.util.GraphDescribe;
import org.jgrapht.Graph;

public class CheckProjectGraph {

    public static void main(String[] args) {
        Neo4JInfo info = Neo4JInfo.of("bolt://localhost:7687", "neo4j", "password", "90dda3c7");

        Neo4JGraphImporter imp = Neo4JGraphImporterFactory.create(info, "source");

        Graph<Integer, DirectedEdge> g = Graphs.newGraph(Integer.class, DirectedEdge.class);
        imp.importGraph(g);

        GraphDescribe.describe(g);
    }
}
