package jext.graph.neo4j;

import jext.graph.GraphSession;

public interface VGraphSession extends GraphSession {

    boolean deleteNode(String nodeType, String nodeId);
    boolean deleteEdge(String edgeType, String edgeId);

}
