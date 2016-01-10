package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by mac on 1/10/16.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.example.core.repository")
public class PersistenceConfiguration {
}
