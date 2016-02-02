package com.example.integration;

import com.example.SpringDemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(
        classes = SpringDemoApplication.class)
@WebAppConfiguration
@WebIntegrationTest({"server.port=0", "management.port=0"})
public class SpringDemoApplicationTests {

    @Test
    public void contextLoads() {
    }

}
