package org.hls.check;

import jext.jgrapht.igraph.igraph_lib;
import jext.jgrapht.igraph.igraph_t;
import jext.jgrapht.igraph.igraph_vector_t;
import jext.logging.Logger;

public class App4 {

    public static void main(String[] args) {
        Logger.configure();

        igraph_vector_t v = new igraph_vector_t();

        igraph_t g = new igraph_t();
        igraph_lib l = igraph_lib.INSTANCE;

        l.igraph_empty(g, 100, 0);
        System.out.println(l.igraph_vcount(g));
        System.out.println(l.igraph_ecount(g));
        l.igraph_destroy(g);

    }
}
