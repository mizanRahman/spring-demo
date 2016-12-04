package com.konasl.epg.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by mac on 8/6/16.
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.konasl.epg.infrastructure.database", "com.konasl.epg.domain"})
public class ApplicationConfig extends WebMvcConfigurerAdapter {

    @Bean(name = "preControllerServiceHandlerRegister")
    FilterRegistrationBean preControllerServiceHandler() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new DelegatingFilterProxy());
        bean.setName("preControllerServiceHandle");
        bean.setOrder(2);
        return bean;
    }

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
}
