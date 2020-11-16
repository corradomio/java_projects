package org.hls.check.data.dependency;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@Data
@NodeEntity(label = "project")
public class Project {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String fullname;
    private String repository;

}
