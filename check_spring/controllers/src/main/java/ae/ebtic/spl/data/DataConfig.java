package ae.ebtic.spl.data;

import jext.neo4j.ogm.config.FileConfigurationSource;
import jext.springframework.data.neo4j.repository.support.ExtendedNeo4jRepository;
import org.neo4j.ogm.config.ConfigurationSource;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;

import java.io.File;

@Configuration
@EnableCaching
@EnableNeo4jRepositories(repositoryBaseClass = ExtendedNeo4jRepository.class)
@ComponentScan("jext.springframework")
public class DataConfig {

    @Value("${spring.app.root}")
    private String homeApp;

    @Bean
    public SessionFactory sessionFactory() {
        // with domain entity base package(s)
        return new SessionFactory(configuration(), "ae.ebtic.spl.data");
    }

    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {
        File applicationPropertiesFile = new File(homeApp, "WEB-INF/application.properties");
        if (!applicationPropertiesFile.exists())
            applicationPropertiesFile = new File(homeApp, "config/application.properties");
        String propertiesFilePath = applicationPropertiesFile.getAbsolutePath();
        ConfigurationSource properties = new FileConfigurationSource(propertiesFilePath, "spring.data.neo4j");
        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder(properties).build();
        return configuration;
    }

    @Bean
    public Neo4jTransactionManager transactionManager() {
        return new Neo4jTransactionManager(sessionFactory());
    }

}
