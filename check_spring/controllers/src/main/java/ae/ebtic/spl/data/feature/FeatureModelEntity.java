package ae.ebtic.spl.data.feature;

import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
name: DEUM
repository: example_repo
fullname: example_repo/DEUM


reason: VALID
refId: abe112c1
role: PROJECT
status: VALID
timestamp: 1607860578199
type: feature
 */

@Data
@NodeEntity(label="mfeature")
public class FeatureModelEntity extends FeatureEntity {

    private String repository;

    private String status;
    private String reason;
    private Long timestamp;

}
