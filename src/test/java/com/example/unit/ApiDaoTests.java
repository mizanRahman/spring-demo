package com.example.unit;

import com.example.SpringDemoApplication;
import com.example.dao.ApiDao;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

/**
 * Created by mac on 12/4/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringDemoApplication.class)
@ActiveProfiles("test")
@Slf4j
public class ApiDaoTests {

    @Autowired
    private ApiDao apiDao;

    @Test
    public void shouldGetPositiveResponse() {

        apiDao.get("http://jsonplaceholder.typicode.com/posts", JsonNode[].class);

    }

    @Test(expected = HttpClientErrorException.class)
    public void shouldThrowNotFoundException() {

        apiDao.get("http://eugenedvorkin.com/seven-micro-services-architecture-advan788tages", String.class);

    }


    @Test(expected = ResourceAccessException.class)
    public void shouldThrowResourceAccessException() {

        apiDao.get("https://www.facebook.com", String.class);

    }
}
