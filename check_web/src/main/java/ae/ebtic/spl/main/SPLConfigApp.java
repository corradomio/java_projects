package ae.ebtic.spl.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Configuration
@PropertySource(value = "file:${spring.app.root:./web}/WEB-INF/application.properties", ignoreResourceNotFound = true)
@PropertySource(value = "file:${spring.app.root:./web}/WEB-INF/application-${spring.profiles.active}.properties", ignoreResourceNotFound = true)
public class SPLConfigApp {

    private Logger logger = LoggerFactory.getLogger(SPLConfigApp.class);

    private Environment environment;

    private String profile;

    public SPLConfigApp() {
        logger.info("SPLConfigApp::new");
    }

    @Autowired
    void setEnvironment(Environment env) {
        this.environment = env;
    }

    @PostConstruct
    void postConstruct(){
        String[] activeProfiles = environment.getActiveProfiles();
        logger.info("active profiles: {}", Arrays.toString(activeProfiles));
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
