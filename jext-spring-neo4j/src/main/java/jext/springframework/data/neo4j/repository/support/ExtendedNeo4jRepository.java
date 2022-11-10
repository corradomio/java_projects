package jext.springframework.data.neo4j.repository.support;

import jext.springframework.data.cypherdsl.CypherdslStatementExecutor;
import jext.springframework.data.cypherdsl.Neo4jOgmSessionExecutor;
import jext.springframework.data.neo4j.repository.Neo4jRepository;
import org.neo4j.cypherdsl.core.Cypher;
import org.neo4j.cypherdsl.core.ExposesReturning;
import org.neo4j.cypherdsl.core.Expression;
import org.neo4j.cypherdsl.core.FunctionInvocation;
import org.neo4j.cypherdsl.core.Functions;
import org.neo4j.cypherdsl.core.Statement;
import org.neo4j.cypherdsl.core.renderer.Renderer;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.neo4j.repository.support.SimpleNeo4jRepository;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;


// @Repository
public class ExtendedNeo4jRepository<T, ID extends Serializable> extends SimpleNeo4jRepository<T, ID>
        implements Neo4jRepository<T, ID>
        , CypherdslStatementExecutor<T>
        , Neo4jOgmSessionExecutor<T>
{

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static final String VERSION = "1.0.0";
    private Logger logger;
    private static Renderer cypherRenderer = Renderer.getDefaultRenderer();
    private final Class<T> domainClass;
    private final Session session;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ExtendedNeo4jRepository(Class<T> domainClass, Session session) {
        super(domainClass, session);

        this.domainClass = domainClass;
        this.session = session;

        String loggerName = String.format("%s.%s", getClass().getName(), domainClass.getSimpleName());
        this.logger = LoggerFactory.getLogger(loggerName);

        logger.info("    Repository[" + domainClass.getSimpleName() + "]");
    }

    // ----------------------------------------------------------------------
    // Version
    // ----------------------------------------------------------------------

    @Override
    public String getVersion() {
        return VERSION;
    }

    // ----------------------------------------------------------------------
    // Neo4jOgmSessionExecutor<T, ID>
    // ----------------------------------------------------------------------

    @Override
    public Class<T> getDomainClass() {
        return domainClass;
    }

    @Override
    public Session getSession() {
        return session;
    }

    // ----------------------------------------------------------------------

    @Override
    public <U> U queryForObject(Class<U> objectType, String cypher, Map<String, ?> parameters) {
        return session.queryForObject(objectType, cypher, parameters);
    }

    @Override
    public <U> Iterable<U> query(Class<U> objectType, String cypher, Map<String, ?> parameters) {
        return session.query(objectType, cypher, parameters);
    }

    @Override
    public Result query(String cypher, Map<String, ?> parameters) {
        return session.query(cypher, parameters);
    }

    @Override
    public Result query(String cypher, Map<String, ?> parameters, boolean readOnly) {
        return session.query(cypher, parameters, readOnly);
    }

    // ----------------------------------------------------------------------
    // CypherdslStatementExecutor
    // ----------------------------------------------------------------------

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
        Expression orderBy = OrderBy.of(sort, variable);
        Statement statement = noReturn.returning(variable).orderBy(orderBy).skip(0);
        String cypher = cypherRenderer.render(statement);

        return session.query(getDomainClass(), cypher, Collections.emptyMap());
    }

    @Override
    public Page<T> findAll(ExposesReturning noReturn, String variable, Pageable pageable) {
        Statement statement;

        // ... RETURN n ORDER BY n.
        if (pageable.isPaged() && pageable.getSort().isSorted())
            statement = noReturn
                    .returning(variable)
                    .orderBy(OrderBy.of(pageable.getSort(), variable))
                    .skip(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .build();
        else if (pageable.isPaged())
            statement = noReturn
                    .returning(variable)
                    .skip(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .build();
        else if (pageable.getSort().isSorted())
            statement = noReturn
                    .returning(variable)
                    .orderBy(OrderBy.of(pageable.getSort(), variable))
                    .skip(0)
                    .build();
        else
            statement = noReturn.returning(variable)
                    .build();

        String cypher = cypherRenderer.render(statement);

        List<T> content = PageUtils.toList(session.query(getDomainClass(), cypher, Collections.emptyMap()));

        FunctionInvocation count = Functions.count(Cypher.anyNode(variable));
        statement = noReturn.returning(count).build();
        cypher = cypherRenderer.render(statement);

        long total = session.queryForObject(Long.class, cypher, Collections.emptyMap());

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public long count(ExposesReturning noReturn, String variable) {
        // count(n)
        FunctionInvocation count = Functions.count(Cypher.anyNode(variable));
        // ... RETURN count(n)
        Statement statement = noReturn.returning(count).build();
        String cypher = cypherRenderer.render(statement);

        return session.queryForObject(Long.class, cypher, Collections.emptyMap());
    }

    @Override
    public boolean exists(ExposesReturning noReturn, String variable) {
        return count(noReturn, variable) > 0;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
