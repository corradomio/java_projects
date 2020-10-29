package org.hls.check.check_neo4j_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
public class CheckNeo4j2Application {

    public static void main(String[] args) {
        SpringApplication.run(CheckNeo4j2Application.class, args);
    }

}
