package org.hls.check.check_spring_paginate.truffa;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "bank_office")
public class BankOffice {

    @Id
    @GeneratedValue
    private Long nativeId;

    private String bank;
    private String id;

    public BankOffice() { }

    public String getId() {
        return id;
    }

    public String getName() {
        return bank;
    }
}
