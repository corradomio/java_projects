package org.hls.check.data.dependency;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ProjectRepository extends Neo4jRepository<Project, Long> {

    Project findByName(String name);
}
