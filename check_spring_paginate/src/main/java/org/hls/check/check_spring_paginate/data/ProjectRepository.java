package org.hls.check.check_spring_paginate.data;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ProjectRepository extends Neo4jRepository<Project, Long> {

    // <S extends T> S save(S s, int depth);
    // <S extends T> Iterable<S> save(Iterable<S> entities, int depth);
    // Optional<T> findById(ID id, int depth);
    // Iterable<T> findAll();
    // Iterable<T> findAll(int depth);
    // Iterable<T> findAll(Sort sort);
    // Iterable<T> findAll(Sort sort, int depth);
    // Iterable<T> findAllById(Iterable<ID> ids);
    // Iterable<T> findAllById(Iterable<ID> ids, int depth);
    // Iterable<T> findAllById(Iterable<ID> ids, Sort sort);
    // Iterable<T> findAllById(Iterable<ID> ids, Sort sort, int depth);
    // Page<T> findAll(Pageable pageable);
    // Page<T> findAll(Pageable pageable, int depth);

    // @Query("MATCH (p:project)<-[:memberOf]-(m:module) WHERE id(p)={projectId} RETURN m")
    // Externalized in /META-INF/neo4j-named-queries.properties/Project.findModules
    Iterable<Module> findModules(Long projectId);

    // @Query("MATCH (p:project)<-[:memberOf]-(m:module) WHERE id(p)={project.getId()} RETURN m")
    // Iterable<Module> findModules(Project project);
}
