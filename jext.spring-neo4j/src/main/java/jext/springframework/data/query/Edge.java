package jext.springframework.data.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.neo4j.ogm.response.model.RelationshipModel;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@QueryResult
public class Edge {

    private Long from;
    private Long to;
    private Map<String, Object> properties;

    public Edge(Map<String, Object> map) {
        RelationshipModel rel = (RelationshipModel)map.get("e");
        from = rel.getStartNode();
        to = rel.getEndNode();
        properties = new HashMap<>();
        rel.getPropertyList()
            .forEach(property -> {
                properties.put(property.getKey(), property.getValue());
            });
    }

    public Long getFrom(){ return from; }
    public Long getTo(){ return to; }
    public  Map<String, Object> getProperties(){ return properties; }

}
