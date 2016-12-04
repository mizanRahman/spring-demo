package com.konasl.epg.infrastructure.config;

import com.konasl.epg.infrastructure.logging.LoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

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
