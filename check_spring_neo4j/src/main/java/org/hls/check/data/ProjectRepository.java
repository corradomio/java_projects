package org.hls.check.data;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ProjectRepository extends Neo4jRepository<Project, Long> {

}
