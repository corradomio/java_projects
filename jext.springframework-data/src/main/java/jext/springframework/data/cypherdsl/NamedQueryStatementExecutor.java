package jext.springframework.data.cypherdsl;

import jext.springframework.data.query.Query;

import java.util.Map;
import java.util.Optional;

public interface NamedQueryStatementExecutor<T> {

    long countUsing(String queryName, Map<String, ?> parameters);

    boolean existsUsing(String queryName, Map<String, ?> parameters);

    Optional<T> findOneUsing(String queryName, Map<String, ?> parameters);

    Iterable<T> findAllUsing(String queryName, Map<String, ?> parameters);

    Query<T> queryUsing(String queryName, Object ... args);

    Query<T> queryUsing(String queryName, Map<String, ?> parameters);
}
