package org.hls.check.check_spring_paginate.truffa;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface AtmRepository extends Neo4jRepository<Atm, Long> {

}
