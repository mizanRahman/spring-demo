package com.example.integration;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by mac on 11/29/15.
 */

@DatabaseSetup(value = "/dbunit/cardData.xml",
        type = DatabaseOperation.CLEAN_INSERT)
public class CardEndPointIntegrationTests extends AbstractMockMvcIntegrationTest {

    @Test
    @ExpectedDatabase(value = "/dbunit/cardData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void shouldReturn200OK() throws Exception {

        mockMvc().perform(get("/cards"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }


    @Test
    @ExpectedDatabase(value = "/dbunit/cardDataAfterAdd.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void shouldSaveNewCard() throws Exception {

        mockMvc().perform(
                post("/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"pan\": \"3352732138298\",\n" +
                                "  \"balance\": 90\n" +
                                "}"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldReturnErrorJson() throws Exception {

        mockMvc().perform(
                post("/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"pan\": \"335273211412412412412421412412\",\n" +
                                "  \"balance\": 90\n" +
                                "}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
