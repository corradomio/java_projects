package org.hls.check.check_neo4j_2;

import org.hls.check.check_neo4j_2.truffa.AtmRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
public class CheckNeo4j2Application {

    public static void main(String[] args) {
        SpringApplication.run(CheckNeo4j2Application.class, args);
    }

    @Bean
    CommandLineRunner demo(AtmRepository repository) {
        return args -> {
            repository.findAll( PageRequest.of(1, 10))
                    .forEach(atm -> {
                        System.out.printf("Atm %s: %s\n", atm.getId(), atm.getName());
                    });
        };
    }

}
