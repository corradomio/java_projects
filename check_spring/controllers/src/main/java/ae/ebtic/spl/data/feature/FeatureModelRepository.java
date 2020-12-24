package ae.ebtic.spl.data.feature;

import jext.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.query.Param;

@CacheConfig(cacheNames = {"data.feature"})
public interface FeatureModelRepository extends Neo4jRepository<FeatureModelEntity, Long>
{
    @Cacheable
    @Query("MATCH (f:feature {role:'PROJECT'}) WHERE f.refId=$refId RETURN f")
    FeatureModelEntity findByRefId(@Param("refId") String refId);

    @Cacheable
    FeatureModelEntity findByFullname(String fullname);

    FeatureModelEntity findByRefIdAndRole(String refId, String role);
    FeatureModelEntity findByRepositoryAndName(String repository, String name);

    @Query("MATCH (f:feature) WHERE f.fullname=$0 RETURN count(f)>0")
    boolean existsByFullname(String fullname);

    @Query("MATCH (f:feature {role:'PROJECT'}) WHERE f.refId=$0 RETURN count(f)>0")
    boolean existsByRefId(String refId);
}
