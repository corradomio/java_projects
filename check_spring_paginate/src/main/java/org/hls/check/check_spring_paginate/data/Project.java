package org.hls.check.data;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Project {

    @Id
    @GeneratedValue
    private Long id;

    private String fullname;
    private String name;
    private String repository;
    private String reason;
    private String role;
    private String status;
    private Long timestamp;
    private String type;

    public Project() { }

}
