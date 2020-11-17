package org.hls.check.data;

import jext.springframework.data.neo4j.repository.support.ExtendedNeo4jRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Configuration
@EnableNeo4jRepositories(repositoryBaseClass = ExtendedNeo4jRepository.class)
public class DataConfiguration {

    public DataConfiguration() {
        System.out.println("DataConfiguration::new");
    }
}
