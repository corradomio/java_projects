package org.hls.check.misc;

import jext.jgrapht.Graphs;
import jext.jgrapht.edges.DirectedEdge;
import jext.jgrapht.util.GraphDescribe;
import org.jgrapht.Graph;

public class App4 {

    public static void main(String[] args) {
        Graph<Integer, DirectedEdge> g = Graphs.newGraph(Integer.class, DirectedEdge.class);
        GraphDescribe.describe(g);
    }
}
