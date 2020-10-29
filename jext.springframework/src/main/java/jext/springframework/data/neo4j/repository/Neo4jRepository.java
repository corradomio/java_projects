package jext.springframework.data.neo4j.repository;

import java.io.Serializable;

public interface Neo4jRepository<T, ID extends Serializable> extends
        org.springframework.data.neo4j.repository.Neo4jRepository<T, ID> {

}
