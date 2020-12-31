package ae.ebtic.spl.data.idonly;

import lombok.Data;
import org.neo4j.ogm.response.model.RelationshipModel;
import org.springframework.data.neo4j.annotation.QueryResult;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import java.util.Map;

/*
"e": {
            "id": 26601,
            "version": null,
            "type": "uses",
            "startNode": 2050,
            "endNode": 1053,
            "primaryIdName": null,
            "propertyList": [{
                    "key": "type",
                    "value": "dependsOn"
                }, {
                    "key": "uses",
                    "value": "dependsOn"
                }
            ]
        }
 */

@Data
@QueryResult
public class Edge {
    // @Id
    // @GeneratedValue
    // private Long id;
    //
    // private String type;
    // private Long startNode;
    // private Long endNode;
    // private List<Map<String, Object>> propertyList;
    private RelationshipModel e;
}
