package org.hls.examples;

import jext.jgrapht.Graphs;
import org.jgrapht.Graph;

public class Main {

    public static void main(String[] args) {

        Graph<Integer, String> g = Graphs.newGraph(Integer.class, String.class);

        String e = Graphs.addEdge(g, 1, 2);

        Integer s = g.getEdgeSource(e);
        Integer t = g.getEdgeTarget(e);

        System.out.println(e);

    }
}
