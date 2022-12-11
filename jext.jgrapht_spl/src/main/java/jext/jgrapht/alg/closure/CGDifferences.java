package jext.jgrapht.alg.closure;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CGDifferences<V> {

    // ----------------------------------------------------------------------
    // Local data structure
    // ----------------------------------------------------------------------

    public static class Diff<V> {
        private final Set<V> added = new HashSet<>();
        private final Set<V> removed = new HashSet<>();
        private final Set<V> changed = new HashSet<>();
        private final Map<V, Diff<V>> changes = new HashMap<>();

        public void added(Collection<V> ids) {
            added.addAll(ids);
        }
        public void removed(Collection<V> ids) {
            removed.addAll(ids);
        }
        public Diff<V> changed(V id) {
            if (!changed.contains(id)) {
                changed.add(id);
                changes.put(id, new Diff<V>());
            }
            return changes.get(id);
        }

        public boolean isEmpty() {
            return added.isEmpty() && removed.isEmpty() && changed.isEmpty();
        }

        public Set<V> added() {
            return added;
        }

        public Set<V> removed() {
            return removed;
        }

        public Set<V> changed() {
            return changed;
        }

        public Map<V, Diff<V>> changes() {
            return changes;
        }
        public Diff<V> changes(V v) {
            return changes.get(v);
        }
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private final Diff<V> vertices = new Diff<>();
    private final Diff<ClosuresGraph.Edge<V>> edges = new Diff<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public boolean isEmpty() {
        return vertices.isEmpty() && edges.isEmpty();
    }

    public Diff<V> vertices() {
        return vertices;
    }

    public Diff<ClosuresGraph.Edge<V>> edges() {
        return edges;
    }

    // ----------------------------------------------------------------------
    // Utility
    // ----------------------------------------------------------------------

    public void dump() {
        System.out.printf("Vertices\n");
        System.out.printf("    added: %s\n", vertices.added);
        System.out.printf("  removed: %s\n", vertices.removed);
        System.out.printf("  changed: %s\n", vertices.changed);
        for(V v : vertices.changed) {
            System.out.printf("   %s\n", v);
            System.out.printf("        added: %s\n", vertices.changed(v).added);
            System.out.printf("      removed: %s\n", vertices.changed(v).removed);
        }
        System.out.printf("Edges\n");
        System.out.printf("    added: %s\n", edges.added);
        System.out.printf("  removed: %s\n", edges.removed);
        System.out.printf("End\n");
    }
}
