package org.hls.check.check_spring.config;

import org.hls.check.check_spring.Database;
import org.hls.check.check_spring.db.DatabaseImpl;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CheckSpringConfiguration {

    @Bean
    // @Scope("singleton")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Database database() {
        return new DatabaseImpl();
    }
}
