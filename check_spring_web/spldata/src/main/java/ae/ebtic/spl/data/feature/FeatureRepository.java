package ae.ebtic.spl.data.feature;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface FeatureRepository extends Neo4jRepository<Feature, Long> {
}
