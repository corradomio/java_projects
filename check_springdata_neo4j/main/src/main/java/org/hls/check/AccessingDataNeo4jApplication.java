package org.hls.check;

import jext.springframework.core.env.EnvironmentUtils;
import org.hls.check.data.component.ComponentRepository;
import org.hls.check.data.dependency.ProjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;

import java.util.Properties;

// @SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class AccessingDataNeo4jApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataNeo4jApplication.class, args);
    }

    @Bean
    CommandLineRunner demo1(final ProjectRepository projectRepository) {
        return args -> {

            projectRepository.findAll().forEach(p -> {
                System.out.println(p);
            });
        };
    }

    @Bean
    CommandLineRunner demo2(final ComponentRepository componentRepository) {
        return args -> {

            componentRepository.findAll(PageRequest.of(1, 10)).forEach(c -> {
                System.out.println(c);
            });
        };
    }

    @Bean
    CommandLineRunner demo3(final ApplicationContext ctx) {
        return args -> {

            Properties props = EnvironmentUtils.getProperties(ctx);
        };
    }

}