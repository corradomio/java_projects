package org.hls.check.check_spring_paginate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.hls.check.check_spring_paginate.data.ProjectRepository;

@SpringBootApplication
@EnableNeo4jRepositories
public class CheckDatabase {

    private final static Logger log = LoggerFactory.getLogger(CheckDatabase.class);

    public static void main(String[] args) {
        SpringApplication.run(CheckDatabase.class, args);
    }

    @Bean
    CommandLineRunner demo(ProjectRepository repository) {
        return args -> {
            CheckDatabase.this.run(args, repository);
        };
    }

    private void run(String[] args, ProjectRepository repository) {
        repository.findAll().forEach(p -> {
            System.out.printf("project '%s'\n", p.getFullName());
            repository.findModules(p.getId()).forEach(m -> {
                System.out.printf("  module '%s'\n", m.getName());
            });
            // repository.findModules(p).forEach(m -> {
            //     System.out.printf("  module '%s'\n", m.getName());
            // });
        });
        System.out.println(repository);
    }
}
