package jext.springframework.data.neo4j.repository;

import jext.springframework.data.cypherdsl.CypherdslStatementExecutor;
import jext.springframework.data.cypherdsl.Neo4jOgmSessionExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface Neo4jRepository<T, ID extends Serializable>
        extends org.springframework.data.neo4j.repository.Neo4jRepository<T, ID>
        , CypherdslStatementExecutor<T>
        , Neo4jOgmSessionExecutor<T, ID>
{
    /**
     * Just a method for test
     */
    String getVersion();
}
