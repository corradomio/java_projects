package org.hls.check.data.metrics;

import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NodeEntity(label="metric")
public class Metric {
    @Id
    @GeneratedValue
    private Long id;
    private String refId;

    private String name;
    private String fullname;
    private String description;

    private String mproviderId;
}
