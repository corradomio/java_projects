package jext.springframework.data.neo4j.repository.support;

import java.io.Serializable;

public interface Neo4jRepository<T, ID extends Serializable> extends Neo4jRepositoryImplementation<T, ID> {
}
