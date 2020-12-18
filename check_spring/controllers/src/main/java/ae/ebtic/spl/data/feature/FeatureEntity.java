package ae.ebtic.spl.data.feature;

import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
    name: TestDEUMpvOnRoyalroad
    fullname: optimtools.test.TestDEUMpvOnRoyalroad

    role: FEATURE

    typeId: 31172
    componentId: 31221
    projectId: 31128

    count: 11
    countTypes: 13
    classes: 13
 */

@Data
@NodeEntity(label="feature")
public class FeatureEntity /*extends BaseEntity*/ {

    @Id
    @GeneratedValue
    private Long id;

    // 'FEATURE' 'PROJECT'
    private String role;
    // 'feature' null
    private String type;

    private String name;
    private String fullname;

    private Long count;
    private Long countTypes;
    @Deprecated private Long classes;       // better to use countTypes

    private String componentId;
    private String typeId;
    private String projectId;
    private String refId;

}
