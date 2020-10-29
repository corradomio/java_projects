package org.hls.check.check_spring_neo4j;

import org.hls.check.check_spring_neo4j.mysql.AuthorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CheckSpringJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheckSpringNeo4jApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(AuthorRepository repository) {
        return args -> {
            repository.findAll( PageRequest.of(1, 10))
                    .forEach(atm -> {
                        System.out.printf("Atm %s: %s\n", atm.getId(), atm.getName());
                    });
        };
    }

}
