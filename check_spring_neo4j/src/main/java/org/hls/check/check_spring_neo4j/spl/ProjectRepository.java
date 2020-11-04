package org.hls.check.check_spring_neo4j.spl;

import jext.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProjectRepository extends Neo4jRepository<Project, Long> {

    @Query("MATCH ...")
    List<Project> findPippo();

}
