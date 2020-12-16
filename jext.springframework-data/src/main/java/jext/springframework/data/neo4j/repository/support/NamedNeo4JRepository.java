package jext.springframework.data.neo4j.repository.support;

import jext.springframework.data.NamedQueriesRegistry;
import jext.springframework.data.cypherdsl.NamedQueryStatementExecutor;
import jext.springframework.data.query.Query;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.support.SimpleNeo4jRepository;

import java.io.Serializable;
import java.lang.reflect.MalformedParametersException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class NamedNeo4JRepository<T, ID extends Serializable> extends SimpleNeo4jRepository<T, ID>
    implements Neo4jRepository<T, ID>, NamedQueryStatementExecutor<T> {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static final String VERSION = "1.0.0";
    private Logger logger;
    private final Class<T> domainClass;
    private final Session session;

    @Autowired
    private NamedQueriesRegistry namedQueries;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public NamedNeo4JRepository(Class<T> domainClass, Session session) {
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

    // @Override
    public String getVersion() {
        return VERSION;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    // @Override
    public Class<T> getDomainClass() {
        return domainClass;
    }

    // @Override
    public Session getSession() {
        return session;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public long countUsing(String queryName, Object ... args) {
        String cypher = namedQueries.get(queryName);
        Map<String, ?> parameters = toParameters(args);
        return session.queryForObject(Long.class, cypher, parameters);
    }

    public long countUsing(String queryName, Map<String, ?> parameters) {
        String cypher = namedQueries.get(queryName);
        return session.queryForObject(Long.class, cypher, parameters);
    }

    public boolean existsUsing(String queryName, Map<String, ?> parameters) {
        String cypher = namedQueries.get(queryName);
        return session.queryForObject(Boolean.class, cypher, parameters);
    }

    public Optional<T> findOneUsing(String queryName, Map<String, ?> parameters) {
        String cypher = namedQueries.get(queryName);
        T value = session.queryForObject(getDomainClass(), cypher, parameters);
        return Optional.ofNullable(value);
    }

    public Iterable<T> findAllUsing(String queryName, Map<String, ?> parameters) {
        String cypher = namedQueries.get(queryName);
        return session.query(getDomainClass(), cypher, parameters);
    }

    private Map<String, ?> toParameters(Object[] args) {
        if (args == null || args.length == 0)
            return Collections.emptyMap();
        if (args.length %2 != 0)
            throw new MalformedParametersException("Not an even number of parameters");

        Map<String, Object> parameters = new HashMap<>();
        for(int i=0; i<args.length; i+= 2) {
            String name  = args[i].toString();
            Object value = args[i+1];
            parameters.put(name, value);
        }

        return parameters;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public Query<T> queryUsing(String queryName, Object ... args)  {
        return queryUsing(queryName, toParameters(args));
    }

    public Query<T> queryUsing(String queryName, Map<String, ?> parameters)  {
        String cypher = namedQueries.get(queryName);
        return new PartialQuery<T, ID>(this, cypher, parameters);
    }

    // ----------------------------------------------------------------------

    long countUsingCypher(String cypher, Map<String, ?> parameters) {
        return session.queryForObject(Long.class, cypher, parameters);
    }

    String findIdUsingCypher(String cypher, Map<String, ?> parameters) {
        return session.queryForObject(String.class, cypher, parameters);
    }

    Iterable<String> findIdsUsingCypher(String cypher, Map<String, ?> parameters) {
        return session.query(String.class, cypher, parameters);
    }

    Iterable<T> findAllUsingCypher(String cypher, Map<String, ?> parameters) {
        return session.query(getDomainClass(), cypher, parameters);
    }

    long deleteUsingCypher(String cypher, Map<String, ?> parameters) {
        Result result = session.query(cypher, parameters);
        return 0;
    }

}
