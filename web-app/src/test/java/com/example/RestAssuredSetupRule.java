package com.example;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.config.MatcherConfig;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.rules.ExternalResource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

/**
 * Created by mizan on 1/19/17.
 */
public class RestAssuredSetupRule extends ExternalResource {

    private WebApplicationContext context;
    private int port;

    public RestAssuredSetupRule(WebApplicationContext context, int port) {
        this.port = port;
        this.context = context;
    }

    @Override
    protected void before() {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        RestAssured.port = port;
        RestAssuredMockMvc.mockMvc(mockMvc);

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.requestSpecification =
                new RequestSpecBuilder().setContentType(ContentType.JSON)
                        .setAccept(ContentType.JSON)
                        .setBasePath("/api")
                        .addHeader("X-Correlation_Id", UUID.randomUUID().toString())
                        .build();

        MatcherConfig matcherConfig = RestAssured.config().getMatcherConfig()
                .errorDescriptionType(MatcherConfig.ErrorDescriptionType.HAMCREST);
        RestAssured.config().matcherConfig(matcherConfig);
    }
}
