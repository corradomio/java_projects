package jext.springframework.neo4j.repository;

import jext.springframework.neo4j.domain.Specification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

public interface Neo4jSpecificationExecutor<T> {

    Optional<T> findOne(@Nullable Specification<T> spec);

    List<T> findAll(@Nullable Specification<T> spec);

    Page<T> findAll(@Nullable Specification<T> spec, Pageable pageable);

    List<T> findAll(@Nullable Specification<T> spec, Sort sort);

    long count(@Nullable Specification<T> spec);
}
