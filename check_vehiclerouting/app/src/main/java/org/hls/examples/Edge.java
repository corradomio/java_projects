package org.hls.examples;

import java.util.Objects;

public class Edge {
    Location l1, l2;
    double length;

    public Edge(Location l1, Location l2) {
        if (l1.id > l2.id) {
            Location t = l1;
            l1 = l2;
            l2 = t;
        }
        this.l1 = l1;
        this.l2 = l2;
        this.length = l1.distance(l2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(l1.id, l2.id);
    }

    @Override
    public boolean equals(Object obj) {
        Edge that = (Edge) obj;
        return (this.l1.id == that.l1.id && this.l2.id == that.l2.id);
    }
}
