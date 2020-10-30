package org.hls.check.check_spring_neo4j.truffa;

import jext.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtmRepository extends Neo4jRepository<Atm, Long> {

}
