package ae.ebtic.spl.data.component;

import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
name:PopBasedAlgorithm
fullname:optimtools.algorithms.PopBasedAlgorithm

role:COMPONENT
type:component

classes:6
count:5
countTypes:6
depth:1
projectId:31128
refId:abe112c1
typeId:30826
 */

@Data
@NodeEntity(label="component")
public class ComponentEntity /*extends BaseEntity*/ {

    @Id
    @GeneratedValue
    private Long id;

    // 'COMPONENT' 'TYPE' 'PROJECT'
    private String role;
    // 'type' 'component'
    private String type;

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
