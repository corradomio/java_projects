package org.hls.check.data;

import jext.neo4j.ogm.config.FileConfigurationSource;
import jext.springframework.data.neo4j.repository.support.ExtendedNeo4jRepository;
import org.hls.check.check_neo4j_2.AppConfiguration;
import org.neo4j.ogm.config.ConfigurationSource;
import org.neo4j.ogm.session.SessionFactory;
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

    @Bean
    public SessionFactory sessionFactory() {
        // with domain entity base package(s)
        return new SessionFactory(configuration(), "org.hls.check.data");
    }

    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {
        File applicationPropertiesFile = AppConfiguration.getApplicationPropertiesFile();
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
