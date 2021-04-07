package jext.jgrapht.nio.neo4j;

import org.neo4j.driver.Session;

import java.util.Map;

public class Neo4JQuery {

    private Neo4JSession session;
    private String stmt;
    private String alias;
    private Map<String, Object> params;
    private boolean edge;

    public Neo4JQuery(Neo4JSession session, String alias, String stmt, Map<String, Object> p) {
        this.session = session;
        this.stmt = stmt;
        this.alias = alias;
        this.params = p;
    }

    Neo4JQuery edge() {
        edge = true;
        return this;
    }


    public GraphIterator<Map<String, Object>> allValues() { return allValues(alias); }


    public GraphIterator<Map<String, Object>> allValues(String alias) {
        String s;
            s = String.format("%s RETURN %s", stmt, alias);
        return session.retrieveAllIter(alias, s, params, edge);
    }

    public GraphIterator<Map<String, Object>> result() {
        String s = stmt;
        return session.resultIter(alias, s, params, edge);
    }

}
