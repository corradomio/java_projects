package org.hls.check.data;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "project")
public class Project {

    @Id
    @GeneratedValue
    private Long neo4jId;

    private String type;
    private String projectId;

    private String name;
    private String repository;
    private String fullname;
    private String role;

    private String status;
    private String reason;

    private Long timestamp;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public Project() {

    }

    public String getId() {
        return projectId;
    }

    public String getName() {
        return name;
    }

}
