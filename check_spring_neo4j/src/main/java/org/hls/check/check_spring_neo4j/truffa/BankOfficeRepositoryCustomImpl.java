package org.hls.check.check_spring_neo4j.truffa;

// import org.springframework.data.neo4j.config.Neo4JConnector;

import java.util.Collections;
import java.util.List;

public class BankOfficeRepositoryCustomImpl implements BankOfficeRepositoryCustom {

    // @Autowired
    // private Neo4JConnector connector;

    @Override
    public List<BankOffice> listAll() {
        return Collections.emptyList();
    }
}
