package org.hls.check.check_spring_paginate.truffa;

import jext.springframework.data.neo4j.config.Neo4JConnector;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

public class BankOfficeRepositoryImpl implements BankOfficeRepository {

    @Autowired
    private Neo4JConnector connector;

    @Override
    public List<BankOffice> findAll() {
        return Collections.emptyList();
    }
}
