package jext.jgrapht.nio.neo4j;

import static jext.jgrapht.nio.neo4j.Neo4JGraphImporter.NEO4J_PASSWORD;
import static jext.jgrapht.nio.neo4j.Neo4JGraphImporter.NEO4J_URI;
import static jext.jgrapht.nio.neo4j.Neo4JGraphImporter.NEO4J_USERNAME;
import static jext.jgrapht.nio.neo4j.Neo4JGraphImporter.REF_ID;

public class Neo4JGraphImporterFactory {

    private static final String CYPHER_VERTICES = "MATCH (s:%s {refId:$refId}) RETURN id(s) AS s";
    private static final String CYPHER_EDGES = "MATCH (s:%1$s {refId:$refId}) -[:uses]-> (t:%1$s) RETURN id(s) AS s, id(t) AS t";
    private static final String CYPHER_LAYER_VERTICES = "MATCH (s {refId:$refId}) WHERE (labels(s)[0]) in [$stype, $ttype]  RETURN id(s) AS s";
    private static final String CYPHER_LAYER_EDGES = "MATCH (s:%1$s {refId:$refId}) -[:uses]-> (t:%2$s) RETURN id(s) AS s, id(t) AS t";

    public static Neo4JGraphImporter create(Neo4JInfo info, String vtype) {
        Neo4JGraphImporter importer = new Neo4JGraphImporter();
        importer.vertices(String.format(CYPHER_VERTICES, vtype));
        importer.edges(String.format(CYPHER_EDGES, vtype));
        importer.labels("s", "t");
        importer.parameters(
                NEO4J_URI, info.getUrl(),
                NEO4J_USERNAME, info.getUsername(),
                NEO4J_PASSWORD, info.getPassword(),
                REF_ID, info.getRefId()
        );

        return importer;
    }

    public static Neo4JGraphImporter create(Neo4JInfo info, String stype, String ttype) {
        Neo4JGraphImporter importer = new Neo4JGraphImporter();
        importer.vertices(String.format(CYPHER_LAYER_VERTICES, stype, ttype));
        importer.edges(String.format(CYPHER_LAYER_EDGES, stype, ttype));
        importer.labels("s", "t");
        importer.parameters(
                NEO4J_URI, info.getUrl(),
                NEO4J_USERNAME, info.getUsername(),
                NEO4J_PASSWORD, info.getPassword(),
                REF_ID, info.getRefId(),
                "stype", stype,
                "ttype", ttype
        );

        return importer;
    }
}
