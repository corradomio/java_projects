package jext.graph;

public class GraphDatabaseException extends Error {

    public GraphDatabaseException(String msg) {
        super(msg);
    }

    public GraphDatabaseException(String msg, Throwable t) {
        super(msg, t);
    }

    public GraphDatabaseException(Throwable t) {
        super(t);
    }
}
