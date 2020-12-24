package ae.ebtic.spl.data.component;

import lombok.Data;
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
@NodeEntity(label="mcomponent")
public class ComponentModelEntity extends ComponentEntity {

    private String repository;

    private String status;
    private String reason;
    private Long timestamp;

}
