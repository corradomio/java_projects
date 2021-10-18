package org.hls.check.data;

import jext.util.JSONUtils;
import jext.util.Parameters;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NodeEntity(label="splproject")
public class SPLProjectEntity /*extends BaseEntity*/ {

    @Id
    @GeneratedValue
    private Long id;

    @Setter(AccessLevel.NONE)
    private String role = GraphConstants.ROLE_PROJECT;
    @Setter(AccessLevel.NONE)
    private String type = GraphConstants.SPLPROJECT;

    private String name;
    private String repository;
    private String fullname;

    private String refId;

    private String description;

    private String url;

    private String jparameters;

    private String status;
    private String reason;
    private long timestamp = System.currentTimeMillis();

    /**
     * Retrieve the parameters parsing 'jparameters'
     *
     * Note: it is not possible to use 'getParameters()' because it is
     *      considered a 'field' and Spring try to save it in Neo4J
     *
     * @return
     */
    public Parameters retrieveParameters() {
        return JSONUtils.parse(jparameters, Parameters.class);
    }

    public void setParameters(Parameters parameters) {
        this.jparameters = JSONUtils.serialize(parameters);
    }

    public void build() {
        this.fullname = String.format("%s/%s", repository, name);
    }

}
