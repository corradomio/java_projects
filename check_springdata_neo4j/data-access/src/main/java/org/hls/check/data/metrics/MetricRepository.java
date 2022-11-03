package org.hls.check.data.metrics;

import jext.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface MetricRepository  extends Neo4jRepository<Metric, Long> {

    Iterable<Metric> findAllByRefId(@Param("refId") String refId);

    long countAllByRefId(String refId);
    long countAllByRefIdAndMproviderId(String refId, String mproviderId);

    Iterable<Metric> findAllByRefIdAndMproviderId(String refId, String mproviderId);
}
