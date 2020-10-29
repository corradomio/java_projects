package jext.springframework.data.neo4j.repository.support;

import jext.springframework.data.neo4j.repository.Neo4jRepository;
import org.neo4j.cypherdsl.core.Cypher;
import org.neo4j.cypherdsl.core.ExposesReturning;
import org.neo4j.cypherdsl.core.Expression;
import org.neo4j.cypherdsl.core.FunctionInvocation;
import org.neo4j.cypherdsl.core.Functions;
import org.neo4j.cypherdsl.core.Statement;
import org.neo4j.cypherdsl.core.renderer.Renderer;
import org.neo4j.ogm.session.Session;
import jext.springframework.data.cypherdsl.CypherdslPredicateExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.neo4j.repository.support.SimpleNeo4jRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collections;
import java.util.Optional;


// @Repository
public class CypherdslNeo4jRepository<T, ID extends Serializable> extends SimpleNeo4jRepository<T, ID>
        implements CypherdslPredicateExecutor<T>, Neo4jRepository<T, ID> {

    private static Renderer cypherRenderer = Renderer.getDefaultRenderer();

    private final Class<T> domainClass;
    private final Session session;

    public CypherdslNeo4jRepository(Class<T> domainClass, Session session) {
        super(domainClass, session);

        this.domainClass = domainClass;
        this.session = session;
    }

    private Class<T> getDomainClass() {
        return domainClass;
    }

    // --

    @Override
    public Optional<T> findOne(ExposesReturning noReturn, String variable) {
        Statement statement = noReturn.returning(variable).limit(1).build();
        String cypher = cypherRenderer.render(statement);

        T result = session.queryForObject(getDomainClass(), cypher, Collections.emptyMap());
        return Optional.ofNullable(result);
    }

    @Override
    public Iterable<T> findAll(ExposesReturning noReturn, String variable) {
        Statement statement = noReturn.returning(variable).build();
        String cypher = cypherRenderer.render(statement);

        return session.query(getDomainClass(), cypher, Collections.emptyMap());
    }

    @Override
    public Iterable<T> findAll(ExposesReturning noReturn, String variable, Sort sort) {
        Expression orderBy = OrderBy.of(sort);
        Statement statement = noReturn.returning(variable).orderBy(orderBy).skip(0);
        String cypher = cypherRenderer.render(statement);

        return session.query(getDomainClass(), cypher, Collections.emptyMap());
    }

    @Override
    public Page<T> findAll(ExposesReturning noReturn, String variable, Pageable pageable) {
        return null;
    }

    @Override
    public long count(ExposesReturning noReturn, String variable) {
        FunctionInvocation count = Functions.count(Cypher.anyNode(variable));
        Statement statement = noReturn.returning(count).build();
        String cypher = cypherRenderer.render(statement);

        return (Long)session.queryForObject(getDomainClass(), cypher, Collections.emptyMap());
    }

    @Override
    public boolean exists(ExposesReturning noReturn, String variable) {
        return count(noReturn, variable) > 0;
    }

}
