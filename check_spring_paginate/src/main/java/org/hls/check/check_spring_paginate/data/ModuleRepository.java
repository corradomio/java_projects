package org.hls.check.check_spring_paginate.data;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ModuleRepository extends Neo4jRepository<Module, Long> {

    @Query("MATCH (p:project)<-[:memberOf]-(m:module) WHERE id(m)={moduleId} RETURN p")
    Project findProject(Long moduleId);
}
