package jext.springframework.data.neo4j.repository.support;

import jext.springframework.data.neo4j.repository.Neo4jRepository;
import jext.springframework.data.neo4j.repository.Neo4jSpecificationExecutor;

import java.io.Serializable;

public interface Neo4jRepositoryImplementation<T, ID extends Serializable>
        extends Neo4jRepository<T, ID>, Neo4jSpecificationExecutor<T> {

}
