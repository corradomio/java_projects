package jext.jgrapht.vertices;

import java.util.HashMap;
import java.util.Map;

public class Vertex {

    private final String id;
    private final Map<String, Object> data = new HashMap<>();

    public Vertex(String id) {
        this.id = id;
    }

    // ----------------------------------------------------------------------

    public String id() {
        return id;
    }

    public Map<String, Object> data() {
        return data;
    }

    // ----------------------------------------------------------------------

    public Vertex put(String name, Object value) {
        this.data.put(name, value);
        return this;
    }

    public Vertex putAll(Map<String, Object> props) {
        this.data.putAll(props);
        return this;
    }

    // ----------------------------------------------------------------------

    @Override
    public boolean equals(Object obj) {
        Vertex that = (Vertex) obj;
        return this.id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public String toString() {
        return this.id;
    }

}
