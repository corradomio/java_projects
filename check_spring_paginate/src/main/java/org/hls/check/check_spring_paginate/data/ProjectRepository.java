package org.hls.check.data;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ProjectRepository extends Neo4jRepository<Project, Long> {
    // <S extends T> S save(S var1, int var2);
    // <S extends T> Iterable<S> save(Iterable<S> var1, int var2);
    // Optional<T> findById(ID var1, int var2);
    // Iterable<T> findAll();
    // Iterable<T> findAll(int var1);
    // Iterable<T> findAll(Sort var1);
    // Iterable<T> findAll(Sort var1, int var2);
    // Iterable<T> findAllById(Iterable<ID> var1);
    // Iterable<T> findAllById(Iterable<ID> var1, int var2);
    // Iterable<T> findAllById(Iterable<ID> var1, Sort var2);
    // Iterable<T> findAllById(Iterable<ID> var1, Sort var2, int var3);
    // Page<T> findAll(Pageable var1);
    // Page<T> findAll(Pageable var1, int var2);
}
