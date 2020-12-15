package org.hls.check.data.component;

import jext.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.query.Param;

public interface ComponentRepository extends Neo4jRepository<Component, Long> {

    long countAllByRefId(String refId);

    @Query("match (c:component {refId:$0}) return count(c)")
    long countUsingZero(String refId);

    @Query("match (c:component {refId:$refId}) return count(c)")
    long countUsingParam(@Param("refId") String refId);

    // NUN FUNZIONA, bisogna usare @Param
    //@Query("match (c:component {refId:$refId}) return count(c)")
    //long countUsingName(String refId);

    @Query("component.countUsingNamedQuery")
    long countUsingNamedQuery(@Param("refId") String refId);
}
