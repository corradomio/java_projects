package ae.ebtic.spl.data.idonly;

import jext.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.query.Param;

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

public interface NodeRepository extends Neo4jRepository<GraphEntity, Long> {

    // @Query("MATCH (from) -[e:uses]-> (to) WHERE id(from) IN $ids AND id(to) IN $ids RETURN id(from) AS from, id(to) AS to, e AS properties")
    @Query("MATCH (from) -[e:uses]-> (to) WHERE id(from) IN $ids AND id(to) IN $ids RETURN e")
    Iterable<Edge> findRelationsUsingList(@Param("ids") List<Long> ids);

}
