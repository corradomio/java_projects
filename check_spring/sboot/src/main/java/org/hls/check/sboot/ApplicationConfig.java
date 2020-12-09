package org.hls.check.sboot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

@Configuration
public class ApplicationConfig {

    Logger logger = LogManager.getLogger(ApplicationConfig.class);

    public ApplicationConfig() {
        logger.info("constructor");
    }

    @PostConstruct
    private void init() {
        logger.info(String.format("init: %s", "welcome"));
    }

}
