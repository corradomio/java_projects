package org.hls.check.sboot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.support.WebStack;

import javax.annotation.PostConstruct;

@Configuration
@EnableHypermediaSupport(type={})
public class ApplicationConfig {

    private Logger logger = LogManager.getLogger(ApplicationConfig.class);

    public ApplicationConfig() {
        logger.info("new");
    }

    @PostConstruct
    private void init() {
        logger.info(String.format("init: %s", "welcome"));
    }

}
