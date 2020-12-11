package org.hls.check.sboot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
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
