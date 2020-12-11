package org.hls.check.webapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("org.hls")
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class WebappConfig {

    private Logger logger = LogManager.getLogger(WebappConfig.class);

    public WebappConfig() {
        logger.info("new");
    }

}
