package org.hls.check.data.component;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@Data
@NodeEntity(label = "component")
public class Component {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String fullname;
}
