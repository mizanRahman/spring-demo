package com.example;

import org.junit.ClassRule;
import org.junit.Rule;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by mac on 1/19/17.
 */
public class RestAssuredBasedIntegrationTest {

    private WebApplicationContext context;
    private int port;

    public RestAssuredBasedIntegrationTest(WebApplicationContext context, int port) {
        this.context = context;
        this.port = port;
    }

    @ClassRule
    public RestAssuredSetupRule restAssuredSetupRule() {
        return new RestAssuredSetupRule(context, port);
    }

    @Rule
    public DatabaseSeedRule setupDatabaseSeedRule() {
        return new DatabaseSeedRule();
    }
}
