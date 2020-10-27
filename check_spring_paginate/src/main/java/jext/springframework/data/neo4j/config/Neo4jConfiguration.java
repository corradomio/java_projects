package org.hls.check.check_spring_paginate.truffa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Neo4jConfiguration {

    // @Bean
    // public Driver driver() {
    //     return new InternalDriver();
    // }

    @Bean
    public Neo4JConnector connector() {
        return new Neo4JConnector();
    }
}
