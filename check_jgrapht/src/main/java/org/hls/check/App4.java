package org.hls.check;

import jext.jgrapht.Graphs;
import jext.jgrapht.edges.DirectedEdge;
import jext.jgrapht.util.GraphDump;
import org.jgrapht.Graph;

public class App4 {

    public static void main(String[] args) {
        Graph<Integer, DirectedEdge> g = Graphs.newGraph(Integer.class, DirectedEdge.class);
        GraphDump.describe(g);
    }
}
