package jext.jgrapht.vertices;

import java.util.HashMap;
import java.util.Map;

public class Vertex<T> {

    private final T id;
    private final Map<String, Object> props = new HashMap<>();

    public Vertex(T id) {
        this.id = id;
    }

    public T id() {
        return id;
    }

    public Vertex<T> put(String name, Object value) {
        this.props.put(name, value);
        return this;
    }

    public Vertex<T> putAll(Map<String, Object> props) {
        this.props.putAll(props);
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        Vertex<T> that = (Vertex<T>) obj;
        return this.id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public String toString() {
        return String.format("v%s", this.id.toString());
    }

}
