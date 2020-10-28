package org.hls.check.check_spring_neo4j.truffa;

import org.springframework.data.neo4j.repository.Neo4jRepository;

// @Repository
public interface BankOfficeRepository extends Neo4jRepository<Atm, Long>, BankOfficeRepositoryCustom {
}
