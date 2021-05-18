package jext.util;

/*
    https://stackoverflow.com/questions/1690953/transitive-reduction-algorithm-pseudocode
    https://www.ncbi.nlm.nih.gov/pmc/articles/PMC4009766/
 */

import java.util.HashSet;
import java.util.Set;

public class AdjacentMatrix {

    public interface Callable {
        void call(String i, String j);
    }

    private class Edge {
        private String i, j;
        private int hc;

        Edge(String i, String j) {
            this.i = i;
            this.j = j;
            this.hc = String.format("%s,%s", i, j).hashCode();
        }

        public boolean equals(Object other) {
            Edge that = (Edge) other;
            return i.equals(that.i) && j.equals(that.j);
        }

        public int hashCode() {
            return hc;
        }
    }

    private Set<String> vertices = new HashSet<String>();
    private Set<Edge> edges = new HashSet<>();

    public AdjacentMatrix() { }

    public AdjacentMatrix add(String i, String j) {
        vertices.add(i);
        vertices.add(j);
        edges.add(new Edge(i, j));
        return this;
    }

    public boolean areAdjacent(String i, String j) {
        return edges.contains(new Edge(i, j));
    }

    public AdjacentMatrix remove(String i, String j) {
        edges.remove(new Edge(i, j));
        return this;
    }

    public void transitiveReduction(Callable reductor) {
        for(String i : vertices)
            for (String j : vertices)
                if (areAdjacent(i, j))
                    for (String k : vertices)
                        if (areAdjacent(j, k)) {
                            remove(j, k);
                            reductor.call(j, k);
                        }
    }
}
