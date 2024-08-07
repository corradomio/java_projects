package ae.ebtic.spl.data.dependency;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@Data
@NodeEntity(label = "project")
public class Project {

    @Id
    @GeneratedValue
    private Long neo4jId;

    private String type;
    private String projectId;

    private String name;
    private String repository;
    private String fullname;
    private String role;

    private String status;
    private String reason;

    private Long timestamp;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    // public Project() {
    //
    // }
    //
    // public String getId() {
    //     return projectId;
    // }
    //
    // public String getName() {
    //     return name;
    // }

}
