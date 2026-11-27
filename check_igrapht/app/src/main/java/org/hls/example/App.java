package org.hls.example;

import jext.igrapht.Graphs;
import org.jgrapht.Graph;

public class App {

    public static void main(String[] args) {
        // System.setProperty("igraph.library", "D:\\Projects.github\\java_projects\\jext.igrapht-core\\win32-x86-64\\libigraph.dll");
        Graph<Integer, String> g = Graphs.newGraph(Integer.class, String.class);

        String e = Graphs.addEdge(g, 1, 2);

        Integer s = g.getEdgeSource(e);
        Integer t = g.getEdgeTarget(e);

        System.out.println(g);
    }
}
