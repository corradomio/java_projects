package org.hls.check.sboot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.hateoas.config.EnableHypermediaSupport;

import javax.annotation.PostConstruct;

@Configuration
@EnableHypermediaSupport(type={})
@ComponentScan("ae.ebtic")
@PropertySource(value = "./config/application.properties",  ignoreResourceNotFound = true)
public class AppConfig {

    private Logger logger = LogManager.getLogger(AppConfig.class);

    @Value("${spring.app.root}")
    private String appRoot;

    public AppConfig() {
        logger.info("new");
    }

    @PostConstruct
    private void init() {
        logger.info(String.format("init: %s", "welcome"));
    }

}
