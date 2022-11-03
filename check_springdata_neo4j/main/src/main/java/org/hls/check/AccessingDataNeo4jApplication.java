package org.hls.check;

import org.hls.check.data.component.ComponentRepository;
import org.hls.check.data.metrics.MetricRepository;
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

    // String REF_ID = "c678d100";
    // String REF_ID = "dde55a05";
    String REF_ID = "ac61e44a";

    @Bean
    CommandLineRunner demo4(final MetricRepository mr) {
        return args -> {
            System.out.println(mr.countAllByRefIdAndMproviderId(REF_ID, "1795005"));

            mr.findAllByRefIdAndMproviderId(REF_ID, "1795005").forEach(m -> {
                System.out.println(m.getName());
            });
        };
    }


    // @Bean
    // CommandLineRunner demo3(final ComponentRepository cr) {
    //     return args -> {
    //         System.out.println(cr.countAllByRefId(REF_ID));
    //         System.out.println(cr.countUsingZero(REF_ID));
    //         System.out.println(cr.countUsingParam(REF_ID));
    //         // System.out.println(cr.countUsingName("dde55a05"));
    //         System.out.println(cr.checkUsingNamedQuery(REF_ID));
    //     };
    // }

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