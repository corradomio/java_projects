package jext.springframework.data.cypherdsl;

import org.neo4j.ogm.model.QueryStatistics;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;

import java.io.Serializable;
import java.util.Map;

public interface Neo4jOgmSessionExecutor<T> {

    /**
     * the concrete class returned by the queies
     *
     * @return The concrete class
     */
    Class<T> getDomainClass();

    /**
     * the internal Neo4J-OGM session used to access Neo4J
     *
     * @return the internal Neo4J-OGM session
     */
    Session getSession();

    //
    // Some Session methods exported here for convenience
    //

    /**
     * a cypher statement this method will return a domain object that is hydrated to the
     * default level or a scalar (depending on the parametrized type).
     *
     * @param objectType The type that should be returned from the query.
     * @param cypher     The parameterizable cypher to execute.
     * @param parameters Any scalar parameters to attach to the cypher.
     * @param <U>        A domain object or scalar.
     * @return An instance of the objectType that matches the cypher and parameters. Null if no object
     * is matched
     * @throws RuntimeException If more than one object is found.
     */
    <U> U queryForObject(Class<U> objectType, String cypher, Map<String, ?> parameters);

    /**
     * a cypher statement this method will return a collection of domain objects that is hydrated to
     * the default level or a collection of scalars (depending on the parametrized type).
     *
     * @param objectType The type that should be returned from the query.
     * @param cypher     The parameterizable cypher to execute.
     * @param parameters Any parameters to attach to the cypher.
     * @param <U>        A domain object or scalar.
     * @return A collection of domain objects or scalars as prescribed by the parametrized type.
     */
    <U> Iterable<U> query(Class<U> objectType, String cypher, Map<String, ?> parameters);

    /**
     * a cypher statement this method will return a Result object containing a collection of Map's which represent Neo4j
     * objects as properties, along with query statistics if applicable.
     * Each element of the query result is a map which you can access by the name of the returned field
     * TODO: Are we going to use the neo4jOperations conversion method to cast the value object to its proper class?
     *
     * @param cypher     The parameterisable cypher to execute.
     * @param parameters Any parameters to attach to the cypher.
     * @return A {@link Result} containing an {@link Iterable} map representing query results and {@link QueryStatistics} if applicable.
     */
    Result query(String cypher, Map<String, ?> parameters);

    /**
     * a cypher statement this method will return a Result object containing a collection of Map's which represent Neo4j
     * objects as properties, along with query statistics if applicable.
     * Each element of the query result is a map which you can access by the name of the returned field
     * TODO: Are we going to use the neo4jOperations conversion method to cast the value object to its proper class?
     *
     * @param cypher     The parameterisable cypher to execute.
     * @param parameters Any parameters to attach to the cypher.
     * @param readOnly   true if the query is readOnly, false otherwise
     * @return A {@link Result} of {@link Iterable}s with each entry representing a neo4j object's properties.
     */
    Result query(String cypher, Map<String, ?> parameters, boolean readOnly);
}
