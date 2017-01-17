package org.haxagon.hmac.web;

import org.haxagon.hmac.core.CredentialsProvider;
import org.haxagon.hmac.core.HmacSignatureBuilder;
import org.haxagon.hmac.core.SimpleCredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mac on 12/1/16.
 */
@Configuration
@EnableConfigurationProperties(HmacProperties.class)
@ConditionalOnClass(HmacSignatureBuilder.class)
@AutoConfigureAfter({
        WebMvcAutoConfiguration.class,
})
public class HmacConfiguration {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    HmacProperties hmacProperties;

    @Bean
    CredentialsProvider credentialsProvider() {
        return new SimpleCredentialsProvider("api_key_example", "api_secret_example");
    }

    @Bean
    @ConditionalOnProperty(name = "logbook.filter.enabled", havingValue = "true", matchIfMissing = true)
    HmacAccessFilter hmacFilter() {
        return new HmacAccessFilter(hmacProperties);
    }
}
