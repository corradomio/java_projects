package org.hls.check.check_neo4j_2;

import org.hls.check.check_neo4j_2.truffa.AtmRepository;
import org.neo4j.cypherdsl.core.Cypher;
import org.neo4j.cypherdsl.core.ExposesReturning;
import org.neo4j.cypherdsl.core.Node;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories()
@ComponentScan(value = "jext.springframework")
@ComponentScan(value = "org.hls.check")
public class CheckNeo4j2Application {

    public static void main(String[] args) {
        SpringApplication.run(CheckNeo4j2Application.class, args);
    }

    @Bean
    CommandLineRunner demo1(AtmRepository repository) {
        return args -> {
            repository.findAll( PageRequest.of(1, 10))
                    .forEach(atm -> {
                        System.out.printf("Atm %s: %s\n", atm.getId(), atm.getName());
                    });
        };
    }

    // @Bean
    // CommandLineRunner demo2(AtmRepository repository) {
    //     return args -> {
    //         Node a = Cypher.node("atm").named("a");
    //         ExposesReturning noReturn = Cypher.match(a);
    //
    //         System.out.println(repository.count(noReturn, "a"));
    //     };
    // }

}
