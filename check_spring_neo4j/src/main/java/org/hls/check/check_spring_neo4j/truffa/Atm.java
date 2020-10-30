package org.hls.check.check_spring_neo4j.truffa;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "atm")
public class Atm {

    @Id
    @GeneratedValue
    private Long nativeId;

    private String id;
    private Double longitude;
    private Double latitude;
    private String rg;

    public Atm() { }

    public String getId() {
        return id;
    }

    public String getName() {
        return rg;
    }

    @Override
    public String toString() {
        return String.format("Atm[%s, %s]", getId(), getName());
    }

}
