package com.konasl.epg.infrastructure.config;

import com.konasl.epg.ep.security.hmac.CredentialsProvider;
import com.konasl.epg.ep.security.hmac.SimpleCredentialsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mac on 12/1/16.
 */
@Configuration
public class HmacConfiguration {

    // TODO: Opening following bean will enable hmac authentication. test cases will fail. need to update test cases.
//    @Bean
//    HmacAccessFilter hmacFilter() {
//        return new HmacAccessFilter();
//    }

    @Bean
    CredentialsProvider credentialsProvider() {
        return new SimpleCredentialsProvider("api_key_example", "api_secret_example");
    }
}
