package ae.ebtic.spl.web.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class SPLConfigApp {

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer properties =
                new PropertySourcesPlaceholderConfigurer();
        properties.setLocation(new FileSystemResource("D:/Projects.github/java_projects/check_web/web/WEB-INF/config/application.properties"));
        properties.setIgnoreResourceNotFound(false);
        return properties;
    }
}
