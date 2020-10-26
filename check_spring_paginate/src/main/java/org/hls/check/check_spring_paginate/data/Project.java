package org.hls.check.check_spring_paginate.data;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity(label = "project")
public class Project {

    @Id
    @GeneratedValue
    public Long id;

    @Property(name = "fullname")
    private String fullName;
    private String name;
    private String repository;
    private String reason;
    private String role;
    private String status;
    private Long timestamp;
    private String type;

    // @Relationship(type = "memberOf", direction = Relationship.INCOMING)
    // public List<Module> modules;

    public Project() { }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    // public List<Module> getModules() {
    //     return modules;
    // }

    @Override
    public String toString() {
        return name;
    }
}
