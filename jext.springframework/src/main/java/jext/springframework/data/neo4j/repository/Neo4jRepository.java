package jext.springframework.neo4j.repository;

import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface Neo4jRepository<T, ID extends Serializable>
        extends org.springframework.data.neo4j.repository.Neo4jRepository<T, ID> {

}
