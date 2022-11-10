package jext.springframework.data.neo4j.repository.support;

import jext.springframework.data.NamedQueriesRegistry;
import jext.springframework.data.cypherdsl.AdjacentQueryStatementExecutor;
import jext.springframework.data.cypherdsl.NamedQueryStatementExecutor;
import jext.springframework.data.query.Direction;
import jext.springframework.data.query.Edge;
import jext.springframework.data.query.Query;
import jext.springframework.data.query.QueryResult;
import jext.util.IterableUtils;
import jext.util.Parameters;
import jext.util.SetUtils;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.support.SimpleNeo4jRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.MalformedParametersException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@NoRepositoryBean
public class NamedNeo4JRepository<T, ID extends Serializable> extends SimpleNeo4jRepository<T, ID>
    implements Neo4jRepository<T, ID>, NamedQueryStatementExecutor<T>, AdjacentQueryStatementExecutor<T, ID>
    {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static final String VERSION = "1.0.0";
    private Logger logger;
    private final Class<T> domainClass;
    private Class idClass = Long.class;
    private final Session session;

    @Autowired private NamedQueriesRegistry namedQueries;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public NamedNeo4JRepository(Class<T> domainClass, Session session) {
        super(domainClass, session);

        this.domainClass = domainClass;
        this.session = session;

        String loggerName = String.format("%s.%s", getClass().getName(), domainClass.getSimpleName());
        this.logger = LoggerFactory.getLogger(loggerName);

        logger.info("    NamedRepository[" + domainClass.getSimpleName() + "]");
    }

    // ----------------------------------------------------------------------
    // Version
    // ----------------------------------------------------------------------

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

    public Query<T> queryUsing(String queryName, Object ... args)  {
        return queryUsing(queryName, toParameters(args));
    }

    public QueryResult executeUsing(String queryName, Object ... args)  {
        return executeUsing(queryName, toParameters(args));
    }

    public long countUsing(String queryName, Object ... args) {
        Map<String, ?> parameters = toParameters(args);
        return countUsing(queryName, parameters);
    }

    public boolean existsUsing(String queryName, Object ... args) {
        Map<String, ?> parameters = toParameters(args);
        return existsUsing(queryName, parameters);
    }

    public Optional<T> findOneUsing(String queryName, Object ... args) {
        Map<String, ?> parameters = toParameters(args);
        return findOneUsing(queryName, parameters);
    }

    public Iterable<T> findAllUsing(String queryName, Object ... args) {
        Map<String, ?> parameters = toParameters(args);
        return findAllUsing(queryName, parameters);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public Query<T> queryUsing(String queryName, Map<String, ?> parameters)  {
        String cypher = namedQueries.get(queryName);
        return new PartialQuery<T, ID>(this, cypher, parameters);
    }

    public QueryResult executeUsing(String queryName, Map<String, ?> parameters)  {
        String cypher = namedQueries.get(queryName);
        Result result = session.query(cypher, parameters);
        return new QueryResult(result);
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

    // ----------------------------------------------------------------------
    //
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

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private Map<String, ?> toParameters(Object[] args) {
        if (args == null || args.length == 0)
            return Collections.emptyMap();
        if (args.length %2 != 0)
            throw new MalformedParametersException("Not an even number of parameters");

        Map<String, Object> parameters = new HashMap<>();
        for(int i=0; i<args.length; i+=2) {
            String name  = args[i].toString();
            Object value = args[i+1];
            parameters.put(name, value);
        }

        return parameters;
    }

    // ----------------------------------------------------------------------
    // Edges
    // ----------------------------------------------------------------------

    private class IterableEdge implements Iterable<Edge>, Iterator<Edge> {
        Iterable<Map<String, Object>> iter;
        Iterator<Map<String, Object>> it;

        IterableEdge(Iterable<Map<String, Object>> iter) {
            this.iter = iter;
            this.it = this.iter.iterator();
        }

        @Override
        public Iterator<Edge> iterator() {
            return this;
        }

        @Override
        public boolean hasNext() {
            return this.it.hasNext();
        }

        @Override
        public Edge next() {
            Map<String, Object> map = it.next();
            return new Edge(map);
        }
    }

    @Override
    public Iterable<Edge> queryEdges(String edgeType, Collection<ID> ids, Map<String, Object> edgeProps) {

        Parameters parameters = new Parameters();
        parameters.prefix(E, edgeProps);
        parameters.put(IDS, ids.toArray());

        String cypher = String.format(
            //"MATCH (f) %s (n) WHERE id(f) IN $ids AND id(n) IN $ids RETURN e AS edge",
            "MATCH (f) %s (n) WHERE id(f) IN $ids AND id(n) IN $ids RETURN e",
            composeEdge(edgeType, Direction.Output, edgeProps));

        Result result = session.query(cypher, parameters);
        return new IterableEdge(result.queryResults());
        // return session.query(Edge.class, cypher, parameters);
    }


    // ----------------------------------------------------------------------
    // Adjacency
    // ----------------------------------------------------------------------

    private static final String N = "n";
    private static final String E = "e";
    private static final String IDS = "ids";

    @Override
    public Iterable<T> queryAdjacentNodes(
        ID fromId,
        String edgeType, Direction direction, boolean recursive,
        String nodeType, Map<String, Object> nodeProps, Map<String, Object> edgeProps) {
        return queryAdjacentNodes(Collections.singleton(fromId),
            edgeType, direction, recursive,
            nodeType, nodeProps, edgeProps);
    }

    @Override
    public Iterable<T> queryAdjacentNodes(
        Collection<ID> fromIds,
        String edgeType, Direction direction, boolean recursive,
        String nodeType, Map<String, Object> nodeProps, Map<String, Object> edgeProps) {
        Set<ID> toIds;

        if (!recursive)
            toIds = queryAdjacentNodesStep(fromIds, edgeType, direction, edgeProps);
        else
            toIds = queryAdjacentNodesRecursive(fromIds, edgeType, direction, edgeProps);

        if (toIds == null)
            toIds = Collections.emptySet();

        return filterIds(toIds, nodeType, nodeProps);
    }

    private Iterable<T> filterIds(Set<ID> toIds, String nodeType, Map<String, Object> nodeProps) {
        String cypher = String.format(
            "MATCH (n:%s%s) WHERE id(n) IN $ids RETURN n",
            nodeType, composeProps(N, nodeProps));

        Parameters parameters = new Parameters();
        parameters.prefix(N, nodeProps);
        parameters.put(IDS, toIds.toArray());

        return session.query(getDomainClass(), cypher, parameters);
    }

    private Set<ID> queryAdjacentNodesStep(
        Collection<ID> fromIds,
        String edgeType, Direction direction, Map<String, Object> edgeProps) {

        Parameters parameters = new Parameters();
        parameters.prefix(E, edgeProps);
        parameters.put(IDS, fromIds.toArray());

        String cypher = String.format(
            "MATCH (f) %s (n) WHERE id(f) IN $ids%s RETURN DISTINCT id(n)",
            composeEdge(edgeType, direction, edgeProps),
            composeWhereEdge(E, edgeProps));

        Iterable<ID> iter = session.query(idClass, cypher, parameters);

        return IterableUtils.asSet(iter);
    }

    private Set<ID> queryAdjacentNodesRecursive(
        Collection<ID> ids,
        String edgeType, Direction direction, Map<String, Object> edgeProps) {

        Set<ID> fromIds = new HashSet<>(ids);
        Set<ID> foundIds = new HashSet<>();

        Set<ID> toIds = queryAdjacentNodesStep(fromIds, edgeType, direction, edgeProps);
        Set<ID> newIds  = SetUtils.difference(toIds, foundIds);

        while (!newIds.isEmpty()) {
            foundIds.addAll(newIds);
            fromIds = newIds;

            toIds = queryAdjacentNodesStep(fromIds, edgeType, direction, edgeProps);
            newIds  = SetUtils.difference(toIds, foundIds);
        }

        return foundIds;
    }

    private String composeEdge(String edgeType, Direction direction, Map<String, Object> edgeProps) {
        String edgeQuery;
        switch (direction) {
            case Input:
                edgeQuery = String.format("<-[e:%s%s]- ", edgeType, composeProps(E, edgeProps));
                break;
            case Output:
                edgeQuery = String.format(" -[e:%s%s]->", edgeType, composeProps(E, edgeProps));
                break;
            default:
                edgeQuery = String.format(" -[e:%s%s]- ", edgeType, composeProps(E, edgeProps));
                break;
        }
        return edgeQuery;
    }

    private String composeWhereEdge(String prefix, Map<String, Object> props) {
        if (props.isEmpty())
            return "";

        StringBuilder sb = new StringBuilder();
        for (String key : props.keySet()) {
            Object value = props.get(key);
            if (!(value instanceof Collection || value instanceof Array))
                continue;

            sb.append(String.format(" AND %s.%s IN $%s%s", prefix, key, prefix, key));
        }
        return sb.toString();
    }

    private String composeProps(String prefix, Map<String, Object> props) {
        if (props.isEmpty())
            return "";

        StringBuilder sb = new StringBuilder();
        for (String key : props.keySet()) {
            Object value = props.get(key);
            if ((value instanceof Collection || value instanceof Array))
                continue;

            if (sb.length() > 0) sb.append(",");
            sb.append(key).append(":$").append(prefix).append(key);
        }
        return "{" + sb.toString() + "}";
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
