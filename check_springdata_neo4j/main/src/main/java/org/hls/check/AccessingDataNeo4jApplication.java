package org.hls.check;

import org.hls.check.data.component.ComponentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

// @SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class AccessingDataNeo4jApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataNeo4jApplication.class, args);
    }

    // @Bean
    // CommandLineRunner demo1(final ProjectRepository projectRepository) {
    //     return args -> {
    //
    //         projectRepository.findAll().forEach(p -> {
    //             System.out.println(p);
    //         });
    //     };
    // }

    // @Bean
    // CommandLineRunner demo2(final ComponentRepository cr) {
    //     return args -> {
    //
    //         cr.findAll(PageRequest.of(1, 10)).forEach(c -> {
    //             System.out.println(c);
    //         });
    //     };
    // }

    @Bean
    CommandLineRunner demo3(final ComponentRepository cr) {
        return args -> {
            System.out.println(cr.countAllByRefId("dde55a05"));
            System.out.println(cr.countUsingZero("dde55a05"));
            System.out.println(cr.countUsingParam("dde55a05"));
            // System.out.println(cr.countUsingName("dde55a05"));
            System.out.println(cr.checkUsingNamedQuery("dde55a05"));
        };
    }

    // @Bean
    // CommandLineRunner demo3(final ApplicationContext ctx) {
    //     return args -> {
    //
    //         Properties props = EnvironmentUtils.getProperties(ctx);
    //         List<String> names = new ArrayList<>(props.stringPropertyNames());
    //         names.sort(String::compareTo);
    //
    //         System.out.println("-------------------");
    //         names.forEach(n -> {
    //             System.out.printf("%s: %s\n", n, props.getProperty(n));
    //         });
    //         System.out.println("-------------------");
    //     };
    // }

}