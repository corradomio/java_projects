package jext.springframework.data.neo4j.repository.support;

import jext.springframework.data.neo4j.repository.Neo4jRepository;
import org.neo4j.ogm.session.Session;

import java.io.Serializable;


public class SimpleNeo4jRepository<T, ID extends Serializable>
        extends org.springframework.data.neo4j.repository.support.SimpleNeo4jRepository<T, ID>
        implements Neo4jRepository<T, ID> {

    private final Class<T> domainClass;
    private final Session session;

    public SimpleNeo4jRepository(Class<T> domainClass, Session session) {
        super(domainClass, session);

        this.domainClass = domainClass;
        this.session = session;
    }

    private Class<T> getDomainClass() {
        return domainClass;
    }

}
