package org.hls.check.check_spring_neo4j.truffa;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankOfficeRepository extends
        Neo4jRepository<Atm, Long>
        // ,CypherdslPredicateExecutor<Atm>
        // ,BankOfficeRepositoryCustom
{
}
