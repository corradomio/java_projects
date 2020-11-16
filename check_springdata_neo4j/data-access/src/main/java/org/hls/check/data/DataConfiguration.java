package org.hls.check.data;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Configuration
@EnableNeo4jRepositories
public class DataConfiguration {

    public DataConfiguration() {
        System.out.println("DataConfiguration::new");
    }
}
