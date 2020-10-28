package org.hls.check.check_spring_neo4j;

import jext.springframework.data.neo4j.Neo4JConnector;
import org.hls.check.check_spring_neo4j.truffa.BankOfficeRepository;
import org.neo4j.driver.Query;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.hls.check.check_spring_neo4j.truffa.AtmRepository;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import jext.springframework.data.neo4j.repository.support.SimpleNeo4jRepository;

@SpringBootApplication
@EnableNeo4jRepositories(repositoryBaseClass = SimpleNeo4jRepository.class, basePackages = {"org/hls/check/check_spring_neo4j/truffa"})
@ComponentScan(value = "jext")
@ComponentScan(value = "org.hls.check.check_spring_neo4j.truffa")
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
                        System.out.printf("Atm %s: %s\n", atm.getId(), atm.getName());
                    });
        };
    }

    @Bean
    CommandLineRunner demo3(BankOfficeRepository repository) {
        return args -> {
            repository.findAll( PageRequest.of(1, 10))
                    .forEach(atm -> {
                        System.out.printf("Boffice %s: %s\n", atm.getId(), atm.getName());
                    });

            repository.listAll();
        };
    }

    @Bean
    CommandLineRunner demo2(Neo4JConnector connector) {
        return args -> {
            try(Session session = connector.session()) {
                Query query = new Query("MATCH (a:atm) RETURN a LIMIT 5");
                Result result = session.run(query);
                while(result.hasNext()){
                    System.out.println(((Node)result.next().asMap().get("a")).getClass());
                }
            }
        };
    }
}
