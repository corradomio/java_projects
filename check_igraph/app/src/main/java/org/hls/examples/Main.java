package org.hls.examples;

import jext.jgrapht.Graphs;
import org.antlr.v4.runtime.misc.ObjectEqualityComparator;
import org.jgrapht.Graph;

public class Main {

    public static void main(String[] args) {

        Object o1 = new Object();
        Object o2 = new Object();
        System.out.println(o1.equals(o2));

        Graph<Integer, Integer> g = Graphs.newGraph(Integer.class, Integer.class);

        Integer e = Graphs.addEdge(g, 1, 2);

        Integer s = g.getEdgeSource(e);
        Integer t = g.getEdgeTarget(e);

        System.out.println(e);

    }
}
