package org.hls.check.check_spring_neo4j;

import org.hls.check.check_spring_neo4j.mysql.AuthorRepository;
import org.hls.check.check_spring_neo4j.mysql.AuthorService;
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
        SpringApplication.run(CheckSpringJpaApplication.class, args);
    }

    // @Bean
    // CommandLineRunner demo1(AuthorRepository repository) {
    //     return args -> {
    //         String firstName, lastName;
    //         for (int i=3; i<=1000; ++i) {
    //             firstName = String.format("name%04d", i);
    //             lastName  = String.format("family%04d", i);
    //             Author a = new Author(i, firstName, lastName);
    //             repository.save(a);
    //         }
    //     };
    // }

    @Bean
    CommandLineRunner demo1(AuthorRepository repository) {
        return args -> {
            repository.findAll(PageRequest.of(1, 10))
                    .forEach(author -> {
                        System.out.printf("Author/ 1 %4d: %s\n", author.getId(), author.getName());
                    });
        };
    }

    @Bean
    CommandLineRunner demo2(AuthorService repository) {
        return args -> {
            repository.findByQuery(33)
                    .forEach(author -> {
                        System.out.printf("Author/33 %4d: %s\n", author.getId(), author.getName());
                    });
        };
    }

    @Bean
    CommandLineRunner demo3(AuthorService repository) {
        return args -> {
            repository.findByCriteria(99)
                    .forEach(author -> {
                        System.out.printf("Author/99 %4d: %s\n", author.getId(), author.getName());
                    });
        };
    }

    @Bean
    CommandLineRunner demo4(AuthorService repository) {
        return args -> {
            repository.findBySpecification(66)
                    .forEach(author -> {
                        System.out.printf("Author/66 %4d: %s\n", author.getId(), author.getName());
                    });
        };
    }

    @Bean
    CommandLineRunner demo5(AuthorService repository) {
        return args -> {
            repository.findByQuerydsl(42)
                    .forEach(author -> {
                        System.out.printf("Author/42 %4d: %s\n", author.getId(), author.getName());
                    });
        };
    }

}
