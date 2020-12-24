package ae.ebtic.spl.data.feature;

import jext.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.neo4j.annotation.Query;

public interface FeatureRepository extends Neo4jRepository<FeatureEntity, Long>
{
    @Query("MATCH (f:feature {role:'FEATURE'}) WHERE f.refId=$refId RETURN count(f)")
    long countByRefId(String refId);
}
