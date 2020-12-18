package ae.ebtic.spl.data.feature;

import jext.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.query.Param;

public interface FeatureModelRepository extends Neo4jRepository<FeatureModelEntity, Long>
{
    @Query("MATCH (f:feature {role:'PROJECT'}) WHERE f.refId=$refId RETURN f")
    FeatureModelEntity findByRefId(@Param("refId") String refId);

    FeatureModelEntity findByRefIdAndRole(String refId, String role);
    FeatureModelEntity findByFullname(String fullname);
    FeatureModelEntity findByRepositoryAndName(String repository, String name);

    @Query("MATCH (f:feature) WHERE f.fullname=$0 RETURN count(f)>0")
    boolean existsByFullname(String fullname);

    @Query("MATCH (f:feature {role:'PROJECT'}) WHERE f.refId=$0 RETURN count(f)>0")
    boolean existsByRefId(String refId);
}
