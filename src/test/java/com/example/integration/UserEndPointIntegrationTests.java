package com.example.integration;

import com.example.Card;
import com.example.SpringDemoApplication;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.Matchers.*;

/**
 * Created by mac on 11/29/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringDemoApplication.class)
@WebIntegrationTest
@ActiveProfiles("test")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "/cardData.xml",
        type = DatabaseOperation.CLEAN_INSERT)
public class UserEndPointIntegrationTests {

    @Autowired
    private WebApplicationContext context;

    @Before
    public void before() {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    @ExpectedDatabase(value = "/cardData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void shouldReturn200OK() throws Exception {
        RestAssured.when()
                .get("/cards")
                .then()
                .log()
                .everything()
                .statusCode(HttpStatus.OK.value());
    }


    @Test
    @ExpectedDatabase(value = "/cardDataAfterAdd.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void shouldSaveNewCard() throws Exception {
        Card card = Card.builder().balance(90).pan("3352732138298").build();
        RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON.toString())
                .body(card)
                .when().post("/cards")
                .then().log().everything().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturnErrorJson() throws Exception {
        Card card = Card.builder().balance(90).pan("33527321141241241255412421412412").build();
        RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON.toString())
                .body(card)
                .when().post("/cards")
                .then().log().everything()
                .statusCode(is(both(greaterThan(399)).and(lessThan(500))));
    }
}
