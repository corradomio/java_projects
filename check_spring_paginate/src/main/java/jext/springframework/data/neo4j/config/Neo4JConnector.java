package org.hls.check.check_spring_paginate.truffa;

import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;

public class Neo4JConnector {

    @Autowired
    private Driver driver;

    // public Neo4JConnector(Driver driver) {
    //     this.driver = driver;
    // }

    public Neo4JConnector() { }

    public void doSomething() {
        System.out.println("doSomething");
    }
}
