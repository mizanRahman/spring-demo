package com.example.integration;

import com.example.common.AbstractIntegrationTest;
import com.example.core.domain.Card;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.jayway.restassured.RestAssured;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.Matchers.*;

/**
 * Created by mac on 11/29/15.
 */
@DatabaseSetup(value = "/dbunit/cardData.xml", type = DatabaseOperation.CLEAN_INSERT)
public class UserEndPointIntegrationTests extends AbstractIntegrationTest {

    @Test
    @ExpectedDatabase(value = "/dbunit/cardData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void shouldReturn200OK() {
        given()
                .when()
                .get("/cards")
                .then()
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
                .body(card)
                .when()
                .post("/cards")
                .then()
                .statusCode(HttpStatus.OK.value());
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
                .body(card)
                .when()
                .post("/cards")
                .then()
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
                .body(card)
                .when()
                .post("/cards")
                .then()
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
                .body(card)
                .when()
                .put("/cards/{id}", 1)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/cardDataAfterDelete.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void shouldDeleteCardSuccessfully() {
        RestAssured
                .when()
                .delete("/cards/{id}", 2)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/cardData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void shouldFailToDeleteCardBecauseNoCardFoundWithId() {
        RestAssured
                .when()
                .delete("/cards/{id}", 3)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
