package org.hls.check.check_spring_paginate;

import jext.springframework.data.neo4j.config.Neo4JConnector;
import org.hls.check.check_spring_paginate.truffa.AtmRepository;
import org.hls.check.check_spring_paginate.truffa.BankOfficeRepository;
import org.neo4j.driver.Query;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
@ComponentScan(value = "jext.springframework")
@ComponentScan(value = "org.hls.check.check_spring_paginate")
public class CheckDatabase {

    private final static Logger log = LoggerFactory.getLogger(CheckDatabase.class);

    public static void main(String[] args) {
        SpringApplication.run(CheckDatabase.class, args);
    }

    // @Bean
    // CommandLineRunner demo(ProjectRepository repository) {
    //     return args -> {
    //         CheckDatabase.this.run(args, repository);
    //     };
    // }

    // private void run(String[] args, ProjectRepository repository) {
    //     repository.findAll().forEach(p -> {
    //         System.out.printf("project '%s'\n", p.getFullName());
    //         repository.findModules(p.getId()).forEach(m -> {
    //             System.out.printf("  module '%s'\n", m.getName());
    //         });
    //         // repository.findModules(p).forEach(m -> {
    //         //     System.out.printf("  module '%s'\n", m.getName());
    //         // });
    //     });
    //     System.out.println(repository);
    // }

    // @Bean
    // CommandLineRunner dumpBeans(ApplicationContext applicationContext) {
    //     return args -> {
    //         new SpringBeans(applicationContext).dumpBeans();
    //     };
    // }

    // @Bean
    // CommandLineRunner demo(AtmRepository repository) {
    //     return args -> {
    //         repository.findAll( PageRequest.of(1, 10))
    //                 .forEach(atm -> {
    //                     System.out.printf("%s: %s\n", atm.getId(), atm.getName());
    //                 });
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
    // CommandLineRunner demo3(BankOfficeRepository repository) {
    //     return args -> {
    //         repository.findAll( PageRequest.of(1, 10))
    //                 .forEach(atm -> {
    //                     System.out.printf("%s: %s\n", atm.getId(), atm.getName());
    //                 });
    //
    //         repository.listAll();
    //     };
    // }
}
