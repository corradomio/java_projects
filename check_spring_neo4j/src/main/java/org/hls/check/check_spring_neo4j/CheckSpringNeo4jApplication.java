package org.hls.check.check_spring_neo4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import truffa.AtmRepository;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.repository.support.SimpleNeo4jRepository;

@SpringBootApplication
@EnableNeo4jRepositories(repositoryBaseClass = SimpleNeo4jRepository.class)
@ComponentScan(value = "jext")
// @ComponentScan(value = "truffa")
@ComponentScan(value = "org.hls.check")
public class CheckSpringNeo4jApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheckSpringNeo4jApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(AtmRepository repository) {
        return args -> {
            repository.findAll( PageRequest.of(1, 10))
                    .forEach(atm -> {
                        System.out.printf("%s: %s\n", atm.getId(), atm.getName());
                    });
        };
    }

}
