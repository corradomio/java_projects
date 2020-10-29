package org.hls.check.check_neo4j_2.truffa;

import jext.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtmRepository extends Neo4jRepository<Atm, Long> {

}
