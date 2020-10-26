package org.hls.check.main;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.Repository;

public interface ProjectRepository extends Neo4jRepository<Project, Long> {

}
