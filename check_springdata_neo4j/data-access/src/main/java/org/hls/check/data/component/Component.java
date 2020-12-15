package org.hls.check.data.component;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NodeEntity("component")
public class Component {

    @Id
    @GeneratedValue
    private Long id;

    @Setter(AccessLevel.NONE)
    private String role = "COMPONENT";
    @Setter(AccessLevel.NONE)
    private String type = "component";

    private String name;
    private String fullname;

    private Long depth;

    private Long count;
    private Long countTypes;
    @Deprecated private Long classes;       // better to use countTypes

    private String typeId;
    private String projectId;
    private String refId;

}
