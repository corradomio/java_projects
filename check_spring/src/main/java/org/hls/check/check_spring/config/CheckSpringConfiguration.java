package org.hls.check.check_spring.config;

import org.hls.check.check_spring.Database;
import org.hls.check.check_spring.db.DatabaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CheckSpringConfig {

    @Bean
    public Database database() {
        return new DatabaseImpl();
    }
}
