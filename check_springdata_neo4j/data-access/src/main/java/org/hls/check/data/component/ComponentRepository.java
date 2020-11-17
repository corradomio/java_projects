package org.hls.check.data.component;

import jext.springframework.data.neo4j.repository.Neo4jRepository;

public interface ComponentRepository extends Neo4jRepository<Component, Long> {

}
