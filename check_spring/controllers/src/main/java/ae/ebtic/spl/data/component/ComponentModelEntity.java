package ae.ebtic.spl.data.component;

import ae.ebtic.spl.analysis.graph.GraphConstants;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
name:DEUM
repository:example_repo
fullname:example_repo/DEUM

type:component
role:PROJECT

status:VALID
reason:VALID
timestamp:1607860567987

refId:abe112c1
 */

@Data
@NodeEntity(label="component")
public class ComponentModelEntity {

    @Id
    @GeneratedValue
    private Long id;

    // 'COMPONENT' 'TYPE' 'PROJECT'
    private String role;
    // 'type' 'component'
    private String type;

    private String name;
    private String repository;
    private String fullname;

    private String status;
    private String reason;
    private Long timestamp;

    // private String projectId; MISSING
    private String refId;
}
