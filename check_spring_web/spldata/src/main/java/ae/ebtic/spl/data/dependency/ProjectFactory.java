package ae.ebtic.spl.data.dependency;

import jext.springframework.data.neo4j.repository.Neo4jRepository;

public interface ProjectFactory extends Neo4jRepository<Project, Long> {

}