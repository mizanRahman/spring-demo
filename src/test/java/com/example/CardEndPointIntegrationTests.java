package com.example;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by mac on 11/29/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringDemoApplication.class)
@WebIntegrationTest
@ActiveProfiles("test")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("/cardData.xml")
public class CardEndPointIntegrationTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void before() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    @ExpectedDatabase(value = "/cardData.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void shouldReturn200OK() throws Exception {
        mockMvc.perform(get("/cards"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }


    @Test
    @ExpectedDatabase(value = "/cardDataAfterAdd.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void shouldSaveNewCard() throws Exception {


        mockMvc.perform(
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


        mockMvc.perform(
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