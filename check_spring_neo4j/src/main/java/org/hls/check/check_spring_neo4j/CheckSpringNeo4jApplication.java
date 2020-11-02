package org.hls.check.check_spring_neo4j;

import jext.springframework.data.neo4j.repository.support.ExtendedNeo4jRepository;
import jext.springframework.data.neo4j.util.SpringBeans;
import org.hls.check.check_spring_neo4j.truffa.AtmRepository;
import org.neo4j.cypherdsl.core.Cypher;
import org.neo4j.cypherdsl.core.ExposesReturning;
import org.neo4j.cypherdsl.core.Node;
import org.neo4j.ogm.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import javax.swing.*;
import java.util.Collections;

@SpringBootApplication
@EnableNeo4jRepositories(repositoryBaseClass = ExtendedNeo4jRepository.class)
// @EnableNeo4jRepositories(basePackageClasses = Neo4jRepository.class)
// @EnableNeo4jRepositories()
@ComponentScan(value = "jext.springframework")
@ComponentScan(value = "org.hls.check.check_spring_neo4j.truffa")
@ComponentScan(value = "org.hls.check")
public class CheckSpringNeo4jApplication {
    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(CheckSpringNeo4jApplication.class, args);
    }

    @Bean
    CommandLineRunner demo0(AtmRepository repository) {
        return args -> {
            new SpringBeans(applicationContext).dumpBeans();
        };
    }


    @Bean
    CommandLineRunner demo1(AtmRepository repository) {
        return args -> {
            repository.findAll( PageRequest.of(1, 10))
                    .forEach(atm -> {
                        System.out.printf("1: %s: %s\n", atm.getId(), atm.getName());
                    });
        };
    }


    @Bean
    CommandLineRunner demo2(AtmRepository repository) {
        return args -> {
            // ritorna una lista di mappe {'a'-> Atm object !!!!}
            Result qr = repository.query("MATCH (a:atm) RETURN a SKIP 1 LIMIT 2", Collections.emptyMap());
            qr.forEach(result -> {
                System.out.printf("2: %s\n", result);
            });
        };
    }


    @Bean
    CommandLineRunner demo3(AtmRepository repository) {
        return args -> {
            // ritorna una lista di mappe {'a'-> Atm object !!!!}
            Result qr = repository.query("MATCH (a:atm) RETURN a SKIP 1 LIMIT 2", Collections.emptyMap());
            qr.forEach(result -> {
                System.out.printf("3: %s\n", result);
            });
        };
    }

    //
    // Nota: una volta chiamata la "build()", lo statement NON PUO' ESSERE riutilizzato!
    //
    @Bean
    CommandLineRunner demo4(AtmRepository repository) {
        return args -> {
            Node a;
            ExposesReturning noReturn;

            a = Cypher.node("atm").named("a");
            noReturn = Cypher.match(a);
            System.out.printf("4: %d\n", repository.count(noReturn, "a"));

            a = Cypher.node("atm").named("a");
            noReturn = Cypher.match(a);
            System.out.printf("4: %s\n", repository.findOne(noReturn, "a").toString());

            a = Cypher.node("atm").named("a");
            noReturn = Cypher.match(a).where(a.property("name").isEqualTo(Cypher.literalOf("Casa")));
            System.out.printf("4: %s\n", repository.findOne(noReturn, "a"));


        };
    }


    // @Bean
    // CommandLineRunner demo3(BankOfficeRepository repository) {
    //     return args -> {
    //         repository.findAll( PageRequest.of(1, 10))
    //                 .forEach(atm -> {
    //                     System.out.printf("Boffice %s: %s\n", atm.getId(), atm.getName());
    //                 });
    //
    //         repository.listAll();
    //     };
    // }

    // @Bean
    // CommandLineRunner demo2(Neo4JConnector connector) {
    //     return args -> {
    //         try(Session session = connector.session()) {
    //             Query query = new Query("MATCH (a:atm) RETURN a LIMIT 5");
    //             Result result = session.run(query);
    //             while(result.hasNext()){
    //                 System.out.println(((Node)result.next().asMap().get("a")).getClass());
    //             }
    //         }
    //     };
    // }

    // @Bean
    // CommandLineRunner demo2(BankOfficeRepository repository) {
    //     return args -> {
    //         repository.test();
    //     };
    // }
}
