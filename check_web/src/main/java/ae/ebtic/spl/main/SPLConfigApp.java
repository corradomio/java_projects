package ae.ebtic.spl.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
@PropertySource("file:${ae.ebtic.spl.webapp.root}/WEB-INF/application.properties")
public class SPLConfigApp {

    private Logger logger = LoggerFactory.getLogger(SPLConfigApp.class);

    public SPLConfigApp() {
        logger.info("SPLConfigApp::new");
    }

    // @Bean
    // public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    //     PropertySourcesPlaceholderConfigurer properties =
    //             new PropertySourcesPlaceholderConfigurer();
    //     properties.setLocation(new FileSystemResource("D:/Projects.github/java_projects/check_web/web/WEB-INF/application.properties"));
    //     // properties.setLocation(new FileSystemResource("/WEB-INF/application.properties"));
    //     properties.setIgnoreResourceNotFound(false);
    //     return properties;
    // }
}
