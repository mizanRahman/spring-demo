package com.konasl.epg.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.konasl.epg.ep.security.hmac.HmacAccessFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by mac on 8/6/16.
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.konasl.epg.infrastructure.database", "com.konasl.epg.domain"})
public class ApplicationConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }

    @Bean
    public Scheduler httpClientScheduler() {
        return Schedulers.io();
    }

    @Bean
    public ThreadPoolTaskExecutor apiGatewayRequestExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        return executor;

    }

    @Bean
    AsyncRestTemplate restTemplate( ) {
        return new AsyncRestTemplate(apiGatewayRequestExecutor());
    }

    // TODO: Opening following bean will enable hmac authentication. test cases will fail. need to update test cases.
//    @Bean
//    HmacAccessFilter hmacFilter() {
//        return new HmacAccessFilter();
//    }

}