package ae.ebtic.spl.data.idonly;

import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@NodeEntity(label="node")
public class GraphEntity {

    @Id
    @GeneratedValue
    private Long id;

}
