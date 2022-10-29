package jext.graph;

public class GraphDatabaseException extends Error {

    public GraphDatabaseException(String msg) {
        super(msg);
    }

    public GraphDatabaseException(String msg, Exception e) {
        super(msg, e);
    }

    public GraphDatabaseException(Exception e) {
        super(e);
    }
}
