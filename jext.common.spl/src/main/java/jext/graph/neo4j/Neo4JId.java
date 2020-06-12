package jext.graph.neo4j;

import jext.graph.Id;

import java.io.Serializable;

public class Neo4JId implements Id, Serializable {

    private long id;

    Neo4JId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object other) {
        return id == ((Neo4JId) other).id;
    }

    @Override
    public boolean equals(Id that) {
        return id == ((Neo4JId) that).id;
    }


    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }

}
