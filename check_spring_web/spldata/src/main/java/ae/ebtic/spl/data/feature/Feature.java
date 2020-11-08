package ae.ebtic.spl.data.feature;

import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// @Data
@NodeEntity(label="feature")
public class Feature {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Long getId() { return id; }

    public String getName() { return name; }
}
