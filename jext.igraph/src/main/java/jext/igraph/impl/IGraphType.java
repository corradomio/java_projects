package jext.igraph.impl;

import com.sun.jna.Structure;

@Structure.FieldOrder({"n", "directed", "from","to", "oi", "ii", "os", "is", "attr", "cache"})
public class IGraphType extends Structure {
    public int n;
    public int directed;
    public VectorIntType from;
    public VectorIntType to;
    public VectorIntType oi;
    public VectorIntType ii;
    public VectorIntType os;
    public VectorIntType is;
    public long attr;
    public long cache;
}
