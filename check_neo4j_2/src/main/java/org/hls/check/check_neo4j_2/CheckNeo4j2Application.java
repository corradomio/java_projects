package org.hls.check.check_neo4j_2;

import org.hls.check.data.SPLProjectEntity;
import org.hls.check.data.SPLProjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.io.File;

@SpringBootApplication
@EnableNeo4jRepositories()
@ComponentScan(value = "jext.springframework")
@ComponentScan(value = "org.hls.check")
@ComponentScan(value = "org.hls.check.data")
public class CheckNeo4j2Application {

    public static void main(String[] args) {

        org.neo4j.ogm.drivers.bolt.driver.BoltDriver bd;

        AppConfiguration.configure(new File(".").getAbsolutePath());
        SpringApplication.run(CheckNeo4j2Application.class, args);
    }

    // @Bean
    // CommandLineRunner demo(Neo4JIndices indices) {
    //     return args -> {
    //         indices.getIndexes().forEach(id -> {
    //             System.out.println(id.name);
    //         });
    //     };
    //
    // }

    @Bean
    CommandLineRunner demo(SPLProjectRepository repository) {
        return args -> {
            repository.findAll( PageRequest.of(0, 100))
                    .forEach(ent -> {
                        System.out.printf("entity %s: %s/%s  [%s]\n",
                            ent.getId(),
                            ent.getRepository(),
                            ent.getName(),

                            ent.getFullname());
                    });

            SPLProjectEntity ent =
                //repository.findByRepositoryAndName("dev-workspace", "ACME-Modified");
                repository.findByFullname("dev-workspace/ACME-Modified");
            System.out.printf(">>> entity %s: %s\n", ent.getId(), ent.getName());
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
