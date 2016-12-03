package com.example.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by mac on 1/10/16.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.example.infrastructure.database")
public class PersistenceConfiguration {
}
