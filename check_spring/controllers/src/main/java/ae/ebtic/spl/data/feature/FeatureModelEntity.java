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
@NodeEntity(label="feature")
public class FeatureModelEntity /*extends BaseEntity*/ {

    @Id
    @GeneratedValue
    private Long id;

    // 'FEATURE' 'PROJECT'
    private String role;
    // 'feature' null
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
