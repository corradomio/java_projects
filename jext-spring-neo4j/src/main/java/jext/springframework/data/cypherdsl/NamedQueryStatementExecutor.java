package jext.springframework.data.cypherdsl;

import jext.springframework.data.query.Query;
import jext.springframework.data.query.QueryResult;

import java.util.Map;
import java.util.Optional;

public interface NamedQueryStatementExecutor<T> {

    long countUsing(String queryName, Object ... args);

    boolean existsUsing(String queryName, Object ... args);

    Optional<T> findOneUsing(String queryName, Object ... args);

    Iterable<T> findAllUsing(String queryName, Object ... args);

    Query<T> queryUsing(String queryName, Object ... args);

    QueryResult executeUsing(String queryName, Object ... args);

    // ----------------------------------------------------------------------

    long countUsing(String queryName, Map<String, ?> parameters);

    boolean existsUsing(String queryName, Map<String, ?> parameters);

    Optional<T> findOneUsing(String queryName, Map<String, ?> parameters);

    Iterable<T> findAllUsing(String queryName, Map<String, ?> parameters);

    Query<T> queryUsing(String queryName, Map<String, ?> parameters);

    QueryResult executeUsing(String queryName, Map<String, ?> parameters);
}
