package org.hls.check.check_spring_paginate.data;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "module")
public class Module {

    @Id
    @GeneratedValue
    private Long id;

    private String fullname;
    private String name;
    private String path;
    private String projectId;
    private String type;

    public Module() { }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
