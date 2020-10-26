package org.hls.check.data;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ModuleRepository extends Neo4jRepository<Module, Long> {

}
