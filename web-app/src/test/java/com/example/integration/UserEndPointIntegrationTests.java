package com.example.integration;

import com.example.SpringDemoApplication;
import com.example.core.domain.Card;
import com.example.ep.config.EndPointPaths;
import com.example.ep.config.EndPoint;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@WebIntegrationTest(randomPort = true)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "/dbunit/cardData.xml",
        type = DatabaseOperation.CLEAN_INSERT)
public class UserEndPointIntegrationTests {

    @Autowired
    private WebApplicationContext context;

    @Value("${local.server.port}")
    int port;

    @Before
    public void before() {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        RestAssured.port = port;
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/cardData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void shouldReturn200OK() {
        RestAssured.when()
                .get(EndPoint.CARDS.fullPath())
                .then()
                .log()
                .everything()
                .statusCode(HttpStatus.OK.value());
    }


    @Test
    @ExpectedDatabase(value = "/dbunit/cardDataAfterAdd.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void shouldSaveNewCard() {
        Card card = Card.builder()
                .balance(90)
                .pan("3352732138298")
                .build();

        RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON.toString())
                .body(card)
                .when().post(EndPoint.CARDS.fullPath())
                .then().log().everything().statusCode(HttpStatus.OK.value());
    }


    @Test
    @ExpectedDatabase(value = "/dbunit/cardData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void shouldReturnErrorJsonForIntegrityViolation() {
        Card card = Card.builder()
                .balance(90)
                .pan("3352732114124124125541242141")
                .build();

        RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON.toString())
                .body(card)
                .when().post(EndPoint.CARDS.fullPath())
                .then().log().everything()
                .statusCode(is(both(greaterThan(399)).and(lessThan(500))));
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/cardData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void shouldReturnErrorJsonForAlreadyExistsPan() {
        Card card = Card.builder()
                .balance(90)
                .pan("121324344")
                .build();

        RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON.toString())
                .body(card)
                .when().post(EndPoint.CARDS.fullPath())
                .then().log().everything()
                .statusCode(HttpStatus.CONFLICT.value());
    }



    @Test
    @ExpectedDatabase(value = "/dbunit/cardDataAfterEdit.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void shouldUpdateCardSuccessfully() {
        Card card = Card.builder()
                .balance(120)
                .pan("1122334455")
                .build();

        RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON.toString())
                .body(card)
                .when().put(EndPointPaths.A_CARD, 1)
                .then().log().everything()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/cardDataAfterDelete.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void shouldDeleteCardSuccessfully() {
        RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON.toString())
                .when().delete(EndPointPaths.A_CARD, 2)
                .then().log().everything()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/cardData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void shouldFailToDeleteCardBecauseNoCardFoundWithId() {
        RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON.toString())
                .when().delete(EndPointPaths.A_CARD, 3)
                .then().log().everything()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
