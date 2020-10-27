package org.hls.check.check_spring_paginate.truffa;

import jext.springframework.data.neo4j.config.Neo4JConnector;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

public class BankOfficeRepositoryCustomImpl implements BankOfficeRepositoryCustom {

    @Autowired
    private Neo4JConnector connector;

    @Override
    public List<BankOffice> listAll() {
        return Collections.emptyList();
    }
}
