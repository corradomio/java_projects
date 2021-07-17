package jext.jgrapht.igraph;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class igraph_vector_t extends Structure {
    public Pointer stor_begin;
    public Pointer stor_end;
    public Pointer end;
}
