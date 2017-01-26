package com.example.common;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.config.MatcherConfig;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

/**
 * Created by mac on 1/18/17.
 */
@EasyIntegrationTest
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractIntegrationTest {

    @Autowired
    WebApplicationContext context;

    @Value("${local.server.port}")
    int port;

    @Before
    public void before() {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        RestAssured.port = port;
        RestAssuredMockMvc.mockMvc(mockMvc);

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.requestSpecification=
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
