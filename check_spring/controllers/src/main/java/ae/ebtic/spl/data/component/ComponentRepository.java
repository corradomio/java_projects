package ae.ebtic.spl.data.component;

import jext.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.query.Param;

public interface ComponentRepository extends Neo4jRepository<ComponentEntity, Long>
{
    @Query("MATCH (c:component) WHERE c.role <> 'PROJECT' AND c.refId=$refId RETURN count(c)")
    long countByRefId(@Param("refId") String refId);

    @Query("MATCH (c:component) WHERE c.role <> 'PROJECT' AND c.refId=$refId AND c.depth=$depth RETURN count(c)")
    long countByRefIdAndDepth(@Param("refId") String refId, @Param("depth") int depth);

    @Query("MATCH (c:component) WHERE c.role <> 'PROJECT' AND c.role=$role AND c.refId=$refId RETURN count(c)")
    long countByRefIdAndRole(@Param("refId") String refId, @Param("role") String role);
}
