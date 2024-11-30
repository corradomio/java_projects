package org.hls.check;

import org.hls.data.ApplicationMasterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "org.hls.data")
@EntityScan(basePackages = "org.hls.data")
@SpringBootApplication
public class AccessingDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataApplication.class, args);
    }

    // @Bean
    // public DataSource dataSource() {
    //     DriverManagerDataSource dataSource = new DriverManagerDataSource();
    //
    //     dataSource.setDriverClassName("org.postgresql.Driver");
    //     dataSource.setUsername("postgres");
    //     dataSource.setPassword("p0stgres");
    //     dataSource.setUrl("jdbc:postgresql://10.193.20.15:5432/btdigital_ipredict_development");
    //
    //     return dataSource;
    // }

    @Bean
    CommandLineRunner demo(ApplicationMasterRepository amr) {
        return (args) -> {
            amr.findAll().forEach(m -> {
                System.out.println(m.getName());
            });
            System.out.printf("Done");
        };
    }

}
