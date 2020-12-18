package ae.ebtic.spl.data.component;

import jext.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.query.Param;

public interface ComponentModelRepository extends Neo4jRepository<ComponentModelEntity, Long>
{
    @Query("MATCH (c:component {role:'PROJECT'}) WHERE c.refId=$refId RETURN c")
    ComponentModelEntity findByRefId(@Param("refId") String refId);

    ComponentModelEntity findByFullname(String fullname);
    ComponentModelEntity findByRepositoryAndName(String repository, String name);

    @Query("MATCH (c:component) WHERE c.fullname=$0 RETURN count(c)>0")
    boolean existsByFullname(String fullname);

    @Query("MATCH (c:component {role:'PROJECT'}) where c.refId=$0 RETURN count(c)>0")
    boolean existsByRefId(String refId);
}
