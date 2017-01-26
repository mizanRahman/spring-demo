package com.example.config;

import com.example.aop.logging.LoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;

/**
 * Created by mac on 1/10/16.
 */
@Configuration
@EnableAspectJAutoProxy
public class AspectLoggingConfiguration {

    @Bean
    public LoggingAspect loggingAspect() {
        System.out.println("logging aspect setup...");
        return new LoggingAspect();
    }
}
