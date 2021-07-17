package jext.jgrapht.igraph;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class igraph_t extends Structure {
    public int n;
    public int directed;
    public igraph_vector_t from;
    public igraph_vector_t to;
    public igraph_vector_t oi;
    public igraph_vector_t ii;
    public igraph_vector_t os;
    public igraph_vector_t is;
    public Pointer attr;
}
