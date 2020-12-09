package org.hls.check.sboot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;

@Configuration
public class SPLAppConfig {

    Logger logger = LogManager.getLogger(SPLAppConfig.class);

    @Value("${spring.app.root}")
    private String appRoot;

    @Value("${ae.ebtic.spl.welcome}")
    private String welcome;

    public SPLAppConfig() {
        logger.info("constructor");
    }

    @PostConstruct
    private void init() {
        logger.info(String.format("init: %s", welcome));
    }

}
