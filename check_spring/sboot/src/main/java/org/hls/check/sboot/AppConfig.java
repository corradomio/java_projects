package org.hls.check.sboot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.hateoas.config.EnableHypermediaSupport;

import javax.annotation.PostConstruct;

@Configuration
@EnableHypermediaSupport(type={})
@ComponentScan("org.hls")
@ComponentScan("ae.ebtic")
@PropertySource(value = "./config/application.properties",  ignoreResourceNotFound = true)
public class ApplicationConfig {

    private Logger logger = LogManager.getLogger(ApplicationConfig.class);

    @Value("${spring.app.root}")
    private String appRoot;

    public ApplicationConfig() {
        logger.info("new");
    }

    @PostConstruct
    private void init() {
        logger.info(String.format("init: %s", "welcome"));
    }

}
