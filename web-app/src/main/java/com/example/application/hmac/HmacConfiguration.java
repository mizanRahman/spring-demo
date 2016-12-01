package com.example.application.hmac;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mac on 12/1/16.
 */
@Configuration
public class HmacConfiguration {

    @Bean
    CredentialsProvider credentialsProvider() {
        return new SimpleCredentialsProvider("api_key_example", "api_secret_example");
    }
}
