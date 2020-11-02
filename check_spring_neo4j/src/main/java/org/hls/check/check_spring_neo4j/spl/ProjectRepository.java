package org.hls.check.check_spring_neo4j.spl;

import jext.springframework.data.neo4j.repository.Neo4jRepository;

public interface ProjectRepository extends Neo4jRepository<Project, Long> {

}
