package jext.springframework.data.neo4j.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Neo4jConfiguration {

    @Bean
    public Neo4JConnector connector() {
        return new Neo4JConnector();
    }
}
