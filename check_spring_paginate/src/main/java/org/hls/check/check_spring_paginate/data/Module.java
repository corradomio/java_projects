package org.hls.check.data;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
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
}
