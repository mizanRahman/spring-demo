package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = SpringDemoApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class SpringDemoApplicationTests {

    @Test
    public void contextLoads() {
    }

}
