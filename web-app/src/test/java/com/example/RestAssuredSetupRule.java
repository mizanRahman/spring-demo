package com.example;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.rules.ExternalResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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

        // logging concern
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        // json request
        RestAssured.requestSpecification.accept(ContentType.JSON).contentType(ContentType.JSON);


    }




}
