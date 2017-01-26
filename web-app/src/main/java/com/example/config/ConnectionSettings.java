package com.example.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by mac on 1/10/16.
 */
@Component
@ConfigurationProperties(prefix = "environments")
@Data
@ToString
public class ConnectionSettings {

    @NotNull
    @Valid
    private Environment dev;
    private Environment prod;

    @Data
    @ToString
    public static class Environment {
        @NotNull
        @Valid
        private String url;

        @NotNull
        @Valid
        private String name;
    }
}
