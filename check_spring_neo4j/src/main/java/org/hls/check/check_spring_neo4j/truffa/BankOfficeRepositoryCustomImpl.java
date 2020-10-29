package org.hls.check.check_spring_neo4j.truffa;

// import org.springframework.data.neo4j.config.Neo4JConnector;

import jext.springframework.data.neo4j.repository.support.ExtendedNeo4jRepository;
import org.neo4j.cypherdsl.core.Cypher;
import org.neo4j.cypherdsl.core.ExposesReturning;
import org.neo4j.cypherdsl.core.Node;
import org.springframework.beans.factory.annotation.Autowired;

public class BankOfficeRepositoryCustomImpl implements BankOfficeRepositoryCustom {

    @Autowired
    private ExtendedNeo4jRepository<BankOffice, Long> bo;

    @Override
    public void test() {
        Node atm = Cypher.node("atm").named("a");
        ExposesReturning noReturn = Cypher.match(atm);

        System.out.println(bo.count(noReturn, "a"));
    }
}
