package jext.jgrapht.vertices;

import jext.jgrapht.edges.EdgeInfo;

import java.util.BitSet;
import java.util.List;
import java.util.Set;

public class VertexInfo<V> {

    // const values
    public V vertex;                    // vertex
    public int index;                   // unique index, starting from 0
    public int degree = -1;             // vertex degree
    public Set<V> neighbor;             // neighborhood
    public List<VertexInfo<V>> ninfos;  // neighbor vertex infos
    public List<EdgeInfo<V>>   einfos;  // neighbor edges infos

    // changing values
    public int color;                   // vertex color
    public int saved;                   // saved color
    public BitSet ncolors;              // neighbor colors

    public void updateNColors() {
        ncolors.clear();
        ninfos.forEach(v -> {
            ncolors.set(v.color);
        });
    }

    @Override
    public int hashCode() {
        return vertex.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        VertexInfo<V> that = (VertexInfo<V>) obj;
        return this.vertex.equals(that.vertex);
    }

    @Override
    public String toString() {
        return String.format("%s {d:%d, c:%d}", vertex, degree, color);
    }
}
