package org.hls.check.check_spring_neo4j;

import org.neo4j.driver.AuthToken;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Config;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.neo4j.driver.springframework.boot.autoconfigure.Neo4jDriverProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfiguration {

    // @Autowired
    private Driver driver;

    // public DBConfiguration(Driver driver) {
    //     this.driver = driver;
    // }

    // @Bean
    // public Session session() {
    //     if (driver == null) {
    //         driver = GraphDatabase.driver("bolt://localhost:7684",
    //                 AuthTokens.basic( "neo4j", "password" ));
    //     }
    //     return driver.session();
    // }

    @Bean
    @ConditionalOnMissingBean(Driver.class)
    @ConditionalOnProperty(prefix = "org.neo4j.driver", name = "uri")
    Driver neo4jDriver(final Neo4jDriverProperties driverProperties) {

        final AuthToken authToken = AuthTokens.basic(
                driverProperties.getAuthentication().getUsername(),
                driverProperties.getAuthentication().getPassword()
        );
        // driverProperties.getAuthentication().asAuthToken();
        final Config config = driverProperties.asDriverConfig();

        return GraphDatabase.driver(driverProperties.getUri(), authToken, config);
    }
}
