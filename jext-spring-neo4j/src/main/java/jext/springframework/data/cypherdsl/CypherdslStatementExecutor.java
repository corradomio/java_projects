package jext.springframework.data.cypherdsl;

import org.neo4j.cypherdsl.core.ExposesReturning;
import org.neo4j.cypherdsl.core.Statement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

/**
 * Interface to allow execution of CypherDsl {@link Statement} instances.
 *
 * @author Corrado Mio
 */
public interface CypherdslStatementExecutor<T> {

    Optional<T> findOne(ExposesReturning noReturn, String variable);

    Iterable<T> findAll(ExposesReturning noReturn, String variable);

    Iterable<T> findAll(ExposesReturning noReturn, String variable, Sort sort);

    Page<T> findAll(ExposesReturning noReturn, String variable, Pageable pageable);

    long count(ExposesReturning noReturn, String variable);

    boolean exists(ExposesReturning noReturn, String variable);
}
