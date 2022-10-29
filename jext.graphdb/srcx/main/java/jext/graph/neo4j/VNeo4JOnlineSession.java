package jext.graph.neo4j;

public class VNeo4JOnlineSession extends Neo4JOnlineSession {

    private final String refId;
    private final String model;
    private final int revision;

    public VNeo4JOnlineSession(VNeo4JOnlineDatabase graphdb, String refId, String model, int revision) {
        super(graphdb);
        this.refId = refId;
        this.model = model;
        this.revision = revision;
    }
}
