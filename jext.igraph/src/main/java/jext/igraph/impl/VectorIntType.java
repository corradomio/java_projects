package jext.igraph.impl;

import com.sun.jna.Structure;

@Structure.FieldOrder({"stor_begin", "stor_end", "end"})
public class VectorIntType extends Structure {
    // public int[] stor_begin = new int[1];
    // public int[] stor_end = new int[1];
    // public int[] end = new int[1];
    public long stor_begin;
    public long stor_end;
    public long end;
}
